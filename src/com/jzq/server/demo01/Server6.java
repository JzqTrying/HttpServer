package com.jzq.server.demo01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 请求并响应
 * @author 25689
 *
 */
public class Server6 {
	private ServerSocket server;
	public static final String CRLF="\r\n";
	public static final String BLANK=" ";
	public static void main(String[] args) {
		Server6 server=new Server6();
		server.start();
	}
	/**
	 * 启动方法
	 */
	public void start() {
		try {
			server = new ServerSocket(8888);
			this.receive();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 接收客户端
	 */
	private void receive() {
		try {
			Socket client=server.accept();
			Servlet serv=new Servlet();
			Request req=new Request(client.getInputStream());
			Response rep=new Response(client.getOutputStream());
			serv.service(req,rep);
			rep.pushToClient(200);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 停止
	 */
	public void stop() {
		
	}
}