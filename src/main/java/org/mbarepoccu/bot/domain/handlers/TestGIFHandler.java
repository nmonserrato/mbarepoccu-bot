package org.mbarepoccu.bot.domain.handlers;

import org.mbarepoccu.bot.domain.reply.content.GIF;

public class TestGIFHandler extends PredicateHandler
{
  public TestGIFHandler()
  {
    super(m -> m.textIsOneOf("test gif"), new GIF("CgADBAADE58AAsgeZAc8eTz0lPWp0gI"));
  }
}
