package com.github.daggerok.actions.api.message;

import com.github.daggerok.message.events.MessageCreatedEvent;
import com.github.daggerok.message.events.MessagePublisher;
import com.github.daggerok.rest.Hateoas;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static javax.ws.rs.core.HttpHeaders.LOCATION;

@Result(type = "json")
@Namespace("/api/message")
@ParentPackage("json-default")
@InterceptorRef(value = "json")
//@javax.enterprise.context.Dependent
public class New extends ActionSupport {

  @Inject
  MessagePublisher messagePublisher;

  @Inject
  Hateoas hateoas;

  @Setter
  String message;

  @Action("new")
  public void save() throws Exception {
    final MessageCreatedEvent event = messagePublisher.publish(message);
    final String id = event.getMessage().getId();
    final HttpServletRequest request = ServletActionContext.getRequest();
    final String location = hateoas.linkTo(request, "api", "message", "one", id);
    final HttpServletResponse response = ServletActionContext.getResponse();
    response.setHeader(LOCATION, location);
    response.setStatus(201);
  }
}
