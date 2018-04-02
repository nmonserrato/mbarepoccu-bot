package org.mbarepoccu.bot.domain.reply.content;

import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class RandomContentProvider implements ContentProvider
{
  private final List<Content> options;

  public RandomContentProvider(Content... options){
    this(Arrays.asList(options));
  }

  public RandomContentProvider(List<Content> options){
    this.options = options;
  }

  public static RandomContentProvider forText(String... options){
    final List<Content> contentOptions = Stream.of(options)
      .map(o -> new TextContent(o))
      .collect(toList());

    return new RandomContentProvider(contentOptions);
  }

  @Override
  public Content get()
  {
    return options.get(RandomUtils.nextInt(0, options.size()));
  }
}
