package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.reply.content.TextContent;

public class PiccioneHandler extends PredicateHandler
{
  public PiccioneHandler()
  {
    super(m -> m.containsOneOf("piccione", "picciuni", "pizzuni"),
      new TextContent("non parlo di piccione con te mbare"));
  }
}
