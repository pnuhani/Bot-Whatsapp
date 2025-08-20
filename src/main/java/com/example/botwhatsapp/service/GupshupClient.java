package com.example.botwhatsapp.service;

import com.example.botwhatsapp.config.GupshupProps;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class GupshupClient {
  private final RestTemplate http;
  private final GupshupProps props;

  public GupshupClient(RestTemplate http, GupshupProps props) {
    this.http = http;
    this.props = props;
  }

  public void sendText(String toPhone, String text) {
    if (props.apiKey() == null || props.apiKey().isBlank()) {
      // Fail fast with a clear message if someone tries to send without creds
      throw new IllegalStateException("Gupshup API key is not set (GUPSHUP_API_KEY).");
    }
    String url = props.baseUrl() + "/msg";

    HttpHeaders h = new HttpHeaders();
    h.setContentType(MediaType.APPLICATION_JSON);
    h.set("apikey", props.apiKey());

    Map<String, Object> body = Map.of(
        "channel", "whatsapp",
        "source", props.appId(),          // your Gupshup WA source/app id/number
        "destination", toPhone,
        "message", Map.of("type", "text", "text", text)
    );

    http.exchange(url, HttpMethod.POST, new HttpEntity<>(body, h), String.class);
  }
}
