package com.github.daggerok.data.customer;

import com.github.daggerok.data.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.PACKAGE;

@Data
@Entity
@NamedQueries({
    @NamedQuery(name = "CustomerEntity.findAll", query = "select ce        from CustomerEntity ce"),
    @NamedQuery(name = "CustomerEntity.count", query = "  select count(ce) from CustomerEntity ce")
})
@Table(name = "customers")
@NoArgsConstructor(access = PACKAGE)
@EqualsAndHashCode(callSuper = false)
public class CustomerEntity extends BaseEntity {

  public CustomerEntity(String data) {
    this.data = data;
  }

  enum Status {
    ACTIVE, INACTIVE;
  }

  @Enumerated(STRING)
  Status status;

  @Column(name = "some_data")
  String data;
}
