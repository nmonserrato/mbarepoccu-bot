package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.Handler;
import org.mbarepoccu.bot.domain.Message;
import org.mbarepoccu.bot.domain.reply.content.Content;
import org.mbarepoccu.bot.domain.reply.content.ContentProvider;
import org.mbarepoccu.bot.domain.reply.content.FixedContentProvider;
import org.mbarepoccu.bot.infrastructure.resources.Reply;

import java.util.function.Predicate;

import static org.mbarepoccu.bot.infrastructure.resources.Reply.Builder.aReply;

public class PredicateHandler implements Handler
{
  private final Predicate<Message> predicate;
  private final ContentProvider contentProvider;

  protected PredicateHandler(Predicate<Message> predicate, Content content)
  {
    this.predicate = predicate;
    this.contentProvider = new FixedContentProvider(content);
  }

  protected PredicateHandler(Predicate<Message> predicate, ContentProvider contentProvider)
  {
    this.predicate = predicate;
    this.contentProvider = contentProvider;
  }

  @Override
  public boolean canHandle(Message message)
  {
    return predicate.test(message);
  }

  @Override
  public Reply buildAnswer(Message message)
  {
    return aReply().in(message.chat).withContent(contentProvider.get()).build();
  }
}
