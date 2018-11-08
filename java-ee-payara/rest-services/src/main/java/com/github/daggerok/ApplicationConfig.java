package com.github.daggerok;

import com.github.daggerok.interceptors.LoggerInterceptor;
import com.github.daggerok.providers.AuthFilterProvider;
import com.github.daggerok.providers.CorsFilterProvider;
import com.github.daggerok.providers.ExceptionMapperProvider;
import com.github.daggerok.providers.JacksonProvider;
import com.github.daggerok.resources.CustomerResource;
import io.vavr.collection.List;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationScoped
@ApplicationPath("")
public class ApplicationConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    return List.of(CustomerResource.class,
                   JacksonProvider.class,
                   JacksonJsonProvider.class,
                   AuthFilterProvider.class,
                   CorsFilterProvider.class,
                   LoggerInterceptor.class,
                   ExceptionMapperProvider.class)
               .toJavaSet();
  }
}
