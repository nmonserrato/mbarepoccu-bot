package org.mbarepoccu.bot.domain.reply.content;

public class Text implements Content
{
  private final String text;

  private Text(String text)
  {
    this.text = text;
  }

  public static Text text(String text)
  {
    return new Text(text);
  }

  @Override
  public String getText()
  {
    return text;
  }

  @Override
  public String getSticker()
  {
    return null;
  }

  @Override
  public String getGIF()
  {
    return null;
  }
}
