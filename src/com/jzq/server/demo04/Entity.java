package com.jzq.server.demo04;
/**
 * <servlet>
		<servlet-name>login</servlet-name>
		<servlet-class>com.jzq.server.demo04.LoginServlet</servlet-class>
	</servlet>
 * @author 25689
 *
 */
public class Entity {
	private String name;
	private String cls;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	
}
