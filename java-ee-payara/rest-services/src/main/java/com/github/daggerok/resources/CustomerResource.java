package com.github.daggerok.resources;

import io.vavr.collection.HashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("")
@ApplicationScoped
@Produces(APPLICATION_JSON)
public class CustomerResource {

  @GET
  @Path("json")
  public JsonObject getJsonObject(@QueryParam("page") int page,
                                  @QueryParam("size") int size) {

    return Json.createObjectBuilder()
               .add("hello", "world")
               .add("page", page)
               .add("size", size)
               .build();
  }

  @GET
  @Path("resp")
  public Response getResponse(@QueryParam("page") int page,
                              @QueryParam("size") int size) {

    return Response.ok(Json.createObjectBuilder()
                           .add("hello", "world")
                           .add("page", page)
                           .add("size", size)
                           .build())
                   .build();
  }

  @GET
  public Map getMap(@QueryParam("page") int page,
                    @QueryParam("size") int size) {

    return HashMap.of("hello", "world",
                      "page", page,
                      "size", size)
                  .toJavaMap();
  }
}
