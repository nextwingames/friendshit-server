package org.nextwin.friendshit.protocol;

import org.nextwin.protocol.Packet;

public class ReceivingExitRoomPacket implements Packet {
	
	private int roomId;
	private String nickname;
	
	public int getRoomId() {
		return roomId;
	}
	
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
