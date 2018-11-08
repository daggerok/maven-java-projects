package com.github.daggerok.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.Lazy;

import javax.annotation.PostConstruct;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.*;

@Provider
public class JacksonProvider implements ContextResolver<ObjectMapper> {

  private Lazy<ObjectMapper> lazyMapper = Lazy.of(() -> {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(INDENT_OUTPUT);
    objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
    objectMapper.configure(FAIL_ON_EMPTY_BEANS, false);
    objectMapper.setSerializationInclusion(NON_NULL);
    return objectMapper;
  });

  @Override
  public ObjectMapper getContext(Class<?> aClass) {
    return lazyMapper.get();
  }
}
