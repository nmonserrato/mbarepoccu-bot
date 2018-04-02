package org.mbarepoccu.bot.domain.handlers;

import org.apache.commons.lang3.RandomUtils;
import org.mbarepoccu.bot.domain.Handler;
import org.mbarepoccu.bot.domain.Message;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.mbarepoccu.bot.domain.reply.content.GIF.gif;
import static org.mbarepoccu.bot.domain.reply.content.Sticker.sticker;
import static org.mbarepoccu.bot.domain.reply.content.Text.text;
import static org.mbarepoccu.bot.domain.reply.content.provider.RandomContentProvider.randomContent;
import static org.mbarepoccu.bot.domain.reply.content.provider.RandomContentProvider.randomText;

public class HandlerFactory
{
  public static List<Handler> buildAll() {
    return Arrays.asList(
      new PredicateHandler(contains("aunni si", "aunni su"), randomText("casa tu", "casa...io", "casa as usual tu")),
      new PredicateHandler(contains("aunni vai"), randomText("eh sapessi mbare", "minchia se potessi parlare mbare", "sapessi")),
      new PredicateHandler(is("con", "con?", "con chi", "con chi?", "con chi esci", "con chi esci?"), text("tua sorella")),
      new PredicateHandler(contains("fifa"), randomText("non mi va mbare. EA sports merda!", "naaaah fifa merda")),
      new PredicateHandler(contains("news"), text("nada tu")),
      new PredicateHandler(contains("piccione", "picciuni", "pizzuni"), text("non parlo di piccione con te mbare")),
      new PredicateHandler(is("test gif"), gif("CgADBAADE58AAsgeZAc8eTz0lPWp0gI")),
      new PredicateHandler(is("test sticker"), sticker("CAADBQADuQEAAukKyAMFe9CCAAH2HHcC")),
      new PredicateHandler(contains("valencia"), randomText("onore a te mbare cheers", "minchia valencia mbare...sei troppo superiore", "non sono degno di parlare di piccione con te mbare", "valencia...cheers")),
      new PredicateHandler(is("we", "we mbare"), randomText("we dica", "we", "we mbare")),
      new PredicateHandler(contains("why"), randomText("eh sapessi mbare", "potessi parlare...", "eh sapessi", "sapessi")),
      new PredicateHandler(sometimes(), randomContent(sticker("CAADBQADuQEAAukKyAMFe9CCAAH2HHcC"), sticker("CAADBAADgAIAAo-zWQNa5qKVuK6KiQI"), gif("CgADBAADE58AAsgeZAc8eTz0lPWp0gI"), text("immagino"), text("ne prendo atto"))
      )
    );
  }

  private static Predicate<Message> sometimes()
  {
    return m -> RandomUtils.nextInt(0, 10) < 5;
  }

  private static Predicate<Message> contains(String... options){
    return m-> m.containsOneOf(options);
  }

  private static Predicate<Message> is(String... options){
    return m -> m.textIsOneOf(options);
  }
}
