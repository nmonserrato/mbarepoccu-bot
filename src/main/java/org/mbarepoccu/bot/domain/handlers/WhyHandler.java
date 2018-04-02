package org.mbarepoccu.bot.domain.handlers;

import org.apache.commons.lang3.StringUtils;
import org.mbarepoccu.bot.domain.Handler;
import org.mbarepoccu.bot.domain.Message;
import org.mbarepoccu.bot.infrastructure.resources.Reply;

import static org.mbarepoccu.bot.infrastructure.resources.Reply.Builder.aReply;

public class WhyHandler implements Handler
{
  @Override
  public boolean canHandle(Message message)
  {
    return StringUtils.containsIgnoreCase(message.text, "why");
  }

  @Override
  public Reply buildAnswer(Message message)
  {
    return aReply().in(message.chat).withRandomText("eh sapessi mbare", "potessi parlare...").build();
  }
}
