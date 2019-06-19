package com.jzq.server.demo01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ����������������
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
	 * ��������
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
	 * ���տͻ���
	 */
	private void receive() {
		try {
			Socket client=server.accept();

			byte[] data=new byte[20480];
			int len=client.getInputStream().read(data);
			//���տͻ��˵�������Ϣ
			String requestInfo=new String(data,0,len).trim();
			System.out.println(requestInfo);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * ֹͣ
	 */
	public void stop() {
		
	}
}