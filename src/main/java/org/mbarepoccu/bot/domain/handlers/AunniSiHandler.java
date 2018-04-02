package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.Message;
import org.mbarepoccu.bot.infrastructure.resources.Reply;

import static org.mbarepoccu.bot.infrastructure.resources.Reply.Builder.aReply;

public class AunniSiHandler extends PredicateHandler
{
  public AunniSiHandler()
  {
    super(m -> m.containsOneOf("aunni si", "aunni su"));
  }

  @Override
  public Reply buildAnswer(Message message)
  {
    return aReply().in(message.chat).withRandomText("casa tu", "casa as usual...io").build();
  }
}
