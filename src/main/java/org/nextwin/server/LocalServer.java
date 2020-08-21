package org.nextwin.server;

import java.net.Socket;

import org.nextwin.net.NetworkManager;
import org.nextwin.thread.LocalServerThread;
import org.nextwin.thread.ServerThread;

public class LocalServer extends Server {

	public static void main(String[] args) {
		Server server = new LocalServer();
		server.go();
	}

	@Override
	protected ServerThread createServerThread(Socket socket) {
		return new LocalServerThread(socket);
	}

	@Override
	protected void onTerminate() {
		System.out.println("Local server terminated");
	}

	@Override
	protected int getPort() {
		return NetworkManager.LOCAL_PORT;
	}
}
