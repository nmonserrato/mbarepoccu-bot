package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.reply.content.RandomContentProvider;

public class AunniSiHandler extends PredicateHandler
{
  public AunniSiHandler()
  {
    super(m -> m.containsOneOf("aunni si", "aunni su"),
      RandomContentProvider.randomText("casa tu", "casa as usual...io"));
  }
}
