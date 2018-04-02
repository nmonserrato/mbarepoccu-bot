package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.reply.content.TextContent;

public class NewsHandler extends PredicateHandler
{
  public NewsHandler()
  {
    super(m -> m.containsOneOf("news"), new TextContent("nada tu"));
  }
}
