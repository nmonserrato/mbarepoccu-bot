package org.mbarepoccu.bot.domain;

import org.mbarepoccu.bot.infrastructure.resources.Reply;

public interface Handler
{
  boolean canHandle(Message message);
  Reply buildAnswer(Message message);
}
