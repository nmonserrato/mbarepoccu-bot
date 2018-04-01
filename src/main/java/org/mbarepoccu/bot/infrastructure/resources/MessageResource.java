package org.mbarepoccu.bot.infrastructure.resources;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.mbarepoccu.bot.domain.Chat;
import org.mbarepoccu.bot.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.mbarepoccu.bot.infrastructure.resources.Reply.Builder.aReply;

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

  private boolean withDelay = true;

  @Autowired
  public MessageResource()
  {
  }

  public MessageResource(boolean withDelay)
  {
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
    if(withDelay)
    {
      final long delay = RandomUtils.nextLong(500, 2000);
      try
      {
        Thread.sleep(delay);
      }
      catch (InterruptedException e)
      {
      }
    }
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
    return aReply().in(message.chat).withText(options[index]).build();
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

class Update {
  public Message message;

  @Override
  public String toString()
  {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

class Reply {
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

  static class Builder {
    private Chat chat;
    private String method;
    private String text;
    private String sticker;
    private String gif;

    public static Builder aReply() {
      return new Builder();
    }

    public Builder withText(String text) {
      this.method = "sendMessage";
      this.text = text;
      this.sticker = null;
      this.gif = null;
      return this;
    }

    public Builder withSticker(String file_id) {
      this.method = "sendSticker";
      this.sticker = file_id;
      this.text = null;
      this.gif = null;
      return this;
    }

    public Builder withGIF(String file_id) {
      this.method = "sendDocument";
      this.gif = file_id;
      this.text = null;
      this.sticker = null;
      return this;
    }

    public Builder in(Chat chat) {
      this.chat = chat;
      return this;
    }

    public Reply build() {
      final Reply reply = new Reply();
      reply.chat_id = chat.id;
      reply.text = text;
      reply.sticker = sticker;
      reply.document = gif;
      reply.method = method;
      return reply;
    }
  }
}