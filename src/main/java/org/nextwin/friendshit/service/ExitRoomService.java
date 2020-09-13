package org.nextwin.friendshit.service;

import org.nextwin.friendshit.protocol.ReceivingExitRoomPacket;
import org.nextwin.friendshit.room.Room;
import org.nextwin.friendshit.server.MainServer;
import org.nextwin.protocol.Packet;
import org.nextwin.service.Service;
import org.nextwin.util.Logger;

public class ExitRoomService extends Service {
	
	private ReceivingExitRoomPacket receivingExitRoomPacket;

	public ExitRoomService(Packet packet) {
		super(packet);
		receivingExitRoomPacket = (ReceivingExitRoomPacket)packet;
	}

	@Override
	public void execute() {
		int roomId = receivingExitRoomPacket.getRoomId();
		String nickname = receivingExitRoomPacket.getNickname();
		Room room = MainServer.rooms.get(roomId);
		
		room.removePlayer(nickname);
		Logger.log("EXIT_ROOM", room.getRoomInfo());
		
		// 아무도 없으면 방 삭제
		if(room.getHeadcount() == 0)
			MainServer.removeRoom(roomId);
	}

}
