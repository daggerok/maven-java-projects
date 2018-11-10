/*
package com.github.daggerok.actions.api.message;

import com.github.daggerok.message.data.Message;
import com.github.daggerok.message.data.MessageRepository;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import com.opensymphony.xwork2.conversion.impl.XWorkBasicConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.struts2.convention.annotation.*;

import javax.inject.Inject;

//@Results({
//    @Result(name = SUCCESS, type = "json"),
//    @Result(name = INPUT, type = "json"),
//    @Result(name = ERROR, type = "json")
//})
@Result(type = "json")
@ParentPackage("json-default")
//@InterceptorRef(value = "json")
@Namespace("/api/message/wtf")
//@javax.enterprise.context.Dependent
public class FuckIt extends ActionSupport {

  @Inject
  MessageRepository messageRepository;

  @Setter
  String id;

  @Getter
  Message message;

  @Getter
//  @TypeConversion(converter = "com.opensymphony.xwork2.conversion.impl.XWorkBasicConverter")
  String error;

  @Override
  @Action("{id}")
  public String execute() throws Exception {
    System.out.println("id: " + id);
    System.out.println("repo: " + messageRepository.findById(id));
    message = messageRepository.findById(id)
                               .orElseThrow(RuntimeException::new);
    System.out.println("message: " + message);
    return SUCCESS;
  }
}
*/
