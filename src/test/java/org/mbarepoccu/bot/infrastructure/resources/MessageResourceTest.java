package org.mbarepoccu.bot.infrastructure.resources;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MessageResourceTest
{
  private final MessageResource messageResource = new MessageResource();

  @Test
  void we_aunni_si()
  {
    final Reply reply1 = messageResource.handle(anIncomingMessage("we aunni si"));
    final Reply reply2 = messageResource.handle(anIncomingMessage("aunni su"));

    assertValidReply(reply1, "casa tu");
    assertValidReply(reply2, "casa tu");
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

    assertValidReply(reply, "non mi va mbare. EA sports merda!");
  }

  @Test
  void aunni_vai()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("aunni vai"));

    assertValidReply(reply, "eh sapessi mbare");
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
    assertEquals(text, reply.text);
    assertEquals("aChatId", reply.chat_id);
    assertEquals("sendMessage", reply.method);
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
