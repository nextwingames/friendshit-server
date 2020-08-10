package org.nextwin.protocol;


public class Header {

	private int msgType;
	private int length;
	
	public Header() {}
	
	public Header(int msgType, int length) {
		this.msgType = msgType;
		this.length = length;
	}
	
	public int getMsgType() {
		return msgType;
	}
	
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
}