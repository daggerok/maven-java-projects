package com.github.daggerok.message.events;

import com.github.daggerok.message.data.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class MessageCreatedEvent {
  final Message message;
}
