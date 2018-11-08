package com.github.daggerok.providers;

import io.vavr.collection.HashMap;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

import static javax.ws.rs.Priorities.AUTHENTICATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Provider
@Priority(AUTHENTICATION)
public class AuthFilterProvider implements ContainerRequestFilter {

  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    final String authorization = containerRequestContext.getHeaderString("Authorization");
    if (null == authorization || authorization.trim().isEmpty()) {
      containerRequestContext.abortWith(Response.status(UNAUTHORIZED)
                                                .entity(HashMap.of("error", "non-empty authorization required")
                                                               .toJavaMap())
                                                .build());
    }
  }
}
