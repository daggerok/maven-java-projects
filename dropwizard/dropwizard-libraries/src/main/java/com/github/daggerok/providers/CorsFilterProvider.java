package com.github.daggerok.providers;

import io.vavr.Lazy;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CorsFilterProvider implements ContainerResponseFilter {

  final Lazy<Integer> MAX_AGE = Lazy.of(() -> 12 * 60 * 60);

  @Override
  public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
    final MultivaluedMap<String, Object> headers = containerResponseContext.getHeaders();
    headers.add("Access-Control-Allow-Origin", "*");
    headers.add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization ");
    headers.add("Access-Control-Expose-Headers", "headers-to-be-exposed");
    headers.add("Access-Control-Allow-Credentials", "true");
    headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    headers.add("Access-Control-Max-Age", MAX_AGE.get());
    headers.add("x-responded-by", "cors-response-filter");
  }
}
