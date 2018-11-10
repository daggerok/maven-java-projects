package com.github.daggerok.actions;

import com.opensymphony.xwork2.ActionSupport;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.struts2.convention.annotation.*;

import static com.opensymphony.xwork2.Action.*;

@Slf4j
@Results({
    @Result(name = SUCCESS, type = "dispatcher", location = "/"),
    @Result(name = INPUT, type = "dispatcher", location = "/"),
    @Result(name = ERROR, type = "dispatcher", location = "/"),
})
@Namespace("/")
@ResultPath("struts-default")
//@javax.enterprise.context.Dependent
public class IndexPageAction extends ActionSupport {

  @Action("/")
  @SneakyThrows
  public String execute() {
    return SUCCESS;
  }
}
