package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.Handler;
import org.mbarepoccu.bot.domain.Message;
import org.mbarepoccu.bot.infrastructure.resources.Reply;

import static org.mbarepoccu.bot.infrastructure.resources.Reply.Builder.aReply;

public class ValenciaHandler implements Handler
{
  @Override
  public boolean canHandle(Message message)
  {
    return message.containsOneOf("valencia");
  }

  @Override
  public Reply buildAnswer(Message message)
  {
    return aReply().in(message.chat).withRandomText("onore a te mbare cheers", "minchia valencia mbare...sei troppo superiore", "non sono degno di parlare di piccione con te mbare").build();
  }
}
