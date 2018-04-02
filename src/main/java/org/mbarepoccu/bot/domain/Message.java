package org.mbarepoccu.bot.domain;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.stream.Stream;

public class Message
{
  public String text;
  public Chat chat;

  public boolean containsOneOf(String... options)
  {
    return Stream.of(options).anyMatch(o -> StringUtils.containsIgnoreCase(text, o));
  }

  public boolean textIsOneOf(String... options)
  {
    return Stream.of(options).anyMatch(o -> StringUtils.equalsIgnoreCase(text, o));
  }

  @Override
  public String toString()
  {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}
