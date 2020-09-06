package org.nextwin.friendshit.protocol;

import org.nextwin.protocol.Packet;

public class ReceivingCreateRoomPacket implements Packet {
	
	private String name;
	private int maxPeople;
	private int map;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getMaxPeople() {
		return maxPeople;
	}
	
	public void setMaxPeople(int maxPeople) {
		this.maxPeople = maxPeople;
	}
	
	public int getMap() {
		return map;
	}
	
	public void setMap(int map) {
		this.map = map;
	}	

}
