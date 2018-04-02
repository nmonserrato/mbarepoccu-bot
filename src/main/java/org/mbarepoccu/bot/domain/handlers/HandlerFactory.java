package org.mbarepoccu.bot.domain.handlers;

import org.apache.commons.lang3.RandomUtils;
import org.mbarepoccu.bot.domain.Handler;
import org.mbarepoccu.bot.domain.reply.content.GIF;
import org.mbarepoccu.bot.domain.reply.content.provider.RandomContentProvider;
import org.mbarepoccu.bot.domain.reply.content.Sticker;
import org.mbarepoccu.bot.domain.reply.content.Text;

import java.util.Arrays;
import java.util.List;

import static org.mbarepoccu.bot.domain.reply.content.provider.RandomContentProvider.randomText;

public class HandlerFactory
{
  public static List<Handler> buildAll() {
    return Arrays.asList(
      new PredicateHandler(m -> m.containsOneOf("aunni si", "aunni su"), randomText("casa tu", "casa as usual...io")),
      new PredicateHandler(m -> m.containsOneOf("aunni vai"), randomText("eh sapessi mbare", "minchia se potessi parlare mbare", "sapessi")),
      new PredicateHandler(m -> m.textIsOneOf("con", "con?", "con chi", "con chi?", "con chi esci", "con chi esci?"), new Text("tua sorella")),
      new PredicateHandler(m -> m.containsOneOf("fifa"), randomText("non mi va mbare. EA sports merda!", "naaaah fifa merda")),
      new PredicateHandler(m -> m.containsOneOf("news"), new Text("nada tu")),
      new PredicateHandler(m -> m.containsOneOf("piccione", "picciuni", "pizzuni"), new Text("non parlo di piccione con te mbare")),
      new PredicateHandler(m -> m.textIsOneOf("test gif"), new GIF("CgADBAADE58AAsgeZAc8eTz0lPWp0gI")),
      new PredicateHandler(m -> m.textIsOneOf("test sticker"), new Sticker("CAADBQADuQEAAukKyAMFe9CCAAH2HHcC")),
      new PredicateHandler(m -> m.containsOneOf("valencia"), randomText("onore a te mbare cheers", "minchia valencia mbare...sei troppo superiore", "non sono degno di parlare di piccione con te mbare")),
      new PredicateHandler(m -> m.textIsOneOf("we", "we mbare"), RandomContentProvider.randomText("we dica", "we", "we mbare")),
      new PredicateHandler(m -> m.containsOneOf("why"), randomText("eh sapessi mbare", "potessi parlare...", "eh sapessi", "sapessi")),
      new PredicateHandler(m -> RandomUtils.nextInt(0, 10) < 3, RandomContentProvider.randomContent(
        new Sticker("CAADBQADuQEAAukKyAMFe9CCAAH2HHcC"),
        new Sticker("CAADBAADgAIAAo-zWQNa5qKVuK6KiQI"),
        new GIF("CgADBAADE58AAsgeZAc8eTz0lPWp0gI"))
      )
    );
  }
}
