package com.github.daggerok.providers;

import lombok.extern.java.Log;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static java.lang.String.format;
import static java.util.Collections.singletonMap;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Log
@Provider
public class ExceptionMapperProvider implements ExceptionMapper<Exception> {

  @Override
  public Response toResponse(Exception exception) {
    final String error = null == exception.getLocalizedMessage() ?
        exception.getClass().getSimpleName() : exception.getLocalizedMessage();
    log.severe(() -> format("oops %s: %s", exception.getClass().getName(), error));
    return Response.status(BAD_REQUEST)
                   .header(ACCEPT, APPLICATION_JSON)
                   .header(CONTENT_TYPE, APPLICATION_JSON)
                   .entity(singletonMap("error", error))
                   .build();
  }
}
