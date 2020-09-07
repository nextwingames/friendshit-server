package org.nextwin.friendshit.room;

import java.util.LinkedList;

import org.nextwin.friendshit.server.MainServer;

public class Room {
	
	public static final int SUPPLY_WEARHOUSE = 0;
	
	public static final boolean WAITING = false;
	public static final boolean PLAYING = true;
	
	private int id;
	private String name;
	private int headcount;
	private int maxPeople;
	private int map;
	private boolean status;
	private LinkedList<String> players = new LinkedList<String>();
	
	public Room(String name, int maxPeople, int map) {
		id = ++MainServer.roomId;
		this.name = name;
		headcount = 0;
		this.maxPeople = maxPeople;
		this.map = map;
		status = WAITING;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
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

	public int getMap() {
		return map;
	}

	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status; 
	}
	
	public void addPlayer(String nickname) {
		players.add(nickname);
		headcount++;
	}
	
	public boolean isFull() {
		return headcount == maxPeople;
	}

	public String getRoomInfo() {
		String mapString = "";		
		switch (map) {
		case SUPPLY_WEARHOUSE:
			mapString = "Supply Wearhouse";
			break;

		default:
			break;
		}
		
		String statuString = status ? "Playing" : "Waiting";
		
		return "id: " + id + ", name: " + name + ", headcount: " + headcount + ", maxPeople: " + maxPeople
														+ ", map: " + mapString + ", status: " + statuString;
	}

}
