package com.jzq.server.demo04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WebHandler extends DefaultHandler{
	private List<Entity> entityList;
	private Entity entity;
	private Mapping mapping;
	private List<Mapping> mappingList;
	private String beginTag;
	
	private boolean isMap;
	@Override
	public void startDocument() throws SAXException {
		//�ĵ��Ľ�����ʼ
		entityList=new ArrayList<Entity>();
		mappingList=new ArrayList<Mapping>();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// ��ʼԪ��
		if(null!=qName) {
			beginTag=qName;
			if(qName.equals("servlet")) {
				isMap=false;
				entity=new Entity();
			}else if(qName.equals("servlet-mapping")) {
				isMap=true;
				mapping=new Mapping();
			}
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		//��������
		if(null!=beginTag) {
			String str=new String(ch,start,length);
			if(isMap) {
			if(beginTag.equals("servlet-name")){
				mapping.setName(str);
			}else if(beginTag.equals("url-pattern")) {
				mapping.getUrlPattern().add(str);
			}
			}else {
				if(beginTag.equals("servlet-name")){
					entity.setName(str);
				}else if(beginTag.equals("servlet-class")) {
					entity.setCls(str);
				}		
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// ����Ԫ��
		if(null!=qName) {
			if(qName.equals("servlet")) {
				entityList.add(entity);
			}else if(qName.equals("servlet-mapping")) {
				mappingList.add(mapping);
			}
		}
		beginTag=null;
	}
	
	@Override
	public void endDocument() throws SAXException {
		//�ĵ���������
	}
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//��ȡ��������
		SAXParserFactory factory=SAXParserFactory.newInstance();
		//��ȡ������
		SAXParser sex=factory.newSAXParser();
		//ָ��xml+������
		WebHandler handler=new WebHandler();
		sex.parse(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("com/jzq/server/demo04/web.xml"), handler);
		System.out.println(handler.getEntityList());
		System.out.println(handler.getMappingList());
	}

	public List<Entity> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}

	public List<Mapping> getMappingList() {
		return mappingList;
	}

	public void setMappingList(List<Mapping> mappingList) {
		this.mappingList = mappingList;
	}
	
	
}
