package org.mbarepoccu.bot.domain.reply.content;

import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class RandomContentProvider implements ContentProvider
{
  private final List<Content> options;

  private RandomContentProvider(List<Content> options){
    this.options = options;
  }

  public static RandomContentProvider randomText(String... options){
    final List<Content> contentOptions = Stream.of(options)
      .map(Text::new)
      .collect(toList());

    return new RandomContentProvider(contentOptions);
  }

  public static RandomContentProvider randomContent(Content... options){
    return new RandomContentProvider(Arrays.asList(options));
  }

  @Override
  public Content get()
  {
    return options.get(RandomUtils.nextInt(0, options.size()));
  }
}
