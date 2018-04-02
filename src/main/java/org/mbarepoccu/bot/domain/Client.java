package org.mbarepoccu.bot.domain;

import org.mbarepoccu.bot.infrastructure.resources.Reply;

public interface Client
{
  void send(Reply reply);
}
