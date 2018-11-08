package com.github.daggerok.resources;

import io.vavr.collection.HashMap;
import lombok.SneakyThrows;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("")
@ApplicationScoped
@Produces(APPLICATION_JSON)
public class CustomerResource {

  @Resource(lookup = "java:global/ServicesDS")
  DataSource dataSource;

  @GET
  @Path("json")
  @SneakyThrows
  public JsonObject getJsonObject(@QueryParam("page") int page,
                                  @QueryParam("size") Integer size) {

    return Json.createObjectBuilder()
               .add("hello", "world")
               .add("page", page)
               .add("size", size)
               .add("url", dataSource.getConnection().getMetaData().getURL())
               .build();
  }

  @GET
  @Path("resp")
  @SneakyThrows
  public Response getResponse(@QueryParam("page") int page,
                              @QueryParam("size") int size) {

    return Response.ok(Json.createObjectBuilder()
                           .add("hello", "world")
                           .add("page", page)
                           .add("size", size)
                           .add("url", dataSource.getConnection().getMetaData().getURL())
                           .build())
                   .build();
  }

  @GET
  @SneakyThrows
  public Map getMap(@QueryParam("page") int page,
                    @QueryParam("size") Integer size) {

    return HashMap.of("hello", "world",
                      "page", page,
                      "size", size,
                      "date", new Date(),
                      "local", LocalDateTime.now(),
                      "zoned", ZonedDateTime.now(),
                      "instant", Instant.now(),
                      "url", dataSource.getConnection().getMetaData().getURL())
                  .toJavaMap();
  }
}
