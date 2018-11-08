package com.github.daggerok.interceptors;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

@Log
public class LoggerInterceptor {

  @AroundInvoke
  public Object logAll(InvocationContext context) throws Exception {
    log.info("object: " + context.getTarget() + ", method: " + context.getMethod().getName());
    return context.proceed();
  }

  @PostConstruct
  public Object logCreate(InvocationContext context) throws Exception {
    System.out.println("create: " + context.getTarget().getClass().getName());
    return context.proceed();
  }
}
