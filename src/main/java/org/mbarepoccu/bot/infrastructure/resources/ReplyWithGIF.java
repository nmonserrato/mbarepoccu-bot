package org.mbarepoccu.bot.infrastructure.resources;

public class ReplyWithGIF extends Reply
{
  @SuppressWarnings("unused")
  private final String document;

  public ReplyWithGIF(String chatId, String fileId)
  {
    super(chatId, "sendDocument");
    this.document = fileId;
  }
}
