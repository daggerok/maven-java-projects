package com.github.daggerok.data.customer;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDAO extends AbstractDAO<CustomerEntity> {
  /**
   * Creates a new DAO with a given session provider.
   *
   * @param sessionFactory a session provider
   */
  public CustomerDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public List<CustomerEntity> findAll() {
    final Query<CustomerEntity> query = namedQuery("CustomerEntity.findAll");
    return list(query);
  }

  public Long count() {
    final Query query = namedQuery("CustomerEntity.count");
    return Long.class.cast(query.uniqueResult());
  }

  public CustomerEntity findById(String id) {
    return get(id);
  }

  public CustomerEntity save(String data) {
    return persist(new CustomerEntity(data));
  }
}
