package org.mbarepoccu.bot.infrastructure.resources;

import org.junit.jupiter.api.Test;
import org.mbarepoccu.bot.domain.Chat;
import org.mbarepoccu.bot.domain.Message;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageResourceTest
{
  private final MessageResource messageResource = new MessageResource();

  @Test
  void we()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("we"));

    assertReplyIn(reply, "we dica", "we", "we mbare");
  }

  @Test
  void we_aunni_si()
  {
    final Reply reply1 = messageResource.handle(anIncomingMessage("we aunni si"));
    final Reply reply2 = messageResource.handle(anIncomingMessage("aunni su"));

    assertReplyIn(reply1, "casa tu", "casa as usual...io");
    assertReplyIn(reply2, "casa tu", "casa as usual...io");
  }

  @Test
  void piccione()
  {
    final Message incomingMessage = anIncomingMessage("hai sentito il piccione?");

    final Reply reply = messageResource.handle(incomingMessage);

    assertValidReply(reply, "non parlo di piccione con te mbare");
  }

  @Test
  void valencia()
  {
    final Message incomingMessage = anIncomingMessage("valencia");

    final Reply reply = messageResource.handle(incomingMessage);

    assertReplyIn(reply, "onore a te mbare cheers", "minchia valencia mbare...sei troppo superiore", "non sono degno di parlare di piccione con te mbare");
  }

  @Test
  void tua_sorella()
  {
    final Reply reply1 = messageResource.handle(anIncomingMessage("con chi esci?"));
    final Reply reply2 = messageResource.handle(anIncomingMessage("con"));
    final Reply reply3 = messageResource.handle(anIncomingMessage("con?"));

    assertValidReply(reply1, "tua sorella");
    assertValidReply(reply2, "tua sorella");
    assertValidReply(reply3, "tua sorella");
  }

  @Test
  void news()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("we news"));

    assertValidReply(reply, "nada tu");
  }

  @Test
  void fifa()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("fifa"));

    assertReplyIn(reply, "non mi va mbare. EA sports merda!", "naaaah fifa merda");
  }

  @Test
  void aunni_vai()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("aunni vai"));

    assertReplyIn(reply, "eh sapessi mbare", "minchia se potessi parlare mbare", "sapessi");
  }

  @Test
  void why()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("why"));

    assertReplyIn(reply, "eh sapessi mbare", "potessi parlare...");
  }

  @Test
  void message_may_be_null()
  {
    final Update update = new Update();
    final Reply reply = messageResource.handle(update.message);
    assertNull(reply);
  }

  //TODO test sticker

  private void assertValidReply(Reply reply, String text)
  {
    assertNotNull(reply);
    assertEquals("aChatId", reply.chat_id);
    assertEquals("sendMessage", reply.method);
    assertEquals(text, reply.text);
  }

  private void assertReplyIn(Reply reply, String... options)
  {
    assertNotNull(reply);
    assertEquals("aChatId", reply.chat_id);
    assertEquals("sendMessage", reply.method);
    assertTrue(Arrays.asList(options).contains(reply.text));
  }

  private Message anIncomingMessage(String text)
  {
    Message message = new Message();
    message.chat = new Chat();
    message.text = text;
    message.chat.id = "aChatId";
    return message;
  }
}
