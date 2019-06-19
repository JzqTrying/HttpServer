package com.jzq.server.demo04;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class WebApp {
	private static ServletContext context;
	static {
		try {
			//��ȡ��������
			SAXParserFactory factory=SAXParserFactory.newInstance();
			//��ȡ������
			SAXParser sex=factory.newSAXParser();
			//ָ��xml+������
			WebHandler handler=new WebHandler();
			sex.parse(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("com/jzq/server/demo04/web.xml"), handler);
			
			//��listת��map
			context=new ServletContext();
			
			Map<String,String> servlet=context.getServlet();
			//servlet-name��servlet-class
			for(Entity entity:handler.getEntityList()) {
				servlet.put(entity.getName(), entity.getCls());
			}
			
			//url-pattern servlet-name	
			Map<String,String> mapping=context.getMapping();
			for(Mapping mapp:handler.getMappingList()) {
				List<String> urls=mapp.getUrlPattern();
				for(String url:urls) {
					mapping.put(url, mapp.getName());
				}
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static Servlet getServlet(String url) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(null==url||(url=url.trim()).equals("")) {
			return null;
		}
		//�����ַ�����������
		
		//return context.getServlet().get(context.getMapping().get(url));
	String name=context.getServlet().get(context.getMapping().get(url));
	return (Servlet)Class.forName(name).newInstance();//ȷ���չ������
	}
}
