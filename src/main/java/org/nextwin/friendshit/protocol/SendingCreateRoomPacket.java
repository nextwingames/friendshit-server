package org.nextwin.friendshit.protocol;

import org.nextwin.friendshit.room.Room;
import org.nextwin.protocol.Packet;

public class SendingCreateRoomPacket implements Packet {
	
	private int id;
	private String name;
	private int headcount;
	private int maxPeople;
	private int map;
	
	public SendingCreateRoomPacket(Room room) {
		id = room.getId();
		name = room.getName();
		headcount = room.getHeadcount();
		maxPeople = room.getMaxPeople();
		map = room.getMap();
	}

}
