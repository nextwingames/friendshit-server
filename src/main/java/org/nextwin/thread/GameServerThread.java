package org.nextwin.thread;

import java.io.IOException;
import java.net.Socket;

public class GameServerThread extends ServerThread {

	public GameServerThread(Socket socket) {
		super(socket);
		System.out.println("Create game server thread");
	}

	/* 
	 * 게임 서버 작업
	 */
	@Override
	protected void service() throws IOException {
		
	}
	
	@Override
	protected void enterServer() {
				
	}

	@Override
	protected void exitServer() {
				
	}

}
