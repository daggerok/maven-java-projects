package com.github.daggerok.data.common;

import lombok.*;
import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;
import static lombok.AccessLevel.PRIVATE;

@Data
@MappedSuperclass
@NoArgsConstructor
public class BaseEntity implements Serializable {

  @Id
  @Setter(PRIVATE)
  @GeneratedValue(generator = "uuid")
  @UuidGenerator(name = "uuid")
  String id;

  @Temporal(TIMESTAMP)
  @Column(name = "created_at")
  Date createdAt;

  @Temporal(TIMESTAMP)
  @Column(name = "updated_at")
  Date updatedAt;

  @PrePersist
  public void onCreate() {
    final Date now = new Date();
    setCreatedAt(now);
    setUpdatedAt(now);
  }

  @PostUpdate
  public void onUpdate() {
    setUpdatedAt(new Date());
  }
}
