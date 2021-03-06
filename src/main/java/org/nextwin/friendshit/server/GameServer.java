package org.nextwin.friendshit.server;

import java.net.Socket;

import org.nextwin.friendshit.thread.GameServerThread;
import org.nextwin.net.NetworkManager;
import org.nextwin.server.Server;
import org.nextwin.thread.ServerThread;

public class GameServer extends Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	protected ServerThread createServerThread(Socket socket) {
		return new GameServerThread(socket);
	}

	@Override
	protected void onTerminate() {
		System.out.println("Game server terminated");		
	}

	/**
	 * 계속해서 포트 증가
	 */
	@Override
	protected int getPort() {
		return NetworkManager.GAME_PORT;
	}

}
