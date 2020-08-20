package org.nextwin.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.nextwin.thread.MainServerThread;
import org.nextwin.thread.ServerThread;

public class MainServer {

	private static final int PORT = 8899;
	private static final String IP = "localhost";
	
	public void go() {
		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(IP, PORT));
			
			while(true) {
				socket = serverSocket.accept();
				ServerThread serverThread = new MainServerThread(socket);
				serverThread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(socket != null)
					socket.close();
				if(serverSocket != null)
					serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Main server terminated");
		}
	}

	public static void main(String[] args) {
		System.out.println("Main server start");
		MainServer server = new MainServer();
		server.go();
	}

}
