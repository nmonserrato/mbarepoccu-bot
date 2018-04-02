package org.mbarepoccu.bot.domain.reply.content;

public class GIF implements Content
{
  private final String fileId;

  public GIF(String fileId)
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
    return null;
  }

  @Override
  public String getGIF()
  {
    return fileId;
  }
}
