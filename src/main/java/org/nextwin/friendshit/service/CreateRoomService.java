package org.nextwin.friendshit.service;

import org.nextwin.friendshit.protocol.Protocol;
import org.nextwin.friendshit.protocol.ReceivingCreateRoomPacket;
import org.nextwin.friendshit.protocol.SendingCreateRoomPacket;
import org.nextwin.friendshit.room.Room;
import org.nextwin.friendshit.server.MainServer;
import org.nextwin.protocol.Packet;
import org.nextwin.service.Service;
import org.nextwin.util.Logger;

public class CreateRoomService extends Service {
	
	private ReceivingCreateRoomPacket receivingCreateRoomPacket;

	public CreateRoomService(Packet packet) {
		super(packet);
		receivingCreateRoomPacket = (ReceivingCreateRoomPacket)packet;
	}

	@Override
	public void execute() {
		String name = receivingCreateRoomPacket.getName();
		int maxPeople = receivingCreateRoomPacket.getMaxPeople();
		int map = receivingCreateRoomPacket.getMap();
		
		// 새로운 방 생성
		Room room = new Room(name, maxPeople, map);
		MainServer.rooms.put(room.getId(), room);
		
		networkManager.send(Protocol.CREATE_ROOM, new SendingCreateRoomPacket(room));
		Logger.log("CREATE_ROOM", room.getRoomInfo());
	}

}
