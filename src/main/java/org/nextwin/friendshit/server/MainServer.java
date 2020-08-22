package org.nextwin.friendshit.server;

import java.net.Socket;

import org.nextwin.friendshit.thread.MainServerThread;
import org.nextwin.net.NetworkManager;
import org.nextwin.server.Server;
import org.nextwin.thread.ServerThread;

public class MainServer extends Server {

	public static void main(String[] args) {
		Server server = new MainServer();
		server.go();
	}
	
	@Override
	protected ServerThread createServerThread(Socket socket) {
		return new MainServerThread(socket);
	}

	@Override
	protected void onTerminate() {
		System.out.println("Main server terminated");	
	}

	@Override
	protected int getPort() {
		return NetworkManager.MAIN_PORT;
	}
	
}
