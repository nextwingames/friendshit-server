package org.nextwin.friendshit.protocol;

public class SendingRegisterPacket {
	
	private boolean isSuccess;
	private String id;
	
	public boolean getIsSuccess() {
		return isSuccess;
	}
	
	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

}
