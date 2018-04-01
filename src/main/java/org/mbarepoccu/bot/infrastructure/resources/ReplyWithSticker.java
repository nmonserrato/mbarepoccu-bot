package org.mbarepoccu.bot.infrastructure.resources;

public class ReplyWithSticker extends Reply
{
  @SuppressWarnings("unused")
  private final String sticker;

  public ReplyWithSticker(String chatId, String sticker)
  {
    super(chatId, "sendSticker");
    this.sticker = sticker;
  }
}
