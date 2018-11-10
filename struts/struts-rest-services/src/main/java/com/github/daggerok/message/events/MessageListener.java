package com.github.daggerok.message.events;

import com.github.daggerok.message.data.Message;
import com.github.daggerok.message.data.MessageRepository;
import lombok.extern.log4j.Log4j2;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Log4j2
@ApplicationScoped
public class MessageListener {

  @Inject
  MessageRepository messageRepository;

  public void on(@Observes MessageCreatedEvent event) {
    log.info("received: {}", event);
    final Message message = event.getMessage();
    messageRepository.save(message);
  }
}
