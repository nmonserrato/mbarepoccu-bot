package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.reply.content.TextContent;

public class ConHandler extends PredicateHandler
{
  public ConHandler()
  {
    super(m -> m.textIsOneOf("con", "con?", "con chi", "con chi?", "con chi esci", "con chi esci?"),
          new TextContent("tua sorella"));
  }
}
