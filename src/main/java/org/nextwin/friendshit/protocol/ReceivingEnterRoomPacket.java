package org.nextwin.friendshit.protocol;

import org.nextwin.protocol.Packet;

public class ReceivingEnterRoomPacket implements Packet {
	
	private int id;
	private String nickname;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
