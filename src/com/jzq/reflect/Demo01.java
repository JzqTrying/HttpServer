package com.jzq.reflect;
/**
 * ��ȡ�ṹ��Ϣclass����
 * @author 25689
 *
 */
public class Demo01 {
	public static void main(String[] args) throws ClassNotFoundException {
		String str="abc";
		//class����
		//����.getclass()
		Class<?> clz=str.getClass();
		//��.class
		clz=String.class;
		//����·��
		clz=Class.forName("jzq.lang.String");
	}
}
