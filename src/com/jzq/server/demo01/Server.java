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
public class Server {
	private ServerSocket server;
	public static void main(String[] args) {
		Server server=new Server();
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
			StringBuilder sb=new StringBuilder();
			String msg=null;//���տͻ��˵�������Ϣ
			
			BufferedReader br=new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			while((msg=br.readLine()).length()>0) {
				sb.append(msg);
				sb.append("\r\n");
				if(msg==null) {
					break;
				}
			}
			String requestInfo=sb.toString().trim();
			System.out.println(requestInfo);
			System.out.println(sb.toString());
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