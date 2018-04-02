package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.reply.content.RandomContentProvider;

public class WeHandler extends PredicateHandler
{
  public WeHandler()
  {
    super(m -> m.textIsOneOf("we", "we mbare"),
      RandomContentProvider.randomText("we dica", "we", "we mbare"));
  }
}
