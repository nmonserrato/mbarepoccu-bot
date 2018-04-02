package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.reply.content.RandomContentProvider;

public class AunniVaiHandler extends PredicateHandler
{
  public AunniVaiHandler()
  {
    super(m -> m.containsOneOf("aunni vai"),
      RandomContentProvider.forText("eh sapessi mbare", "minchia se potessi parlare mbare", "sapessi"));
  }
}
