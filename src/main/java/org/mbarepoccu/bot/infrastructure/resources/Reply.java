package org.mbarepoccu.bot.infrastructure.resources;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.mbarepoccu.bot.domain.Chat;
import org.mbarepoccu.bot.domain.reply.content.Content;

public class Reply {
  public String method = "sendMessage";
  public String chat_id;
  public String text;
  public String sticker;
  public String document;

  @Override
  public String toString()
  {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

  public static class Builder {
    private Chat chat;
    private String text;
    private String sticker;
    private String gif;

    public static Builder aReply() {
      return new Builder();
    }

    public Builder withText(String text) {
      this.text = text;
      this.sticker = null;
      this.gif = null;
      return this;
    }

    public Builder withRandomText(String... options) {
      this.text = options[RandomUtils.nextInt(0, options.length)];
      this.sticker = null;
      this.gif = null;
      return this;
    }

    public Builder withSticker(String file_id) {
      this.sticker = file_id;
      this.text = null;
      this.gif = null;
      return this;
    }

    public Builder withGIF(String file_id) {
      this.gif = file_id;
      this.text = null;
      this.sticker = null;
      return this;
    }

    public Builder withContent(Content content) {
      this.gif = content.getGIF();
      this.text = content.getText();
      this.sticker = content.getSticker();
      return this;
    }

    public Builder in(Chat chat) {
      this.chat = chat;
      return this;
    }

    public Reply build() {
      final Reply reply = new Reply();
      reply.chat_id = chat.id;
      reply.text = text;
      reply.sticker = sticker;
      reply.document = gif;
      reply.method = decodeMethod();
      return reply;
    }

    private String decodeMethod()
    {
      if (text != null)
        return "sendMessage";
      if (gif != null)
        return "sendDocument";
      if (sticker != null)
        return "sendSticker";

      return null;
    }
  }
}
