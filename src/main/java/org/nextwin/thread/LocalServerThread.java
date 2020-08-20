package org.nextwin.thread;

import java.io.IOException;
import java.net.Socket;

import org.nextwin.protocol.Header;
import org.nextwin.service.Service;

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
	protected void service(Header header, byte[] data) throws IOException {
		Service service;
		switch (header.getMsgType()) {
		
		}
	}

	@Override
	protected void enterServer() {
		System.out.println("Connected to local server");
	}

	@Override
	protected void exitServer() {
		System.out.println("Quit local server");
	}

}
