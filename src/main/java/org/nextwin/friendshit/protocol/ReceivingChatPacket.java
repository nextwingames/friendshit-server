package org.nextwin.friendshit.protocol;

import org.nextwin.protocol.Packet;

public class ReceivingChatPacket implements Packet {
	
	private String nickname;
	private String message;
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
