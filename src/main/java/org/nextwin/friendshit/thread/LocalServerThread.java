package org.nextwin.friendshit.thread;

import java.io.IOException;
import java.net.Socket;

import org.nextwin.service.Service;
import org.nextwin.thread.ServerThread;

/**
 * DB를 사용하지 않는 로컬 서버
 * @author hyeonQyu
 *
 */
public class LocalServerThread extends ServerThread {
	
	public LocalServerThread(Socket socket) {
		super(socket);
		System.out.println("Local server thread created");
	}

	@Override
	protected void service(int msgType, byte[] data) throws IOException {
		Service service;
		switch (msgType) {
		
		}
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
