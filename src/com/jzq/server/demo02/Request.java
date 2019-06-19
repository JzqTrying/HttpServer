package com.jzq.server.demo02;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * ��װrequest
 * @author 25689
 *
 */
public class Request {
	//����ʽ
	private String method;
	//������Դ
	private String url;
	//�������
	private Map<String,List<String>> parameterMapValues;
	//�ڲ�
	public static final String CRLF="\r\n";
	private InputStream is;
	private String requestInfo;
	
	public Request() {
		method="";
		url="";
		parameterMapValues=new HashMap<String,List<String>>();
		requestInfo="";
	}	
	public Request(InputStream is) {
		this();
		this.is=is;
		int len;
		try {
			byte[] data=new byte[20480];
			len = is.read(data);
				
			requestInfo=new String(data,0,len);
		} catch (IOException e) {
			return;
		}
		//����������Ϣ
		parseRequestInfo();
	}
	/**
	 * ����������Ϣ
	 */
	private void parseRequestInfo() {
		if(requestInfo==null||requestInfo.trim().equals("")) {
			return;
		}
	/**
	 * ===============================================
	 * ����Ϣ�����зֽ�������������get���ܴ��ڣ�������ʽ������·��
	 * �磺GET /index.html?name=123&pwd=5456 HTTP/1.1
	 * 
	 * ���Ϊpost��ʽ��������������� ���������
	 * ===============================================
	 */
	String paramString="";//�����������
	//1����ȡ����ʽ
	String firstLine=requestInfo.substring(0, requestInfo.indexOf(CRLF));
	int idx=requestInfo.indexOf("/");// /��λ��
	this.method=firstLine.substring(0,idx).trim();
	String urlStr=firstLine.substring(idx,firstLine.indexOf("HTTP/")).trim();
	if(this.method.equalsIgnoreCase("post")) {
		this.url=urlStr;
		paramString=requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
	}else if(this.method.equals("get")) {
		if(urlStr.contains("?")) {//�Ƿ���ڲ���
			String[] urlArray=urlStr.split("\\?");
			this.url=urlArray[0];
			paramString=urlArray[1];//�����������
		}else {
			this.url=urlStr;
		}
	}
	
	//2�������������װ��Map��
	//�������������
	if(paramString.equals("")) {
		return;
	}else {
	paramParams(paramString);
	}
	}
	private void paramParams(String paramString) {
		//�ָ� ���ַ���ת������
		StringTokenizer token=new StringTokenizer(paramString, "&");
		while(token.hasMoreTokens()) {
			String keyValue=token.nextToken();
			String[] keyValues=keyValue.split("=");
			if(keyValues.length==1){//�ַ���ֻ��=ǰ�档��=����Ϊnull
				keyValues=Arrays.copyOf(keyValues, 2);
				keyValues[1]=null;
			}
			
			String key=keyValues[0];
			String value=null==keyValues[1]?null:decode(keyValues[1].trim(),"utf-8");
			//ת����Map
			if(!parameterMapValues.containsKey(key)){
				parameterMapValues.put(key,new ArrayList<String>());
			}
			
			List<String> values=parameterMapValues.get(key);
			values.add(value);
		}
	}
	/**
	 * ����������⣬java.net�Դ�����
	 * @param values
	 * @param code
	 * @return
	 */
	private String decode(String values,String code) {
		try {
			return java.net.URLDecoder.decode(values, code);
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		}
		return null;
	}
	/**
	 * ����ҳ���name��ȡ���ֵ
	 * @param args
	 */
	public String[] getParameterValues(String name) {
		List<String> values=null;
		if((values=parameterMapValues.get(name))==null) {
			return null;
		}else {
			return values.toArray(new String[0]);
		}
	}
	/**
	 * ����ҳ���name��ȡ����ֵ
	 * @param args
	 */
	public String getParameter(String name) {
		String[] values=getParameterValues(name);
		if(values==null) {
			return null;
		}else {
			return values[0];
		}
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
