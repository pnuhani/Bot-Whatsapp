package com.example.botwhatsapp.web;

import com.example.botwhatsapp.service.GupshupClient;
import org.slf4j.Logger; import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/webhook/gupshup")
public class WebhookController {
  private static final Logger log = LoggerFactory.getLogger(WebhookController.class);
  private final GupshupClient client;

  public WebhookController(GupshupClient client) {
    this.client = client;
  }

  @PostMapping
  public ResponseEntity<Void> onMessage(@RequestBody Map<String, Object> payload) {
    log.info("Inbound payload: {}", payload);

    Map<String,Object> sender = (Map<String,Object>) payload.getOrDefault("sender", Map.of());
    String from = String.valueOf(sender.getOrDefault("phone", sender.getOrDefault("id", "")));

    Map<String,Object> message = (Map<String,Object>) payload.getOrDefault("message", Map.of());
    String text = String.valueOf(message.getOrDefault("text", ""));

    if (!from.isBlank()) {
      String reply = "Hello! CareVego here 🚀";
      if (!text.isBlank()) reply += " You said: \"" + text + "\"";
      try {
        client.sendText(from, reply);
      } catch (Exception e) {
        log.warn("Skipping Gupshup send (maybe creds not set). Cause: {}", e.getMessage());
      }
    }
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<String> ping() { return ResponseEntity.ok("ok"); }
}
