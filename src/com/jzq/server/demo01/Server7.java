package com.jzq.server.demo01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import com.jzq.util.CloseUtil;

/**
 * ������Ӧ
 * @author 25689
 *
 */
public class Server7 {
	private ServerSocket server;
	public static final String CRLF="\r\n";
	public static final String BLANK=" ";
	
	private boolean isShutDown=false;
	public static void main(String[] args) {
		Server7 server=new Server7();
		server.start();
	}
	/**
	 * ��������
	 */
	public void start() {
		start (8888);
	}
	/**
	 * ָ���˿ڵ���������
	 */
	public void start(int port) {
		try {
			server = new ServerSocket(port);
			this.receive();
		} catch (IOException e) {
			//e.printStackTrace();
			stop();
		}
	}
	/**
	 * ���տͻ���
	 */
	private void receive() {
		try {
			while(!isShutDown) {
			new Thread(new Dispatcher(server.accept())).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			stop();
		}

	}
	/**
	 * ֹͣ
	 */
	public void stop() {
		isShutDown=true;
		CloseUtil.closeAll(server);
	}
}