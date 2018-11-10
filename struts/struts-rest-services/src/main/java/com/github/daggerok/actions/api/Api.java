package com.github.daggerok.actions.api;

import com.github.daggerok.rest.Hateoas;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.opensymphony.xwork2.Action.*;

@Log4j2
@Namespace("/api")
@Result(type = "json")
@ParentPackage("json-default")
//@javax.enterprise.context.Dependent
public class Api extends ActionSupport {

  @Inject
  Hateoas hateoas;

  @Getter
  Map<String, String> api = new LinkedHashMap<>();

  @Actions({
      @Action("/api"),
      @Action("/api*"),
      @Action("/api/*"),
  })
  public String execute() {
    final HttpServletRequest request = ServletActionContext.getRequest();
    api.put(" _self      ", hateoas.linkTo(request, "api"));
    api.put(" health     ", hateoas.linkTo(request, "api", "health"));
    api.put(" fail       ", hateoas.linkTo(request, "api", "fail"));
    api.put(" find all   ", hateoas.linkTo(request, "api", "message", "all"));
    api.put(" create new ", hateoas.linkTo(request, "api", "message", "new"));
    api.put(" find one   ", hateoas.linkTo(request, "api", "message", "one"));
    return SUCCESS;
  }
}
