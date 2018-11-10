package com.github.daggerok.actions.api;

import com.github.daggerok.rest.Hateoas;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.opensymphony.xwork2.Action.*;

@Slf4j
@Results({
    @Result(name = SUCCESS, type = "json"),
    @Result(name = INPUT, type = "json"),
    @Result(name = ERROR, type = "json")
})
@Namespace("/")
@ParentPackage("json-default")
//@javax.enterprise.context.Dependent
public class AvailableApiAction extends ActionSupport {

  @Inject
  Hateoas hateoas;

  @Getter
  Map<String, String> api = new LinkedHashMap<>();

  @Actions({
      @Action("/api"),
      @Action("/api/*"),
  })
  public String execute() {
    final HttpServletRequest request = ServletActionContext.getRequest();
    api.put("_self", hateoas.linkTo(request));
    api.put("health", hateoas.linkTo(request, "health"));
    api.put("fail", hateoas.linkTo(request, "fail"));
    api.put("save", hateoas.linkTo(request, "save"));
    return SUCCESS;
  }
}
