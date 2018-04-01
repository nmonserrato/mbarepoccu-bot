package org.mbarepoccu.bot.infrastructure;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ObjectMapperFactory
{
  public static ObjectMapper forRestResource()
  {
    return new ObjectMapper(){{
      enable(SerializationFeature.INDENT_OUTPUT);
      enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
      disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
      disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
      disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS);
      disable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
      disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
      setSerializationInclusion(JsonInclude.Include.NON_NULL);
      setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
      setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }};
  }
}
