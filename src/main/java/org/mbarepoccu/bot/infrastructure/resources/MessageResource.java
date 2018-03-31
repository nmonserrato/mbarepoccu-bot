package org.mbarepoccu.bot.infrastructure.resources;

import org.apache.commons.lang3.builder.ToStringBuilder;
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

  @RequestMapping(value = "/new-message", method = RequestMethod.POST)
  public Reply handle(@RequestBody Update update) {
    LOGGER.info("Message Received with body {}", ToStringBuilder.reflectionToString(update));

    Reply reply = null;

    if(update.message != null)
     reply = buildReplyForUpdate(update.message.text, update.message.chat.id);;

    if(reply != null)
      LOGGER.info("Replying with {}", ToStringBuilder.reflectionToString(reply));

    return reply;
  }

  private Reply buildReplyForUpdate(String inputMessageText, String chatId)
  {
    if (inputMessageText.equalsIgnoreCase("we aunni si")){
      Reply reply = new Reply();
      reply.chat_id = chatId;
      reply.text = "casa tu";
      return reply;
    }
    if (inputMessageText.contains("piccione")){
      Reply reply = new Reply();
      reply.chat_id = chatId;
      reply.text = "non parlo di piccione con te mbare";
      return reply;
    }

    return null;
  }

  @RequestMapping(value = "/status", method = RequestMethod.GET)
  public String status() {
    return "mbarepoccu-bot: 1.0.0-SNAPSHOT";
  }
}

class Chat {
  public String id;

  @Override
  public String toString()
  {
    return ToStringBuilder.reflectionToString(this);
  }
}

class Message {
  public String text;
  public Chat chat;

  @Override
  public String toString()
  {
    return ToStringBuilder.reflectionToString(this);
  }
}

class Update {
    public Message message;

  @Override
  public String toString()
  {
    return ToStringBuilder.reflectionToString(this);
  }
}

class Reply{
  public String method = "sendMessage";
  public String chat_id;
  public String text;

  @Override
  public String toString()
  {
    return ToStringBuilder.reflectionToString(this);
  }
}