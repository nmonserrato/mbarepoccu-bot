package org.mbarepoccu.bot.domain.reply.content;

public class Text implements Content
{
  private final String text;

  public Text(String text)
  {
    this.text = text;
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
