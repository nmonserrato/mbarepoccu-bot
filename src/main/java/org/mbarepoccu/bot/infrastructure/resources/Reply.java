package org.mbarepoccu.bot.infrastructure.resources;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.mbarepoccu.bot.domain.Chat;

class Reply {
  private String method = "sendMessage";
  public String chat_id;
  public String text;
  public String sticker;
  public String document;

  @Override
  public String toString()
  {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

  public String getMethod()
  {
    return method;
  }

  public void setMethod(String method)
  {
    this.method = method;
  }

  static class Builder {
    private Chat chat;
    private String method;
    private String text;
    private String sticker;
    private String gif;

    public static Builder aReply() {
      return new Builder();
    }

    public Builder withText(String text) {
      this.method = "sendMessage";
      this.text = text;
      this.sticker = null;
      this.gif = null;
      return this;
    }

    public Builder withSticker(String file_id) {
      this.method = "sendSticker";
      this.sticker = file_id;
      this.text = null;
      this.gif = null;
      return this;
    }

    public Builder withGIF(String file_id) {
      this.method = "sendDocument";
      this.gif = file_id;
      this.text = null;
      this.sticker = null;
      return this;
    }

    public Builder in(Chat chat) {
      this.chat = chat;
      return this;
    }

    public Reply build() {
      final Reply reply;
      if (gif != null)
      {
        reply = new ReplyWithGIF();
      }
      else
      {
        reply = new Reply();
      }

      reply.chat_id = chat.id;
      reply.text = text;
      reply.sticker = sticker;
      reply.document = gif;
      reply.setMethod(method);
      return reply;
    }
  }
}
