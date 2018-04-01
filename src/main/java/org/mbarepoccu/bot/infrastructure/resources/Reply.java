package org.mbarepoccu.bot.infrastructure.resources;

import org.mbarepoccu.bot.domain.Chat;

import java.lang.reflect.Constructor;

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
    private Class<? extends Reply> clazz;
    private String content;

    public static Builder aReply() {
      return new Builder();
    }

    public Builder withText(String text) {
      this.clazz = ReplyWithText.class;
      this.content = text;
      return this;
    }

    public Builder withSticker(String fileId) {
      this.clazz = ReplyWithSticker.class;
      this.content = fileId;
      return this;
    }

    public Builder withGIF(String fileId) {
      this.clazz = ReplyWithGIF.class;
      this.content = fileId;
      return this;
    }

    public Builder in(Chat chat) {
      this.chat = chat;
      return this;
    }

    public Reply build() {
      try {
        final Constructor<? extends Reply> constructor = clazz.getConstructor(String.class, String.class);
        return constructor.newInstance(chat.id, content);
      }
      catch (ReflectiveOperationException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
