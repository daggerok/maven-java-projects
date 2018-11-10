package com.github.daggerok.message.events;

import com.github.daggerok.message.data.MessageRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class MessageListener {

  @Inject
  MessageRepository messageRepository;

  public void on(@Observes MessageCreatedEvent event) {
    log.info("received: {}", event);
    messageRepository.save(event.getMessage());
  }
}
