package org.mbarepoccu.bot.domain.handlers;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.mbarepoccu.bot.domain.Handler;
import org.mbarepoccu.bot.domain.Message;
import org.mbarepoccu.bot.infrastructure.resources.Reply;

import java.util.Arrays;
import java.util.List;

import static org.mbarepoccu.bot.infrastructure.resources.Reply.Builder.aReply;

public class RandomHandler implements Handler
{
  @Override
  public boolean canHandle(Message message)
  {
    return RandomUtils.nextInt(0, 10) < 3;
  }

  @Override
  public Reply buildAnswer(Message message)
  {
    final List<Reply> options = Arrays.asList(
      aReply().in(message.chat).withSticker("CAADBQADuQEAAukKyAMFe9CCAAH2HHcC").build(),
      aReply().in(message.chat).withSticker("CAADBAADgAIAAo-zWQNa5qKVuK6KiQI").build(),
      aReply().in(message.chat).withGIF("CgADBAADE58AAsgeZAc8eTz0lPWp0gI").build()
    );
    return options.get(RandomUtils.nextInt(0, options.size()));
  }
}
