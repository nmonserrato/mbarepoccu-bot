package org.mbarepoccu.bot.infrastructure.resources;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ReplyWithGIF extends Reply
{
  private final String document;

  public ReplyWithGIF(String chatId, String fileId)
  {
    super(chatId, "sendDocument");
    this.document = fileId;
  }

  @Override
  public String toString()
  {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
