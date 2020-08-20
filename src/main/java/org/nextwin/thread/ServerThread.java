package org.nextwin.thread;

import java.io.IOException;
import java.net.Socket;

import org.nextwin.net.NetworkManager;
import org.nextwin.protocol.Header;

public abstract class ServerThread extends Thread {
	
	protected Socket socket;
	protected NetworkManager networkManager;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
		networkManager = NetworkManager.getInstance();
	}
	
	@Override
	public void run() {
		try {
			work();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(socket != null)
					socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void work() throws IOException {
		try {
			enterServer();
			networkManager.setSocket(socket);
			
			while(networkManager.isConnected()) {
				Header header = networkManager.receive();
				byte[] data = networkManager.receive(header.getLength());
				service(header, data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			exitServer();
			networkManager.close();
		}
	}
	
	protected abstract void service(Header header, byte[] data) throws IOException;
	
	protected abstract void enterServer();
	
	protected abstract void exitServer();
	
}
