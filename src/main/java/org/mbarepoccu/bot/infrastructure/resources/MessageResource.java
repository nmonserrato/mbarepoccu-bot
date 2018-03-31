package org.mbarepoccu.bot.infrastructure.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MessageResource
{
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageResource.class);

  @RequestMapping(value = "/new-message", method = RequestMethod.POST)
  public void handle(@RequestBody Map<String, Object> body) {
    LOGGER.info("Message Received with body {}!", body);
  }

  @RequestMapping(value = "/STATUS", method = RequestMethod.GET)
  public String status() {
    return "mbarepoccubot: 1.0.0-SNAPSHOT";
  }
}
