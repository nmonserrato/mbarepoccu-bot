package org.mbarepoccu.bot.infrastructure.resources;

import org.mbarepoccu.bot.domain.Chat;

class Reply {
  @SuppressWarnings("unused")
  private String method;
  public String chat_id;

  public Reply(String chatId, String method)
  {
    this.chat_id = chatId;
    this.method = method;
  }

  static class Builder {
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

    public Builder in(Chat chat) {
      this.chat = chat;
      return this;
    }

    public Reply build() {
      final Reply reply;
      if (gif != null)
      {
        reply = new ReplyWithGIF(chat.id, gif);
      } else if (sticker != null){
        reply = new ReplyWithSticker(chat.id, sticker);
      } else {
        reply = new ReplyWithText(chat.id, text);
      }

      return reply;
    }
  }
}
