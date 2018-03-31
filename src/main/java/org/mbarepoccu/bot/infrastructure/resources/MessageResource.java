package org.mbarepoccu.bot.infrastructure.resources;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MessageResource
{
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageResource.class);
  private final ObjectMapper objectMapper = new ObjectMapper(){{
    enable(SerializationFeature.INDENT_OUTPUT);
    enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
    disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS);
    disable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
    disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
  }};

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
      final Reply reply = handle(update);
      sleep();
      return reply;
    }
    catch (IOException e)
    {
      LOGGER.error("Error parsing input update");
      return null;
    }
  }

  Reply handle(Update update) {
    Reply reply = null;

    if(update.message != null)
     reply = buildReplyForMessage(update.message);;

    if(reply != null)
      LOGGER.info("Replying with {}", ToStringBuilder.reflectionToString(reply));

    return reply;
  }

  private void sleep()
  {
    final long delay = RandomUtils.nextLong(1000, 5000);
    try
    {
      Thread.sleep(delay);
    }
    catch (InterruptedException e) { }
  }

  private Reply buildReplyForMessage(Message message)
  {
    if (StringUtils.equalsIgnoreCase(message.text, "we"))
    {
      return buildReplyWithRandomText(message, "we dica", "we", "we mbare");
    }

    if (StringUtils.containsIgnoreCase(message.text, "aunni si") ||
        StringUtils.containsIgnoreCase(message.text, "aunni su")){
      return buildReplyWithRandomText(message, "casa tu", "casa as usual...io");
    }

    if (StringUtils.containsIgnoreCase(message.text, "piccione")){
      return buildReplyWithText(message, "non parlo di piccione con te mbare");
    }

    if (StringUtils.containsIgnoreCase(message.text, "con chi") ||
        StringUtils.equalsIgnoreCase(message.text, "con")){
      return buildReplyWithText(message, "tua sorella");
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

    if (RandomUtils.nextInt(0, 10) < 2)
    {
      return buildReplyWithRandomImage(message);
    }

    return null;
  }

  private Reply buildReplyWithRandomImage(Message message)
  {
    final int index = RandomUtils.nextInt(0, 3);
    switch (index)
    {
      case 0: //XD sticker
        return buildReplyWithSticker(message, "CAADBQADuQEAAukKyAMFe9CCAAH2HHcC");
      case 1: //cheers sticker
        return buildReplyWithSticker(message, "CAADBAADgAIAAo-zWQNa5qKVuK6KiQI");
      case 2: //cheers GIF
        return buildReplyWithGIF(message, "CgADBAADE58AAsgeZAc8eTz0lPWp0gI");
    }

    return null;
  }

  private Reply buildReplyWithRandomText(Message message, String... options)
  {
    final int index = RandomUtils.nextInt(0, options.length);
    return buildReplyWithText(message, options[index]);
  }

  private Reply buildReplyWithText(Message message, String text)
  {
    Reply reply = new Reply();
    reply.chat_id = message.chat.id;
    reply.text = text;
    reply.method = "sendMessage";
    return reply;
  }

  private Reply buildReplyWithSticker(Message message, String file_id)
  {
    Reply reply = new Reply();
    reply.chat_id = message.chat.id;
    reply.sticker = file_id;
    reply.method = "sendSticker";
    return reply;
  }

  private Reply buildReplyWithGIF(Message message, String file_id)
  {
    Reply reply = new Reply();
    reply.chat_id = message.chat.id;
    reply.document = file_id;
    reply.method = "sendDocument";
    return reply;
  }
}

class Chat {
  public String id;

  @Override
  public String toString()
  {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

class Message {
  public String text;
  public Chat chat;

  @Override
  public String toString()
  {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

class Update {
    public Message message;

  @Override
  public String toString()
  {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

class Reply{
  public String method = "sendMessage";
  public String chat_id;
  public String text;
  public String sticker;
  public String document;

  @Override
  public String toString()
  {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}