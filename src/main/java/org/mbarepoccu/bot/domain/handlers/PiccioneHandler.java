package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.Handler;
import org.mbarepoccu.bot.domain.Message;
import org.mbarepoccu.bot.infrastructure.resources.Reply;

import static org.mbarepoccu.bot.infrastructure.resources.Reply.Builder.aReply;

public class PiccioneHandler implements Handler
{
  @Override
  public boolean canHandle(Message message)
  {
    return message.containsOneOf("piccione", "picciuni", "pizzuni");
  }

  @Override
  public Reply buildAnswer(Message message)
  {
    return aReply().in(message.chat).withText("non parlo di piccione con te mbare").build();
  }
}
