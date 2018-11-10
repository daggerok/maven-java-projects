package com.github.daggerok.actions;

import com.opensymphony.xwork2.ActionSupport;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.struts2.convention.annotation.*;

import static com.opensymphony.xwork2.Action.*;

@Slf4j
@Results({
    @Result(name = SUCCESS, location = "/"),
    @Result(name = INPUT, type = "redirect", location = "/"),
    @Result(name = ERROR, type = "redirect", location = "/"),
})
@Namespace("/")
@ResultPath("struts-default")
//@javax.enterprise.context.Dependent
public class FallbackPageAction extends ActionSupport {

  @Action("/*")
  @SneakyThrows
  public String execute() {
    return SUCCESS;
  }
}
