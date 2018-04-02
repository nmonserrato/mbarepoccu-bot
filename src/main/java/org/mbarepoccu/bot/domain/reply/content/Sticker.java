package org.mbarepoccu.bot.domain.reply.content;

public class Sticker implements Content
{
  private final String fileId;

  public Sticker(String fileId)
  {
    this.fileId = fileId;
  }

  @Override
  public String getText()
  {
    return null;
  }

  @Override
  public String getSticker()
  {
    return fileId;
  }

  @Override
  public String getGIF()
  {
    return null;
  }
}
