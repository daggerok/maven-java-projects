package com.github.daggerok.resources;

import com.github.daggerok.data.customer.CustomerEntity;
import com.github.daggerok.interceptors.LoggerInterceptor;
import io.vavr.collection.HashMap;
import lombok.SneakyThrows;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.interceptor.Interceptors;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Interceptors(LoggerInterceptor.class)
public class CustomerResource {

  @Resource(lookup = "java:global/ServicesDS")
  DataSource dataSource;

  @PersistenceContext
  EntityManager em;

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
                      "local-date", LocalDate.now(),
                      "local-time", LocalTime.now(),
//                      "local", LocalDateTime.now(),
//                      "instant", Instant.now(),
                      "em.find", em.createNamedQuery("CustomerEntity.findAll", CustomerEntity.class)
                                   .getResultList(),
                      "em.count", em.createNamedQuery("CustomerEntity.count", Long.class)
                                    .getSingleResult(),
                      "url", dataSource.getConnection().getMetaData().getURL(),
                      "zoned", ZonedDateTime.now())
                  .toJavaMap();
  }

  @POST
  @Transactional
  @Consumes(APPLICATION_JSON)
  public CustomerEntity save(JsonObject json) {
    final String data = json.getString("data", "");
    final CustomerEntity customerEntity = new CustomerEntity(data);
    em.persist(customerEntity);
    return customerEntity;
  }
}
