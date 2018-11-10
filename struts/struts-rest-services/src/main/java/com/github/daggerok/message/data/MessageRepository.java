package com.github.daggerok.message.data;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static javax.ejb.ConcurrencyManagementType.BEAN;

@Startup
@Singleton
@ApplicationScoped
@ConcurrencyManagement(BEAN)
public class MessageRepository {

  ConcurrentHashMap<String, String> db;

  @PostConstruct
  public void init() {
    db = new ConcurrentHashMap<>();
  }

  public void save(Message message) {
    final String body = message.getBody();
    if (body == null || body.trim().isEmpty())
      throw new IllegalStateException("message.null.or.empty");
    final UUID id = message.getId() == null
        ? UUID.randomUUID() : message.getId();
    db.put(id.toString(), body);
  }

  public List<Message> findAll() {
    return db.entrySet()
             .stream()
             .filter(e -> nonNull(e))
             .filter(e -> nonNull(e.getKey()))
             .filter(e -> nonNull(e.getValue()))
             .map(e -> Message.allOf(UUID.fromString(e.getKey()), e.getValue()))
             .collect(Collectors.toList());
  }
}
