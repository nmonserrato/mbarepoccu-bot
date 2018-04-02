package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.Handler;
import org.mbarepoccu.bot.domain.Message;
import org.mbarepoccu.bot.infrastructure.resources.Reply;

import static org.mbarepoccu.bot.infrastructure.resources.Reply.Builder.aReply;

public class ConHandler implements Handler
{
  @Override
  public boolean canHandle(Message message)
  {
    return message.textIsOneOf("con", "con?", "con chi", "con chi?", "con chi esci", "con chi esci?");
  }

  @Override
  public Reply buildAnswer(Message message)
  {
    return aReply().in(message.chat).withText("tua sorella").build();
  }
}
