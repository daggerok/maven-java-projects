package com.github.daggerok.actions.api.message;

import com.github.daggerok.message.data.Message;
import com.github.daggerok.message.data.MessageRepository;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import org.apache.struts2.convention.annotation.*;

import javax.inject.Inject;
import java.util.List;

import static com.opensymphony.xwork2.Action.*;

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
public class FindAllMessagesAction extends ActionSupport {

  @Inject
  MessageRepository messageRepository;

  @Getter
  List<Message> message;

  @Override
  @Action("/api/message/find-all")
  public String execute() throws Exception {
    message = messageRepository.findAll();
    return SUCCESS;
  }
}
