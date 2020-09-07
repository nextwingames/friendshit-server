package org.nextwin.friendshit.protocol;

import org.nextwin.friendshit.room.Room;
import org.nextwin.friendshit.service.EnterRoomService;
import org.nextwin.protocol.Packet;

public class SendingEnterRoomPacket implements Packet {
	
	private int result;
	private int id;
	private String name;
	private int maxPeople;
	private int map;
	
	public SendingEnterRoomPacket(Room room) {
		result = EnterRoomService.ENTER_SUCCESS;
		id = room.getId();
		name = room.getName();
		maxPeople = room.getMaxPeople();
		map = room.getMap();
	}
	
	public SendingEnterRoomPacket(int result) {
		this.result = result;
	}
	
	public int getResult() {
		return result;
	}
	
	public void setResult(int result) {
		this.result = result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
