package org.mbarepoccu.bot.infrastructure.resources;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageResourceTest
{
  private final MessageResource messageResource = new MessageResource(false);

  @Test
  void we()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("we"));

    assertTextReply(reply, "we dica", "we", "we mbare");
  }

  @Test
  void we_aunni_si()
  {
    final Reply reply1 = messageResource.handle(anIncomingMessage("we aunni si"));
    final Reply reply2 = messageResource.handle(anIncomingMessage("aunni su"));

    assertTextReply(reply1, "casa tu", "casa as usual...io");
    assertTextReply(reply2, "casa tu", "casa as usual...io");
  }

  @Test
  void piccione()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("hai sentito il piccione?"));

    assertTextReply(reply, "non parlo di piccione con te mbare");
  }

  @Test
  void valencia()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("valencia"));

    assertTextReply(reply, "onore a te mbare cheers", "minchia valencia mbare...sei troppo superiore", "non sono degno di parlare di piccione con te mbare");
  }

  @Test
  void tua_sorella()
  {
    final Reply reply1 = messageResource.handle(anIncomingMessage("con chi esci?"));
    final Reply reply2 = messageResource.handle(anIncomingMessage("con"));
    final Reply reply3 = messageResource.handle(anIncomingMessage("con?"));

    assertTextReply(reply1, "tua sorella");
    assertTextReply(reply2, "tua sorella");
    assertTextReply(reply3, "tua sorella");
  }

  @Test
  void news()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("we news"));

    assertTextReply(reply, "nada tu");
  }

  @Test
  void fifa()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("fifa"));

    assertTextReply(reply, "non mi va mbare. EA sports merda!", "naaaah fifa merda");
  }

  @Test
  void aunni_vai()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("aunni vai"));

    assertTextReply(reply, "eh sapessi mbare", "minchia se potessi parlare mbare", "sapessi");
  }

  @Test
  void why()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("why"));

    assertTextReply(reply, "eh sapessi mbare", "potessi parlare...");
  }

  @Test
  void message_may_be_null()
  {
    final Reply reply = messageResource.handle("{}");

    assertNull(reply);
  }

  @Test
  void sticker()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("test sticker"));

    assertReplyIsSticker(reply);
  }

  @Test
  void gif()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("test gif"));

    assertReplyIsGIF(reply);
  }

  private void assertReplyIsSticker(Reply reply)
  {
    assertEquals("sendSticker", reply.getMethod());
    assertNull(reply.text);
    assertNull(reply.document);
    assertNotNull(reply.sticker);
  }

  private void assertReplyIsGIF(Reply reply)
  {
    assertTrue(reply instanceof ReplyWithGIF);
    assertEquals("sendDocument", reply.getMethod());
    assertNull(reply.text);
    assertNull(reply.sticker);
    assertNotNull(reply.document);
  }

  private void assertTextReply(Reply reply, String text)
  {
    assertNotNull(reply);
    assertEquals("aChatId", reply.chat_id);
    assertEquals("sendMessage", reply.getMethod());
    assertEquals(text, reply.text);
  }

  private void assertTextReply(Reply reply, String... options)
  {
    assertNotNull(reply);
    assertEquals("aChatId", reply.chat_id);
    assertEquals("sendMessage", reply.getMethod());
    assertTrue(Arrays.asList(options).contains(reply.text));
  }

  private String anIncomingMessage(String text)
  {
    return "{" +
      "  \"update_id\": 512650892," +
      "  \"message\": {" +
      "    \"message_id\": 58," +
      "    \"from\": {" +
      "      \"id\": 8588218," +
      "      \"is_bot\": false," +
      "      \"first_name\": \"Nino\"," +
      "      \"language_code\": \"en-US\"" +
      "    }," +
      "    \"chat\":{" +
      "      \"id\": \"aChatId\"," +
      "      \"first_name\": \"Nino\"," +
      "      \"type\": \"private\"" +
      "    }," +
      "    \"date\": 1522510390," +
      "    \"text\": \"" + text + "\"" +
      "  }  " +
      "}";
  }
}
