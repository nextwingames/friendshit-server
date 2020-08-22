package org.nextwin.friendshit.thread;

import java.io.IOException;
import java.net.Socket;

import org.nextwin.friendshit.protocol.Protocols;
import org.nextwin.friendshit.protocol.TestPacket;
import org.nextwin.friendshit.service.TestService;
import org.nextwin.protocol.Header;
import org.nextwin.service.Service;
import org.nextwin.thread.ServerThread;
import org.nextwin.util.JsonManager;

public class MainServerThread extends ServerThread {

	/** 
	 * 메인 서버 작업 쓰레드
	 */
	public MainServerThread(Socket socket) {
		super(socket);
		System.out.println("Main server thread created");
	}
	
	/**
	 * 메인 서버 작업
	 */
	@Override
	protected void service(int msgType, byte[] data) throws IOException {
		Service service;
		switch (msgType) {
		case Protocols.TEST:
			TestPacket packet = (TestPacket)JsonManager.bytesToObject(data, TestPacket.class);
			service = new TestService((TestPacket)packet);
			service.execute();
			break;

		default:
			break;
		}
	}

	@Override
	protected void onEnterServer() {
		System.out.println("Connect to main server");
	}

	@Override
	protected void onCloseServer() {
		// TODO Auto-generated method stub
		
	}

}
