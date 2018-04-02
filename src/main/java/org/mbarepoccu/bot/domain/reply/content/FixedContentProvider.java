package org.mbarepoccu.bot.domain.reply.content;

public class FixedContentProvider implements ContentProvider
{
  private final Content content;

  public FixedContentProvider(Content content)
  {
    this.content = content;
  }

  @Override
  public Content get()
  {
    return content;
  }
}
