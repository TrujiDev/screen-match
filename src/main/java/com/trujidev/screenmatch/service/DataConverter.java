package com.trujidev.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter implements IDataConverter {
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public <T> T jsonToObject(String json, Class<T> tClass) {
    try {
      return objectMapper.readValue(json, tClass);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
