package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.reply.content.RandomContentProvider;

public class WhyHandler extends PredicateHandler
{
  public WhyHandler()
  {
    super(m -> m.containsOneOf("why"),
      RandomContentProvider.randomText("eh sapessi mbare", "potessi parlare...", "eh sapessi", "sapessi"));
  }
}
