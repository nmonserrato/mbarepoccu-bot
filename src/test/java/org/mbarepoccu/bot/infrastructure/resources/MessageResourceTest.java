package org.mbarepoccu.bot.infrastructure.resources;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageResourceTest
{
  private final MessageResource messageResource = new MessageResource();

  @Test
  void we_aunni_si()
  {
    final Reply reply1 = messageResource.handle(anIncomingMessage("we aunni si"));
    final Reply reply2 = messageResource.handle(anIncomingMessage("aunni su"));

    assertReplyIn(reply1, "casa tu", "casa as usual. io.");
    assertReplyIn(reply2, "casa tu", "casa as usual. io.");
  }

  @Test
  void piccione()
  {
    final Update incomingMessage = anIncomingMessage("hai sentito il piccione?");

    final Reply reply = messageResource.handle(incomingMessage);

    assertValidReply(reply, "non parlo di piccione con te mbare");
  }

  @Test
  void tua_sorella()
  {
    final Reply reply1 = messageResource.handle(anIncomingMessage("con chi esci?"));
    final Reply reply2 = messageResource.handle(anIncomingMessage("con"));

    assertValidReply(reply1, "tua sorella");
    assertValidReply(reply2, "tua sorella");
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
    final Reply reply = messageResource.handle(new Update());
    assertNull(reply);
  }

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

  private Update anIncomingMessage(String text)
  {
    final Update incomingMessage = new Update();
    incomingMessage.message = new Message();
    incomingMessage.message.chat = new Chat();
    incomingMessage.message.text = text;
    incomingMessage.message.chat.id = "aChatId";
    return incomingMessage;
  }
}
