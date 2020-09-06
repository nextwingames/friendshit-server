package org.nextwin.friendshit.thread;

import java.io.IOException;
import java.net.Socket;

import org.nextwin.friendshit.protocol.Protocol;
import org.nextwin.friendshit.protocol.ReceivingChatPacket;
import org.nextwin.friendshit.protocol.ReceivingCreateRoomPacket;
import org.nextwin.friendshit.protocol.ReceivingLoginPacket;
import org.nextwin.friendshit.protocol.ReceivingRegisterPacket;
import org.nextwin.friendshit.service.CreateRoomService;
import org.nextwin.friendshit.service.LobbyChatService;
import org.nextwin.friendshit.service.LoginService;
import org.nextwin.friendshit.service.RegisterService;
import org.nextwin.protocol.Packet;
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
		Packet packet;
		switch (msgType) {
		case Protocol.REGISTER:
			packet = (ReceivingRegisterPacket)JsonManager.bytesToObject(data, ReceivingRegisterPacket.class);
			service = new RegisterService(packet);
			service.execute();
			break;
			
		case Protocol.LOGIN:
			packet = (ReceivingLoginPacket)JsonManager.bytesToObject(data, ReceivingLoginPacket.class);
			service = new LoginService(packet);
			service.execute();
			break;
			
		case Protocol.LOBBY_CHAT:
			packet = (ReceivingChatPacket)JsonManager.bytesToObject(data, ReceivingChatPacket.class);
			service = new LobbyChatService(packet);
			service.execute();
			break;
			
		case Protocol.CREATE_ROOM:
			packet = (ReceivingCreateRoomPacket)JsonManager.bytesToObject(data, ReceivingCreateRoomPacket.class);
			service = new CreateRoomService(packet);
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
	protected void onExitServer() {
		System.out.println("Exit main server");		
	}

}
