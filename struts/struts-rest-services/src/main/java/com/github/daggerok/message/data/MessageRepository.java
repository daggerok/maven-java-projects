package com.github.daggerok.message.data;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static com.github.daggerok.message.data.MessageEntity.FIND_ALL;
import static com.github.daggerok.message.data.MessageEntity.FIND_BY_ID;
import static java.util.stream.Collectors.toList;

@Stateless
public class MessageRepository {

  @PersistenceContext
  EntityManager em;

  public void save(Message message) {
    final MessageEntity entity = MessageEntity.of(message.getId(), message.getBody());
    em.persist(entity);
  }

  public List<Message> findAll() {
    return em.createNamedQuery(FIND_ALL, MessageEntity.class)
             .getResultList()
             .stream()
             .map(MessageRepository::messageMapper)
             .collect(toList())
        ;
  }

  public Optional<Message> findById(String id) {
    return em.createNamedQuery(FIND_BY_ID, MessageEntity.class)
             .setParameter("id", id)
             .getResultList()
             .stream()
             .map(MessageRepository::messageMapper)
             .findFirst();
  }

  private static Message messageMapper(MessageEntity entity) {
    return Message.of(entity.getId(), entity.getBody());
  }
}
