package org.mbarepoccu.bot.infrastructure.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.mbarepoccu.bot.domain.Handler;
import org.mbarepoccu.bot.domain.Message;
import org.mbarepoccu.bot.domain.handlers.RandomHandler;
import org.mbarepoccu.bot.infrastructure.ObjectMapperFactory;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@RestController
public class MessageResource
{
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageResource.class);
  private final ObjectMapper objectMapper;
  private final List<Handler> handlers = new Reflections("org.mbarepoccu.bot.domain.handlers")
    .getSubTypesOf(Handler.class)
    .stream()
    .filter(c -> !c.getSimpleName().equals("RandomHandler"))
    .filter(c -> !Modifier.isAbstract(c.getModifiers()))
    .map(createInstance())
    .collect(toList());

  private boolean withDelay = true;

  @Autowired
  public MessageResource()
  {
    this(true);
  }

  MessageResource(boolean withDelay)
  {
    this.objectMapper = ObjectMapperFactory.forRestResource();
    this.withDelay = withDelay;
    this.handlers.add(new RandomHandler());
  }

  @RequestMapping(value = "/status", method = RequestMethod.GET)
  public String status() {
    return "mbarepoccu-bot: 1.0.0-SNAPSHOT";
  }

  @RequestMapping(value = "/new-message", method = RequestMethod.POST)
  public Reply handle(@RequestBody String string) {
    LOGGER.info("Message Received with body {}", string);
    try
    {
      final Update update = objectMapper.readValue(string, Update.class);
      final Message message = update.message;
      if (message == null)
        return null;

      final Optional<Handler> handler = handlers.stream().filter(i -> i.canHandle(message)).findAny();
      LOGGER.debug( "Found handler {}", handler);
      Reply reply = handler.map(i -> i.buildAnswer(message)).orElse(null);

      LOGGER.info("Replying with {}", ToStringBuilder.reflectionToString(reply));
      sleep();
      return reply;
    }
    catch (IOException e)
    {
      LOGGER.error("Error parsing input update", e);
      return null;
    }
  }

  private void sleep()
  {
    if (withDelay) {
      final long delay = RandomUtils.nextLong(500, 2000);
      try {
        Thread.sleep(delay);
      }
      catch (InterruptedException e) {
      }
    }
  }

  private static Function<Class<? extends Handler>, ? extends Handler> createInstance()
  {
    return aClass -> {
      try
      {
        return aClass.newInstance();
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }
    };
  }
}

