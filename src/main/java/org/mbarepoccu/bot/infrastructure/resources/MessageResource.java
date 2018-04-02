package org.mbarepoccu.bot.infrastructure.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.mbarepoccu.bot.domain.Intent;
import org.mbarepoccu.bot.domain.Message;
import org.mbarepoccu.bot.infrastructure.ObjectMapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.mbarepoccu.bot.infrastructure.resources.Reply.Builder.aReply;

@RestController
public class MessageResource
{
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageResource.class);
  private final ObjectMapper objectMapper;

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
      final Reply reply = handle(update.message);
      sleep();
      return reply;
    }
    catch (IOException e)
    {
      LOGGER.error("Error parsing input update", e);
      return null;
    }
  }

  private Reply handle(Message message) {
    Reply reply = null;

    if(message != null)
     reply = buildReplyForMessage(message);

    if(reply != null)
      LOGGER.info("Replying with {}", ToStringBuilder.reflectionToString(reply));

    return reply;
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

  private Reply buildReplyForMessage(Message message)
  {
    final Reply reply = Stream.of(Intent.values())
      .filter(i -> i.canHandle(message))
      .findAny()
      .map(i -> i.buildAnswer(message))
      .orElse(null);

    if (reply != null)
    {
      return reply;
    }

    if (StringUtils.containsIgnoreCase(message.text, "aunni si") ||
        StringUtils.containsIgnoreCase(message.text, "aunni su")){
      return buildReplyWithRandomText(message, "casa tu", "casa as usual...io");
    }

    if (StringUtils.containsIgnoreCase(message.text, "piccione")){
      return aReply().in(message.chat).withText("non parlo di piccione con te mbare").build();
    }

    if (StringUtils.containsIgnoreCase(message.text, "con chi") ||
        StringUtils.equalsIgnoreCase(message.text, "con") ||
      StringUtils.equalsIgnoreCase(message.text, "con?")){
      return aReply().in(message.chat).withText("tua sorella").build();
    }

    if (StringUtils.containsIgnoreCase(message.text, "aunni vai")){
      return buildReplyWithRandomText(message, "eh sapessi mbare", "minchia se potessi parlare mbare", "sapessi");
    }

    if (StringUtils.containsIgnoreCase(message.text, "fifa")){
      return buildReplyWithRandomText(message, "non mi va mbare. EA sports merda!", "naaaah fifa merda");
    }

    if (StringUtils.containsIgnoreCase(message.text, "news")){
      return buildReplyWithRandomText(message, "nada tu");
    }

    if (StringUtils.containsIgnoreCase(message.text, "why")){
      return buildReplyWithRandomText(message, "eh sapessi mbare", "potessi parlare...");
    }

    if (StringUtils.containsIgnoreCase(message.text, "valencia")){
      return buildReplyWithRandomText(message, "onore a te mbare cheers", "minchia valencia mbare...sei troppo superiore", "non sono degno di parlare di piccione con te mbare");
    }

    if (StringUtils.equalsIgnoreCase(message.text, "test gif")){
      return aReply().in(message.chat).withGIF("CgADBAADE58AAsgeZAc8eTz0lPWp0gI").build();
    }

    if (StringUtils.equalsIgnoreCase(message.text, "test sticker")){
      return aReply().in(message.chat).withSticker("CAADBQADuQEAAukKyAMFe9CCAAH2HHcC").build();
    }

    if (RandomUtils.nextInt(0, 10) < 2)
    {
      return buildReplyWithRandomImage(message);
    }

    return null;
  }

  private Reply buildReplyWithRandomImage(Message message)
  {
    final List<Reply> options = Arrays.asList(
      aReply().in(message.chat).withSticker("CAADBQADuQEAAukKyAMFe9CCAAH2HHcC").build(),
      aReply().in(message.chat).withSticker("CAADBAADgAIAAo-zWQNa5qKVuK6KiQI").build(),
      aReply().in(message.chat).withGIF("CgADBAADE58AAsgeZAc8eTz0lPWp0gI").build()
    );
    return random(options);
  }

  private Reply buildReplyWithRandomText(Message message, String... textuals)
  {
    return random(Stream.of(textuals).map(t -> aReply().in(message.chat).withText(t).build()).collect(toList()));
  }

  private Reply random(List<Reply> randomResponses)
  {
    return randomResponses.get(RandomUtils.nextInt(0, randomResponses.size()));
  }
}

