package org.nextwin.thread;

import java.io.IOException;
import java.net.Socket;

import org.nextwin.protocol.Header;
import org.nextwin.protocol.Protocols;
import org.nextwin.protocol.TestPacket;
import org.nextwin.service.Service;
import org.nextwin.service.TestService;
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
	protected void service(Header header, byte[] data) throws IOException {
		Service service;
		switch (header.getMsgType()) {
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
	protected void enterServer() {
		System.out.println("Connected to main server");
	}

	@Override
	protected void exitServer() {
		System.out.println("Quit main server");
	}

}
