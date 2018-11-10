package com.github.daggerok.message.data;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "allOf")
@RequiredArgsConstructor(staticName = "of")
public class Message {
  UUID id = UUID.randomUUID();
  @NonNull String body;
}
