package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.Handler;
import org.mbarepoccu.bot.domain.Message;
import org.mbarepoccu.bot.domain.reply.content.Content;
import org.mbarepoccu.bot.infrastructure.resources.Reply;

import java.util.function.Predicate;

import static org.mbarepoccu.bot.infrastructure.resources.Reply.Builder.aReply;

public abstract class PredicateHandler implements Handler
{
  private final Predicate<Message> predicate;
  private final Content content;

  protected PredicateHandler(Predicate<Message> predicate)
  {
    this(predicate, null);
  }

  protected PredicateHandler(Predicate<Message> predicate, Content content)
  {
    this.predicate = predicate;
    this.content = content;
  }

  @Override
  public boolean canHandle(Message message)
  {
    return predicate.test(message);
  }

  @Override
  public Reply buildAnswer(Message message)
  {
    return aReply().in(message.chat).withContent(content).build();
  }
}
