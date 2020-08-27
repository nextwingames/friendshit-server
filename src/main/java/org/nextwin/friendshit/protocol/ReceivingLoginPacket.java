package org.nextwin.friendshit.protocol;

import org.nextwin.protocol.Packet;

public class ReceivingLoginPacket implements Packet {
	
	private String id;
	private String pw;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}

}
