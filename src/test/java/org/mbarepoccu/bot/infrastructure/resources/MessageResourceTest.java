package org.mbarepoccu.bot.infrastructure.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mbarepoccu.bot.infrastructure.ObjectMapperFactory;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class MessageResourceTest
{
  private final MessageResource messageResource = new MessageResource(false);
  private final ObjectMapper objectMapper = ObjectMapperFactory.forRestResource();

  @Test
  public void we()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("we"));

    assertTextReply(reply, "we dica", "we", "we mbare");
  }

  @Test
  public void we_aunni_si()
  {
    final Reply reply1 = messageResource.handle(anIncomingMessage("we aunni si"));
    final Reply reply2 = messageResource.handle(anIncomingMessage("aunni su"));

    assertTextReply(reply1, "casa tu", "casa...io", "casa as usual tu");
    assertTextReply(reply2, "casa tu", "casa...io", "casa as usual tu");
  }

  @Test
  public void piccione()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("hai sentito il piccione?"));

    assertTextReply(reply, "non parlo di piccione con te mbare");
  }

  @Test
  public void valencia()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("valencia"));

    assertTextReply(reply, "onore a te mbare cheers", "minchia valencia mbare...sei troppo superiore", "non sono degno di parlare di piccione con te mbare", "valencia...cheers");
  }

  @Test
  public void tua_sorella()
  {
    final Reply reply1 = messageResource.handle(anIncomingMessage("con chi esci?"));
    final Reply reply2 = messageResource.handle(anIncomingMessage("con"));
    final Reply reply3 = messageResource.handle(anIncomingMessage("con?"));

    assertTextReply(reply1, "tua sorella");
    assertTextReply(reply2, "tua sorella");
    assertTextReply(reply3, "tua sorella");
  }

  @Test
  public void news()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("we news"));

    assertTextReply(reply, "nada tu");
  }

  @Test
  public void fifa()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("fifa"));

    assertTextReply(reply, "non mi va mbare. EA sports merda!", "naaaah fifa merda");
  }

  @Test
  public void aunni_vai()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("aunni vai"));

    assertTextReply(reply, "eh sapessi mbare", "minchia se potessi parlare mbare", "sapessi");
  }

  @Test
  public void why()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("why"));

    assertTextReply(reply, "eh sapessi mbare", "potessi parlare...", "eh sapessi", "sapessi");
  }

  @Test
  public void message_may_be_null()
  {
    final Reply reply = messageResource.handle("{}");

    assertNull(reply);
  }

  @Test
  public void sticker()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("test sticker"));

    assertReplyIsSticker(reply);
  }

  @Test
  public void gif()
  {
    final Reply reply = messageResource.handle(anIncomingMessage("test gif"));

    assertReplyIsGIF(reply);
  }

  private void assertReplyIsSticker(Reply reply)
  {
    try
    {
      final String json = objectMapper.writeValueAsString(reply);
      assertTrue(json.contains("\"method\" : \"sendSticker\""));
      assertTrue(json.contains("\"chat_id\" : \"aChatId\""));
      assertTrue(json.contains("\"sticker\" : \""));
      assertFalse(json.contains("\"text\" : \""));
      assertFalse(json.contains("\"document\" : \""));
    }
    catch (JsonProcessingException e)
    {
      throw new RuntimeException(e);
    }
  }

  private void assertReplyIsGIF(Reply reply)
  {
    try
    {
      final String json = objectMapper.writeValueAsString(reply);
      assertTrue(json.contains("\"method\" : \"sendDocument\""));
      assertTrue(json.contains("\"chat_id\" : \"aChatId\""));
      assertTrue(json.contains("\"document\" : \""));
      assertFalse(json.contains("\"text\" : \""));
      assertFalse(json.contains("\"sticker\" : \""));
    }
    catch (JsonProcessingException e)
    {
      throw new RuntimeException(e);
    }
  }

  private void assertTextReply(Reply reply, String text)
  {
    try
    {
      final String json = objectMapper.writeValueAsString(reply);
      assertTrue(json.contains("\"method\" : \"sendMessage\""));
      assertTrue(json.contains("\"chat_id\" : \"aChatId\""));
      assertTrue(json.contains("\"text\" : \""+text+"\""));
      assertFalse(json.contains("\"document\" : \""));
      assertFalse(json.contains("\"sticker\" : \""));
    }
    catch (JsonProcessingException e)
    {
      throw new RuntimeException(e);
    }
  }

  private void assertTextReply(Reply reply, String... options)
  {
    boolean valid = false;
    for (String option : options)
    {
      try {
        assertTextReply(reply, option);
        valid = true;
      } catch (AssertionError e){ }
    }
    assertTrue("expected value to match one of " + Arrays.toString(options), valid);
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
