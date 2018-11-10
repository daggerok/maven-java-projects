package com.github.daggerok.actions;

import com.opensymphony.xwork2.ActionSupport;
import lombok.SneakyThrows;
import org.apache.struts2.convention.annotation.*;

import static com.opensymphony.xwork2.Action.*;

@Results({
    @Result(name = SUCCESS, location = "/"),
    @Result(name = INPUT, type = "redirect", location = "/"),
    @Result(name = ERROR, type = "redirect", location = "/"),
})
@Namespace("/")
@ResultPath("struts-default")
//@javax.enterprise.context.Dependent
public class FallbackPage extends ActionSupport {

  @Action("/*")
  @SneakyThrows
  public String execute() {
    return SUCCESS;
  }
}
