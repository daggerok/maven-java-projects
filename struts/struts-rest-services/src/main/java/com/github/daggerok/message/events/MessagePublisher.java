package com.github.daggerok.message.events;

import com.github.daggerok.message.data.Message;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Slf4j
@Stateless
public class MessagePublisher {

  @Inject
  Event<MessageCreatedEvent> events;

  public MessageCreatedEvent publish(String messageBody) {
    final Message message = Message.of(messageBody);
    final MessageCreatedEvent event = MessageCreatedEvent.of(message);
    log.info("publishing: {}", event);
    events.fire(event);
    return event;
  }
}
