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
import java.util.UUID;

import static com.opensymphony.xwork2.Action.*;
import static javax.ws.rs.core.HttpHeaders.LOCATION;

@Results({
    @Result(name = SUCCESS, type = "json"),
    @Result(name = INPUT, type = "json"),
    @Result(name = ERROR, type = "json")
})
@Result(type = "json")
@Namespace("/api/message")
@ParentPackage("json-default")
//@javax.enterprise.context.Dependent
@InterceptorRef(value = "json")
public class SaveMessageAction extends ActionSupport {

  @Inject
  MessagePublisher messagePublisher;

  @Inject
  Hateoas hateoas;

  @Setter
  String message;

  @Action("/api/message/save")
  public void save() throws Exception {
    final MessageCreatedEvent event = messagePublisher.publish(message);
    final UUID uuid = event.getMessage().getId();
    final HttpServletRequest request = ServletActionContext.getRequest();
    final String location = hateoas.linkTo(request, uuid.toString());
    ServletActionContext.getResponse().setHeader(LOCATION, location);
  }
}
