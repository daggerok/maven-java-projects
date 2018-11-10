package com.github.daggerok.actions.api.health;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.struts2.convention.annotation.*;

import static com.opensymphony.xwork2.Action.*;

@Slf4j
@Results({
    @Result(name = SUCCESS, type = "json"),
    @Result(name = INPUT, type = "json"),
    @Result(name = ERROR, type = "json")
})
@Namespace("/api")
@Result(type = "json")
@ParentPackage("json-default")
//@javax.enterprise.context.Dependent
public class HealthCheckAction extends ActionSupport {

  @Getter
  String status = "DOWN";

  @Override
  @Action("/api/health")
  public String execute() throws Exception {
    status = "UP";
    return SUCCESS;
  }

  @Action(
      value = "/api/fail",
      exceptionMappings = {
          @ExceptionMapping(exception = "java.lang.Throwable", result = ERROR)
      }
  )
  public String failImmediately() {
    if (true) throw new RuntimeException("should fail immediately");
    return ERROR;
  }
}
