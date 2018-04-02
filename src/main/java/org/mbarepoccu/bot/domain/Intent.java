package org.mbarepoccu.bot.domain;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.mbarepoccu.bot.infrastructure.resources.Reply;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.mbarepoccu.bot.infrastructure.resources.Reply.Builder.aReply;

public enum Intent
{
  WE {
    @Override
    public boolean canHandle(Message message) {
      return StringUtils.equalsIgnoreCase(message.text, "we");
    }

    @Override
    public Reply buildAnswer(Message message) {
      return buildReplyWithRandomText(message, "we dica", "we", "we mbare");
    }
  };

  public abstract boolean canHandle(Message message);
  public abstract Reply buildAnswer(Message message);

  private static Reply buildReplyWithRandomText(Message message, String... textuals)
  {
    return random(Stream.of(textuals).map(t -> aReply().in(message.chat).withText(t).build()).collect(toList()));
  }

  private static Reply random(List<Reply> randomResponses)
  {
    return randomResponses.get(RandomUtils.nextInt(0, randomResponses.size()));
  }
}
