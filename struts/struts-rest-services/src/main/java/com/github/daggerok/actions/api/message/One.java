package com.github.daggerok.actions.api.message;

import com.github.daggerok.message.data.Message;
import com.github.daggerok.message.data.MessageRepository;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.*;

import javax.inject.Inject;
import java.util.Optional;

import static com.opensymphony.xwork2.Action.*;

@Results({
    @Result(name = SUCCESS, type = "json"),
    @Result(name = INPUT, type = "json"),
    @Result(name = ERROR, type = "json", params = {
        "statusCode", "404",
        "errorCode", "404123",
        "ignoreHierarchy", "false",
        "includeProperties", ".*",
    })
})
@ExceptionMappings({
    @ExceptionMapping(
        exception = "java.lang.Exception",
        params = { "param1", "val1" },
        result = SUCCESS
    ),
})
@Namespace("/api/message")
@ParentPackage("json-default")
@InterceptorRef(value = "json")
//@javax.enterprise.context.Dependent
public class One extends ActionSupport {

  @Inject
  MessageRepository messageRepository;

  @Setter
  String id;

  @Getter
  Message message;

  @Override
  @Action("one")
  public String execute() throws Exception {
    final Optional<Message> maybeMessage = messageRepository.findById(id);
    if (!maybeMessage.isPresent()) {
      addFieldError("error", "message with '" + id + "' id not found.");
      return ERROR;
    }
    message = maybeMessage.get();
    return SUCCESS;
  }
}
