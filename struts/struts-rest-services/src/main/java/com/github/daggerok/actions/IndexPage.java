package com.github.daggerok.actions;

import com.opensymphony.xwork2.ActionSupport;
import lombok.SneakyThrows;
import org.apache.struts2.convention.annotation.*;

import static com.opensymphony.xwork2.Action.*;

@Results({
    @Result(name = SUCCESS, type = "dispatcher", location = "/"),
    @Result(name = INPUT, type = "dispatcher", location = "/"),
    @Result(name = ERROR, type = "dispatcher", location = "/"),
})
@Namespace("/")
@ResultPath("struts-default")
//@javax.enterprise.context.Dependent
public class IndexPage extends ActionSupport {

  @Actions({
      @Action("/"),
      @Action("/about"),
  })
  @SneakyThrows
  public String execute() {
    return SUCCESS;
  }
}
