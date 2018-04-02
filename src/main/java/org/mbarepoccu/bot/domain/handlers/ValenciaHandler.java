package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.reply.content.RandomContentProvider;

public class ValenciaHandler extends PredicateHandler
{
  public ValenciaHandler()
  {
    super(m -> m.containsOneOf("valencia"),
      RandomContentProvider.forText("onore a te mbare cheers", "minchia valencia mbare...sei troppo superiore", "non sono degno di parlare di piccione con te mbare"));
  }
}
