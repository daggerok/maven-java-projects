package com.github.daggerok;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
//@javax.enterprise.context.Dependent
public class RestApplication extends StrutsPrepareAndExecuteFilter {
}
