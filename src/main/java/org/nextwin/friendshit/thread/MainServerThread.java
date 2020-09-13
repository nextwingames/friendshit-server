package org.nextwin.friendshit.thread;

import java.io.IOException;
import java.net.Socket;

import org.nextwin.friendshit.protocol.Protocol;
import org.nextwin.friendshit.protocol.ReceivingChatPacket;
import org.nextwin.friendshit.protocol.ReceivingCreateRoomPacket;
import org.nextwin.friendshit.protocol.ReceivingEnterRoomPacket;
import org.nextwin.friendshit.protocol.ReceivingExitRoomPacket;
import org.nextwin.friendshit.protocol.ReceivingLoginPacket;
import org.nextwin.friendshit.protocol.ReceivingRegisterPacket;
import org.nextwin.friendshit.service.CreateRoomService;
import org.nextwin.friendshit.service.EnterRoomService;
import org.nextwin.friendshit.service.ExitRoomService;
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
		Service service = null;
		Packet packet;
		
		switch (msgType) {
		case Protocol.REGISTER:
			packet = (ReceivingRegisterPacket)JsonManager.bytesToObject(data, ReceivingRegisterPacket.class);
			service = new RegisterService(packet);
			break;
			
		case Protocol.LOGIN:
			packet = (ReceivingLoginPacket)JsonManager.bytesToObject(data, ReceivingLoginPacket.class);
			service = new LoginService(packet);
			break;
			
		case Protocol.LOBBY_CHAT:
			packet = (ReceivingChatPacket)JsonManager.bytesToObject(data, ReceivingChatPacket.class);
			service = new LobbyChatService(packet);
			break;
			
		case Protocol.CREATE_ROOM:
			packet = (ReceivingCreateRoomPacket)JsonManager.bytesToObject(data, ReceivingCreateRoomPacket.class);
			service = new CreateRoomService(packet);
			break;
			
		case Protocol.ENTER_ROOM:
			packet = (ReceivingEnterRoomPacket)JsonManager.bytesToObject(data, ReceivingEnterRoomPacket.class);
			service = new EnterRoomService(packet);
			break;
			
		case Protocol.EXIT_ROOM:
			packet = (ReceivingExitRoomPacket)JsonManager.bytesToObject(data, ReceivingExitRoomPacket.class);
			service = new ExitRoomService(packet);
			break;
			
		default:
			break;
		}
		
		if(service != null)
			service.execute();
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
