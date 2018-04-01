package org.mbarepoccu.bot.infrastructure.resources;

public class ReplyWithGIF extends Reply
{
  @Override
  public String getMethod()
  {
    return "sendDocument";
  }
}
