package org.mbarepoccu.bot.infrastructure.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomUtils;
import org.mbarepoccu.bot.domain.Handler;
import org.mbarepoccu.bot.domain.Message;
import org.mbarepoccu.bot.domain.handlers.HandlerFactory;
import org.mbarepoccu.bot.infrastructure.ObjectMapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class MessageResource
{
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageResource.class);
  private final ObjectMapper objectMapper;
  private final List<Handler> handlers = HandlerFactory.buildAll();

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
      LOGGER.debug("Handler is -> {}", handler);

      Reply reply = handler.map(i -> i.buildAnswer(message)).orElse(null);
      LOGGER.info("Replying with {}", reply);

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
        LOGGER.info("Interrupted while sleeping! WTF!", e);
      }
    }
  }
}

