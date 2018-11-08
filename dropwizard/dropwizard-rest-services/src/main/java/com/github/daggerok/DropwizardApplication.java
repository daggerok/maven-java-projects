package com.github.daggerok;

import com.github.daggerok.data.customer.CustomerDAO;
import com.github.daggerok.data.customer.CustomerEntity;
import com.github.daggerok.providers.AuthFilterProvider;
import com.github.daggerok.providers.CorsFilterProvider;
import com.github.daggerok.providers.ExceptionMapperProvider;
import com.github.daggerok.providers.JacksonProvider;
import com.github.daggerok.resources.CustomerResource;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.SneakyThrows;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.*;

public class DropwizardApplication extends Application<DropwizardConfig> {

  private final HibernateBundle<DropwizardConfig> hibernate =
      new HibernateBundle<DropwizardConfig>(CustomerEntity.class) {
        @Override
        public PooledDataSourceFactory getDataSourceFactory(DropwizardConfig configuration) {
          return configuration.getDataSourceFactory();
        }
      };

  @SneakyThrows
  public static void main(String[] args) {
    new DropwizardApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<DropwizardConfig> bootstrap) {
    bootstrap.addBundle(hibernate);
  }

  @Override
  public void run(DropwizardConfig configuration, Environment environment) throws Exception {

    environment.getObjectMapper().configure(FAIL_ON_EMPTY_BEANS, false);
    environment.getObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    environment.getObjectMapper().configure(WRITE_DATES_AS_TIMESTAMPS, false);
    environment.getObjectMapper().configure(FAIL_ON_EMPTY_BEANS, false);
    environment.getObjectMapper().enable(INDENT_OUTPUT);
    environment.getObjectMapper().setSerializationInclusion(NON_NULL);

    environment.jersey().register(JacksonProvider.class);
    environment.jersey().register(ExceptionMapperProvider.class);
    environment.jersey().register(AuthFilterProvider.class);
    environment.jersey().register(CorsFilterProvider.class);

    final CustomerDAO customerDAO = new CustomerDAO(hibernate.getSessionFactory());
    environment.jersey().register(customerDAO);

    final CustomerResource customerResource = new CustomerResource(customerDAO);
    environment.jersey().register(customerResource);
  }
}
