package org.mbarepoccu.bot.domain.handlers;

import org.apache.commons.lang3.RandomUtils;
import org.mbarepoccu.bot.domain.reply.content.GIF;
import org.mbarepoccu.bot.domain.reply.content.RandomContentProvider;
import org.mbarepoccu.bot.domain.reply.content.Sticker;

import static java.util.Arrays.asList;

public class RandomHandler extends PredicateHandler
{
  public RandomHandler()
  {
    super(m -> RandomUtils.nextInt(0, 10) < 3, new RandomContentProvider(asList(
      new Sticker("CAADBQADuQEAAukKyAMFe9CCAAH2HHcC"),
      new Sticker("CAADBAADgAIAAo-zWQNa5qKVuK6KiQI"),
      new GIF("CgADBAADE58AAsgeZAc8eTz0lPWp0gI")
    )));
  }
}
