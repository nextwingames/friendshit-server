package org.nextwin.friendshit.protocol;

import org.nextwin.protocol.Packet;

public class SendingCreateRoomPacket implements Packet {
	
	private int id;
	
	public SendingCreateRoomPacket(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
