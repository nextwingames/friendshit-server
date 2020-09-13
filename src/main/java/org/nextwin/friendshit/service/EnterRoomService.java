package org.nextwin.friendshit.service;

import org.nextwin.friendshit.protocol.Protocol;
import org.nextwin.friendshit.protocol.ReceivingEnterRoomPacket;
import org.nextwin.friendshit.protocol.SendingEnterRoomPacket;
import org.nextwin.friendshit.room.Room;
import org.nextwin.friendshit.server.MainServer;
import org.nextwin.protocol.Packet;
import org.nextwin.service.Service;
import org.nextwin.util.Logger;

public class EnterRoomService extends Service {
	
	public static final int ENTER_FAIL_FULL = 0;
	public static final int ENTER_FAIL_NULL = 1;
	public static final int ENTER_SUCCESS = 2;
	
	private ReceivingEnterRoomPacket receivingEnterRoomPacket;

	public EnterRoomService(Packet packet) {
		super(packet);
		receivingEnterRoomPacket = (ReceivingEnterRoomPacket)packet;
	}

	@Override
	public void execute() {
		int roomId = receivingEnterRoomPacket.getId();
		String nickname = receivingEnterRoomPacket.getNickname();
		
		Room room = MainServer.rooms.get(roomId);
		SendingEnterRoomPacket sendingEnterRoomPacket;
		
		if(room == null) 
			sendingEnterRoomPacket = new SendingEnterRoomPacket(ENTER_FAIL_NULL);
		else if(room.isFull())
			sendingEnterRoomPacket = new SendingEnterRoomPacket(ENTER_FAIL_FULL);
		else {
			room.addPlayer(nickname);
			sendingEnterRoomPacket = new SendingEnterRoomPacket(room);
		}
				
		networkManager.send(Protocol.ENTER_ROOM, sendingEnterRoomPacket);
		Logger.log("ENTER_ROOM", room.getRoomInfo());
	}

}
