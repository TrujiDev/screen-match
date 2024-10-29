package com.trujidev.screenmatch.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumeApi {
  public static String getData(String url) {
    HttpResponse<String> response;
    try (HttpClient client = HttpClient.newHttpClient()) {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(url))
          .build();

      response = null;

      try {
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    return response != null ? response.body() : null;
  }
}
