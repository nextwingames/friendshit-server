package org.nextwin.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.nextwin.util.BitConverter;
import org.nextwin.util.JsonManager;

import com.fasterxml.jackson.core.JsonProcessingException;

public class NetworkManager {

	private static NetworkManager instance;
	
	private Socket socket;
	private OutputStream sender;
	private InputStream receiver;
		
	private NetworkManager() {}
	
	/**
	 * 싱글톤 객체 반환
	 * @return Singleton instance of NetworkManager
	 */
	public static NetworkManager getInstance() {
		if(instance == null)
			instance = new NetworkManager();
		return instance;
	}
	
	/**
	 * 소켓 설정 및 OutputStream, InputStream 생성
	 * @param socket
	 * @throws IOException
	 */
	public void setSocket(Socket socket) throws IOException {
		this.socket = socket;
		sender = socket.getOutputStream();
		receiver = socket.getInputStream();
	}
	
	/**
	 * 소켓 연결 확인
	 * @return socket.isConnected() && !socket.isClosed()
	 */
	public boolean isConnected() {
		return (socket.isConnected() && !socket.isClosed());
	}
	
	/**
	 * 데이터 수신
	 * @return received data, null when fail
	 * @throws IOException
	 */
	public byte[] receive() throws IOException {
		if(socket.isClosed())
			return null;
		
		// 현재는 50바이트 읽음
		byte[] data = new byte[50];
		if(receiver.read(data, 0, 50) == -1)
			return null;
		
		return data;
	}
	
	/**
	 * 데이터 전송
	 * @param value
	 * @return Sending data size
	 */
	public int send(int value) {
		byte[] data = BitConverter.intToBytes(value);
		return data.length;
	}
	
	/**
	 * 데이터 전송
	 * @param value
	 * @return Sending data size, Null when fail
	 */
	public int send(Object object) {
		byte[] data = null;
		
		try {
			data = JsonManager.objectToBytes(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		if(data == null)
			return 0;
		return data.length;
	}
	
	/**
	 * 소켓, Stream 닫음
	 */
	public void close() {
		try {
			if(socket != null)
				socket.close();
			if(sender != null)
				sender.close();
			if(receiver != null)
				receiver.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void checkSocketState() {
		System.out.println("socket.isConnected " + socket.isConnected());
		System.out.println("socket.isClosed " + socket.isClosed());
	}
	
}