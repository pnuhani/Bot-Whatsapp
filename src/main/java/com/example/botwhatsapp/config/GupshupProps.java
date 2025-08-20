package com.example.botwhatsapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "carevego.gupshup")
public record GupshupProps(String appId, String apiKey, String baseUrl) {}
