package com.github.daggerok.message.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import static com.github.daggerok.message.data.MessageEntity.FIND_ALL;
import static com.github.daggerok.message.data.MessageEntity.FIND_BY_ID;
import static lombok.AccessLevel.PACKAGE;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@NamedQueries({
    @NamedQuery(name = FIND_ALL, query = "SELECT rm FROM MessageEntity rm"),
    @NamedQuery(name = FIND_BY_ID, query = "SELECT rm FROM MessageEntity rm WHERE rm.id = :id"),
})
public class MessageEntity {

  public static final String FIND_ALL = "MessageEntity.findAll";
  public static final String FIND_BY_ID = "MessageEntity.findById";

  @Id
  @Setter(PACKAGE)
  String id;
  String body;
}
