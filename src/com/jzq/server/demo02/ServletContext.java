package com.jzq.server.demo02;

import java.util.HashMap;
import java.util.Map;

/**
 * ������
 * @author 25689
 *
 */
public class ServletContext {
	//Ϊÿһ��servletȡһ������
	//login-->loginServlet
	private Map<String,Servlet> servlet;
	//url-->login
	//   /log-->login
	//   /login-->login
	private Map<String,String> mapping;
	
	ServletContext(){
		servlet=new HashMap<String,Servlet>();
		mapping=new HashMap<String,String>();
	}
	
	public Map<String, Servlet> getServlet() {
		return servlet;
	}
	public void setServlet(Map<String, Servlet> servlet) {
		this.servlet = servlet;
	}
	public Map<String, String> getMapping() {
		return mapping;
	}
	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}
	
}
