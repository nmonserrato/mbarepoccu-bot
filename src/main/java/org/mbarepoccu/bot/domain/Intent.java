package org.mbarepoccu.bot.domain;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.mbarepoccu.bot.infrastructure.resources.Reply;

import java.util.Arrays;
import java.util.List;

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
        return aReply().in(message.chat).withRandomText("we dica", "we", "we mbare").build();
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
      return aReply().in(message.chat).withRandomText("casa tu", "casa as usual...io").build();
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
      return aReply().in(message.chat).withRandomText("eh sapessi mbare", "minchia se potessi parlare mbare", "sapessi").build();
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
      return aReply().in(message.chat).withRandomText("non mi va mbare. EA sports merda!", "naaaah fifa merda").build();
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
      return aReply().in(message.chat).withRandomText("nada tu").build();
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
      return aReply().in(message.chat).withRandomText("eh sapessi mbare", "potessi parlare...").build();
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
      return aReply().in(message.chat).withRandomText("onore a te mbare cheers", "minchia valencia mbare...sei troppo superiore", "non sono degno di parlare di piccione con te mbare").build();
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
      final List<Reply> options = Arrays.asList(
        aReply().in(message.chat).withSticker("CAADBQADuQEAAukKyAMFe9CCAAH2HHcC").build(),
        aReply().in(message.chat).withSticker("CAADBAADgAIAAo-zWQNa5qKVuK6KiQI").build(),
        aReply().in(message.chat).withGIF("CgADBAADE58AAsgeZAc8eTz0lPWp0gI").build()
      );
      return options.get(RandomUtils.nextInt(0, options.size()));
    }
  };

  public abstract boolean canHandle(Message message);
  public abstract Reply buildAnswer(Message message);
}
