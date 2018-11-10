package com.github.daggerok;

//struts 2.3.36 (java 7):
//import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
// struts 2.5.18 (java 8+) :
import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;

import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
//@javax.enterprise.context.Dependent
public class RestApplication extends StrutsPrepareAndExecuteFilter { }
