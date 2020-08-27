package org.nextwin.friendshit.protocol;

import org.nextwin.protocol.Packet;

public class ReceivingRegisterPacket implements Packet{
	
	private String nickname;
	private String id;
	private String pw;
	private String mail;
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
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
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}	

}
