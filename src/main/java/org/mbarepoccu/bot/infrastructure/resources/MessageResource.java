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
    if (update.message.text.equalsIgnoreCase("we aunni si")){
      reply = new Reply();
      reply.chat_id = update.message.chat.id;
      reply.text = "casa tu";
    }

    if (update.message.text.contains("piccione")){
      reply = new Reply();
      reply.chat_id = update.message.chat.id;
      reply.text = "non parlo di piccione con te mbare";
    }

    if(reply != null)
      LOGGER.info("Replying with {}", ToStringBuilder.reflectionToString(reply));
    return reply;
  }

  @RequestMapping(value = "/status", method = RequestMethod.GET)
  public String status() {
    return "mbarepoccu-bot: 1.0.0-SNAPSHOT";
  }
}

class Chat {
  public String id;
}

class Message {
  public String text;
  public Chat chat;
}

class Update {
    public Message message;
}

class Reply{
  public String method = "sendMessage";
  public String chat_id;
  public String text;
}