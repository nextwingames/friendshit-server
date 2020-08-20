package org.nextwin.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.nextwin.thread.ServerThread;

public abstract class Server {
	
	private int port;
	protected static final String IP = "localhost";
	
	public void go() {
		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			serverSocket = new ServerSocket();
			port = getPort();
			serverSocket.bind(new InetSocketAddress(IP, port));
			
			while(true) {
				socket = serverSocket.accept();
				ServerThread serverThread = createServerThread(socket);
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
			onTerminate();
		}
	}
	
	protected abstract int getPort();
	
	protected abstract ServerThread createServerThread(Socket socket);
	
	protected abstract void onTerminate();
	
}
