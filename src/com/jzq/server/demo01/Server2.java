package com.jzq.server.demo01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务器并启动
 * @author 25689
 *
 */
public class Server2 {
	private ServerSocket server;
	public static void main(String[] args) {
		Server2 server=new Server2();
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

			byte[] data=new byte[20480];
			int len=client.getInputStream().read(data);
			//接收客户端的请求信息
			String requestInfo=new String(data,0,len).trim();
			System.out.println(requestInfo);

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