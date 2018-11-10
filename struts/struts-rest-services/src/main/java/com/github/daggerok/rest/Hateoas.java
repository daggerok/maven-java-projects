package com.github.daggerok.rest;

import lombok.SneakyThrows;

import javax.enterprise.context.Dependent;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;

@Dependent
public class Hateoas {

  @SneakyThrows
  public String linkTo(HttpServletRequest request, String... pathParts) {
    final URL url = new URL(request.getRequestURL().toString());
    final StringBuilder urlBuilder = new StringBuilder(url.getProtocol()).append("://")
                                                                         .append(url.getAuthority());
    for (String pathPart : pathParts)
      urlBuilder.append("/").append(pathPart);
    return urlBuilder.toString();
  }
}
