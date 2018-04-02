package org.mbarepoccu.bot.infrastructure.client;

import org.mbarepoccu.bot.domain.Client;
import org.mbarepoccu.bot.infrastructure.resources.Reply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class RestClient implements Client
{
  private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);
  private final RestTemplate restTemplate = new RestTemplate();
  private String baseUrl = "https://api.telegram.org/bot589608180:AAHLBqFg86G97tYbPueI1Ldsdh0Twr1Og8w/";

  @Override
  public void send(Reply reply)
  {
    try
    {
      final ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + reply.method, reply, String.class);
      LOGGER.info("Response is {}", response);
    }
    catch (RestClientResponseException e)
    {
      LOGGER.error("client exception body {}", e.getResponseBodyAsString());
      LOGGER.error("client exception", e);
    }
  }
}
