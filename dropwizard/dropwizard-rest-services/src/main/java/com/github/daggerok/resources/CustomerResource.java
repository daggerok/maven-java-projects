package com.github.daggerok.resources;

import com.github.daggerok.data.customer.CustomerDAO;
import com.github.daggerok.data.customer.CustomerEntity;
import io.dropwizard.hibernate.UnitOfWork;
import io.vavr.collection.HashMap;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("")
@RequiredArgsConstructor
@Produces(APPLICATION_JSON)
public class CustomerResource {

  final CustomerDAO customerDAO;

  @GET
  @UnitOfWork
  public Map getMap(@QueryParam("page") int page,
                    @QueryParam("size") Integer size) {

    return HashMap.of("hello", "world",
                      "page", page,
                      "size", size,
                      "date", new Date(),
                      "local-date", LocalDate.now(),
                      "local-time", LocalTime.now(),
                      "instant", Instant.now(),
                      "em.find", customerDAO.findAll(),
                      "em.count", customerDAO.count(),
                      "zoned", ZonedDateTime.now())
                  .toJavaMap();
  }

  @POST
  @UnitOfWork
  @Consumes(APPLICATION_JSON)
  public CustomerEntity save(LinkedHashMap<String, String> json) {
    final String data = json.getOrDefault("data", "");
    return customerDAO.save(data);
  }
}
