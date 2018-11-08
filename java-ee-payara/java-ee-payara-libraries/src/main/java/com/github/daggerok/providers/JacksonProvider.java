package com.github.daggerok.providers;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.*;

@Provider
public class JacksonProvider implements ContextResolver<ObjectMapper> {

//  private Lazy<ObjectMapper> objectMapper = Lazy.of(() -> {
//    final ObjectMapper objectMapper = new ObjectMapper();
//    objectMapper.enable(INDENT_OUTPUT);
//    objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
//    objectMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
//    objectMapper.configure(FAIL_ON_EMPTY_BEANS, false);
//    objectMapper.setSerializationInclusion(NON_NULL);
//    return objectMapper;
//  });

  private final ObjectMapper objectMapper;

  public JacksonProvider() {
    objectMapper = new ObjectMapper();
    objectMapper.enable(INDENT_OUTPUT);
    objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
    objectMapper.configure(FAIL_ON_EMPTY_BEANS, false);
    objectMapper.setSerializationInclusion(NON_NULL);
  }

  @Override
  public ObjectMapper getContext(Class<?> aClass) {
//    return objectMapper.get();
    return objectMapper;
  }
}
