package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.reply.content.Sticker;

public class TestStickerHandler extends PredicateHandler
{
  public TestStickerHandler()
  {
    super(m -> m.textIsOneOf("test sticker"), new Sticker("CAADBQADuQEAAukKyAMFe9CCAAH2HHcC"));
  }
}
