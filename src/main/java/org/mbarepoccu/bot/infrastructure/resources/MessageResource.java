package org.mbarepoccu.bot.infrastructure.resources;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageResource
{
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageResource.class);

  @RequestMapping(value = "/status", method = RequestMethod.GET)
  public String status() {
    return "mbarepoccu-bot: 1.0.0-SNAPSHOT";
  }

  @RequestMapping(value = "/new-message", method = RequestMethod.POST)
  public Reply handle(@RequestBody Update update) {
    LOGGER.info("Message Received with body {}", ToStringBuilder.reflectionToString(update));

    Reply reply = null;

    if(update.message != null)
     reply = buildReplyForUpdate(update.message);;

    if(reply != null)
      LOGGER.info("Replying with {}", ToStringBuilder.reflectionToString(reply));

    return reply;
  }

  private Reply buildReplyForUpdate(Message message)
  {
    if (StringUtils.containsIgnoreCase(message.text, "aunni si") ||
        StringUtils.containsIgnoreCase(message.text, "aunni su")){
      return buildReplyWithText(message, "casa tu");
    }

    if (StringUtils.containsIgnoreCase(message.text, "piccione")){
      return buildReplyWithText(message, "non parlo di piccione con te mbare");
    }

    if (StringUtils.containsIgnoreCase(message.text, "con chi") ||
        StringUtils.equalsIgnoreCase(message.text, "con")){
      return buildReplyWithText(message, "tua sorella");
    }

    return null;
  }

  private Reply buildReplyWithText(Message message, String text)
  {
    Reply reply = new Reply();
    reply.chat_id = message.chat.id;
    reply.text = text;
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

  @Override
  public String toString()
  {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}