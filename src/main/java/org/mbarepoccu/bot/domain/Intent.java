package org.mbarepoccu.bot.domain;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.mbarepoccu.bot.infrastructure.resources.Reply;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.mbarepoccu.bot.infrastructure.resources.Reply.Builder.aReply;

public enum Intent
{
  WE {
      @Override
      public boolean canHandle(Message message)
      {
        return StringUtils.equalsIgnoreCase(message.text, "we");
      }

      @Override
      public Reply buildAnswer(Message message)
      {
        return buildReplyWithRandomText(message, "we dica", "we", "we mbare");
      }
  },

  AUNNI_SI {
    @Override
    public boolean canHandle(Message message)
    {
      return StringUtils.containsIgnoreCase(message.text, "aunni si") ||
        StringUtils.containsIgnoreCase(message.text, "aunni su");
    }

    @Override
    public Reply buildAnswer(Message message)
    {
      return buildReplyWithRandomText(message, "casa tu", "casa as usual...io");
    }
  },
  PICCIONE {
    @Override
    public boolean canHandle(Message message)
    {
      return StringUtils.containsIgnoreCase(message.text, "piccione");
    }

    @Override
    public Reply buildAnswer(Message message)
    {
      return aReply().in(message.chat).withText("non parlo di piccione con te mbare").build();
    }
  },
  CON {
    @Override
    public boolean canHandle(Message message)
    {
      return StringUtils.containsIgnoreCase(message.text, "con chi") ||
        StringUtils.equalsIgnoreCase(message.text, "con") ||
        StringUtils.equalsIgnoreCase(message.text, "con?");
    }

    @Override
    public Reply buildAnswer(Message message)
    {
      return aReply().in(message.chat).withText("tua sorella").build();
    }
  },
  AUNNI_VAI {
    @Override
    public boolean canHandle(Message message)
    {
      return StringUtils.containsIgnoreCase(message.text, "aunni vai");
    }

    @Override
    public Reply buildAnswer(Message message)
    {
      return buildReplyWithRandomText(message, "eh sapessi mbare", "minchia se potessi parlare mbare", "sapessi");
    }
  },
  FIFA {
    @Override
    public boolean canHandle(Message message)
    {
      return StringUtils.containsIgnoreCase(message.text, "fifa");
    }

    @Override
    public Reply buildAnswer(Message message)
    {
      return buildReplyWithRandomText(message, "non mi va mbare. EA sports merda!", "naaaah fifa merda");
    }
  },
  NEWS {
    @Override
    public boolean canHandle(Message message)
    {
      return StringUtils.containsIgnoreCase(message.text, "news");
    }

    @Override
    public Reply buildAnswer(Message message)
    {
      return buildReplyWithRandomText(message, "nada tu");
    }
  },
  WHY {
    @Override
    public boolean canHandle(Message message)
    {
      return StringUtils.containsIgnoreCase(message.text, "why");
    }

    @Override
    public Reply buildAnswer(Message message)
    {
      return buildReplyWithRandomText(message, "eh sapessi mbare", "potessi parlare...");
    }
  },
  VALENCIA {
    @Override
    public boolean canHandle(Message message)
    {
      return StringUtils.containsIgnoreCase(message.text, "valencia");
    }

    @Override
    public Reply buildAnswer(Message message)
    {
      return buildReplyWithRandomText(message, "onore a te mbare cheers", "minchia valencia mbare...sei troppo superiore", "non sono degno di parlare di piccione con te mbare");
    }
  },
  TEST_GIF {
    @Override
    public boolean canHandle(Message message)
    {
      return StringUtils.equalsIgnoreCase(message.text, "test gif");
    }

    @Override
    public Reply buildAnswer(Message message)
    {
      return aReply().in(message.chat).withGIF("CgADBAADE58AAsgeZAc8eTz0lPWp0gI").build();
    }
  },
  TEST_STICKER {
    @Override
    public boolean canHandle(Message message)
    {
      return StringUtils.equalsIgnoreCase(message.text, "test sticker");
    }

    @Override
    public Reply buildAnswer(Message message)
    {
      return aReply().in(message.chat).withSticker("CAADBQADuQEAAukKyAMFe9CCAAH2HHcC").build();
    }
  },
  RANDOM {
    @Override
    public boolean canHandle(Message message)
    {
      return RandomUtils.nextInt(0, 10) < 3;
    }

    @Override
    public Reply buildAnswer(Message message)
    {
      return buildReplyWithRandomImage(message);
    }
  };

  public abstract boolean canHandle(Message message);
  public abstract Reply buildAnswer(Message message);

  private static Reply buildReplyWithRandomText(Message message, String... textuals)
  {
    return random(Stream.of(textuals).map(t -> aReply().in(message.chat).withText(t).build()).collect(toList()));
  }

  private static Reply random(List<Reply> randomResponses)
  {
    return randomResponses.get(RandomUtils.nextInt(0, randomResponses.size()));
  }

  private static Reply buildReplyWithRandomImage(Message message)
  {
    final List<Reply> options = Arrays.asList(
      aReply().in(message.chat).withSticker("CAADBQADuQEAAukKyAMFe9CCAAH2HHcC").build(),
      aReply().in(message.chat).withSticker("CAADBAADgAIAAo-zWQNa5qKVuK6KiQI").build(),
      aReply().in(message.chat).withGIF("CgADBAADE58AAsgeZAc8eTz0lPWp0gI").build()
    );
    return random(options);
  }
}
