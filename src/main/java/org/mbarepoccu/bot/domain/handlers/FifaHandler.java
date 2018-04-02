package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.reply.content.RandomContentProvider;

public class FifaHandler extends PredicateHandler
{
  public FifaHandler()
  {
    super(m -> m.containsOneOf("fifa"),
      RandomContentProvider.randomText("non mi va mbare. EA sports merda!", "naaaah fifa merda"));
  }
}
