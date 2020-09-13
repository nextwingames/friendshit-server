package org.nextwin.friendshit.protocol;

import java.util.LinkedList;

import org.nextwin.friendshit.room.Room;
import org.nextwin.friendshit.service.EnterRoomService;
import org.nextwin.protocol.Packet;

public class SendingEnterRoomPacket implements Packet {
	
	private int result;
	private int id;
	private String name;
	private int headcount;
	private int maxPeople;
	private int map;
	private String[] players;
	
	public SendingEnterRoomPacket(Room room) {
		result = EnterRoomService.ENTER_SUCCESS;
		id = room.getId();
		name = room.getName();
		headcount = room.getHeadcount();
		maxPeople = room.getMaxPeople();
		map = room.getMap();
		players = room.playersToArray();
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
	
	public int getHeadcount() {
		return headcount;
	}
	
	public void setHeadcount(int headcount) {
		this.headcount = headcount;
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

	public String[] getPlayers() {
		return players;
	}

	public void setPlayers(String[] players) {
		this.players = players;
	}

}
