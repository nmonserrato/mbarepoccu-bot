package org.mbarepoccu.bot.infrastructure.resources;

public class ReplyWithText extends Reply
{
  @SuppressWarnings("unused")
  private final String text;

  public ReplyWithText(String chatId, String text)
  {
    super(chatId, "sendMessage");
    this.text = text;
  }
}
