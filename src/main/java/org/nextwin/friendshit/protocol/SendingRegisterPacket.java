package org.nextwin.friendshit.protocol;

import org.nextwin.protocol.Packet;

public class SendingRegisterPacket implements Packet {
	
	private int result;
	private String id;
	
	public int getResult() {
		return result;
	}
	
	public void setResult(int result) {
		this.result = result;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

}
