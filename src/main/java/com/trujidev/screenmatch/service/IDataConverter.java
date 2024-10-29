package com.trujidev.screenmatch.service;

public interface IDataConverter {
  <T> T jsonToObject(String json, Class<T> tClass);
}
