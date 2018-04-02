package org.mbarepoccu.bot.domain.reply.content.provider;

import org.mbarepoccu.bot.domain.reply.content.Content;

@FunctionalInterface
public interface ContentProvider
{
  Content get();
}
