package org.nextwin.friendshit.server;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import org.nextwin.friendshit.room.Room;
import org.nextwin.friendshit.thread.MainServerThread;
import org.nextwin.net.NetworkManager;
import org.nextwin.server.Server;
import org.nextwin.thread.ServerThread;

public class MainServer extends Server {
	
	public static ConcurrentHashMap<String, NetworkManager> connectedUsers = new ConcurrentHashMap<String, NetworkManager>();
	public static ConcurrentHashMap<Integer, Room> rooms = new ConcurrentHashMap<Integer, Room>();
	public static int roomId = 0;

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
