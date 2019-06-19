package com.jzq.reflect;
/**
 * 获取结构信息class对象
 * @author 25689
 *
 */
public class Demo01 {
	public static void main(String[] args) throws ClassNotFoundException {
		String str="abc";
		//class对象
		//对象.getclass()
		Class<?> clz=str.getClass();
		//类.class
		clz=String.class;
		//完整路径
		clz=Class.forName("jzq.lang.String");
	}
}
