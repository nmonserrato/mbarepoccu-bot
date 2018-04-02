package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.reply.content.RandomContentProvider;

public class AunniSiHandler extends PredicateHandler
{
  public AunniSiHandler()
  {
    super(m -> m.containsOneOf("aunni si", "aunni su"),
      RandomContentProvider.forText("casa tu", "casa as usual...io"));
  }
}
