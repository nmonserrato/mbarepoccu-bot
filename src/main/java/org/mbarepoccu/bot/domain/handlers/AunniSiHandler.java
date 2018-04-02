package org.mbarepoccu.bot.domain.handlers;

import org.apache.commons.lang3.StringUtils;
import org.mbarepoccu.bot.domain.Handler;
import org.mbarepoccu.bot.domain.Message;
import org.mbarepoccu.bot.infrastructure.resources.Reply;

import static org.mbarepoccu.bot.infrastructure.resources.Reply.Builder.aReply;

public class AunniSiHandler implements Handler
{
  @Override
  public boolean canHandle(Message message)
  {
    return StringUtils.containsIgnoreCase(message.text, "aunni si") ||
      StringUtils.containsIgnoreCase(message.text, "aunni su");
  }

  @Override
  public Reply buildAnswer(Message message)
  {
    return aReply().in(message.chat).withRandomText("casa tu", "casa as usual...io").build();
  }
}
