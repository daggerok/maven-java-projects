package com.github.daggerok.actions.api.message;

import com.github.daggerok.message.data.Message;
import com.github.daggerok.message.data.MessageRepository;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import org.apache.struts2.convention.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Result(type = "json")
@Namespace("/api/message")
@ParentPackage("json-default")
//@InterceptorRef(value = "json")
//@javax.enterprise.context.Dependent
public class All extends ActionSupport {

  @Inject
  MessageRepository messageRepository;

  @Getter
  List<Message> message = new ArrayList<>();

  @Override
  @Actions({
      @Action("all"),
      @Action("*"),
  })
  public String execute() throws Exception {
    message = messageRepository.findAll();
    return SUCCESS;
  }
}
