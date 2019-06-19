package com.jzq.reflect;

import com.jzq.server.demo03.Servlet;

/**
 * 创建实例 调用空构造器
 * @author 25689
 *
 */
public class Demo02 {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException  {
		Class<?> clz=Class.forName("com.jzq.server.demo03.LoginServlet");
		//调用空构造 确保空构造存在
		Servlet ser=(Servlet)clz.newInstance();
		//return ser;
	}
}
