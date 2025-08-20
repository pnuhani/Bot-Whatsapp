package com.example.botwhatsapp.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(GupshupProps.class)
public class AppConfig {
  @Bean
  RestTemplate restTemplate() { return new RestTemplate(); }
}
