package com.jzq.reflect;

import com.jzq.server.demo03.Servlet;

/**
 * ����ʵ�� ���ÿչ�����
 * @author 25689
 *
 */
public class Demo02 {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException  {
		Class<?> clz=Class.forName("com.jzq.server.demo03.LoginServlet");
		//���ÿչ��� ȷ���չ������
		Servlet ser=(Servlet)clz.newInstance();
		//return ser;
	}
}
