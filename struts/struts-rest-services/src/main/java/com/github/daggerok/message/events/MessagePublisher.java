package com.github.daggerok.message.events;

import com.github.daggerok.message.data.Message;
import lombok.extern.log4j.Log4j2;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.UUID;

@Log4j2
@Stateless
public class MessagePublisher {

  @Inject
  Event<MessageCreatedEvent> events;

  public MessageCreatedEvent publish(String messageBody) {
    final Message message = Message.of(UUID.randomUUID().toString(), messageBody);
    final MessageCreatedEvent event = MessageCreatedEvent.of(message);
    log.info("publishing: {}", event);
    events.fire(event);
    return event;
  }
}
