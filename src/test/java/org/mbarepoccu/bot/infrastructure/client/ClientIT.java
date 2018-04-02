package org.mbarepoccu.bot.infrastructure.client;

import org.junit.Ignore;
import org.junit.Test;
import org.mbarepoccu.bot.domain.Chat;
import org.mbarepoccu.bot.infrastructure.resources.Reply;

import static org.mbarepoccu.bot.infrastructure.resources.Reply.Builder.aReply;

@Ignore
public class ClientIT
{
  private final RestClient client = new RestClient();

  @Test
  public void send_a_sticker()
  {
    final Chat chat = new Chat();
    chat.id = "8588218"; // privata con nino
//    chat.id = "327661718";
    final Reply reply = aReply().withSticker("CAADAQADIA4AApl_iAL6cMlQ-bc0HAI").in(chat).build();
    client.send(reply);
  }

  @Test
  public void send_a_text()
  {
    final Chat chat = new Chat();
    chat.id = "8588218"; // privata con nino
//    chat.id = "327661718";
    final Reply reply = aReply().withText("gia'").in(chat).build();
    client.send(reply);
  }
}