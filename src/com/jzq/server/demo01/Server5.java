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
public class Server5 {
	private ServerSocket server;
	public static final String CRLF="\r\n";
	public static final String BLANK=" ";
	public static void main(String[] args) {
		Server5 server=new Server5();
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
			//请求
			Request req=new Request(client.getInputStream());
			
			//响应			
			Response rep=new Response(client.getOutputStream());
			rep.println("<html><head><title>HTTP响应示例</title>");
			rep.println("</head><body>");
			rep.println("欢迎：").println(req.getParameter("uname")).println("回来");
			rep.println("</body></html>");
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