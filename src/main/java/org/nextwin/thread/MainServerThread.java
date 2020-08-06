package org.nextwin.thread;

import java.io.IOException;
import java.net.Socket;

import org.nextwin.protocol.TestProtocol;
import org.nextwin.util.BitConverter;
import org.nextwin.util.JsonManager;

public class MainServerThread extends ServerThread {

	/** 
	 * 메인 서버 작업 쓰레드
	 */
	public MainServerThread(Socket socket) {
		super(socket);
	}
	
	/**
	 * 메인 서버 작업
	 */
	@Override
	protected void work() throws IOException {
		byte[] data = networkManager.receive();
		TestProtocol testProtocol = (TestProtocol)JsonManager.bytesToObject(data, TestProtocol.class);
		System.out.println("num: " + testProtocol.getNum() + ", str: " + testProtocol.getStr());
		//networkManager.send(testProtocol);
	}
	
	@Override
	protected void enterServer() {
		System.out.println("접속");
	}

	@Override
	protected void exitServer() {
		System.out.println("퇴장");
	}

}
