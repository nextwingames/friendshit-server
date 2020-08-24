package org.nextwin.friendshit.thread;

import java.io.IOException;
import java.net.Socket;

import org.nextwin.thread.ServerThread;

public class GameServerThread extends ServerThread {

	public GameServerThread(Socket socket) {
		super(socket);
		System.out.println("Create game server thread");
	}

	/* 
	 * 게임 서버 작업
	 */
	@Override
	protected void service(int msgType, byte[] data) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onEnterServer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onExitServer() {
		// TODO Auto-generated method stub
		
	}

}
