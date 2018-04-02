package org.mbarepoccu.bot.domain.reply.content;

public class GIF implements Content
{
  private final String fileId;

  private GIF(String fileId)
  {
    this.fileId = fileId;
  }

  public static GIF gif(String fileId)
  {
    return new GIF(fileId);
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
