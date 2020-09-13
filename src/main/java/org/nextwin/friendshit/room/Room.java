package org.nextwin.friendshit.room;

import java.util.Iterator;
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
		id = MainServer.freeRoomId.isEmpty() ? ++MainServer.roomId : MainServer.freeRoomId.poll();
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
	
	public String[] playersToArray() {
		String[] array = new String[headcount];
		
		int index = 0;
		Iterator<String> iterator = players.iterator();
		while(iterator.hasNext())
			array[index] = iterator.next();
		
		return array;
	}
	
	public void addPlayer(String nickname) {
		players.add(nickname);
		headcount++;
	}
	
	public void removePlayer(String nickname) {
		Iterator<String> iterator = players.iterator();
		int index = 0;
		while(iterator.hasNext()) {
			if(iterator.next().equals(nickname)) {
				players.remove(index);
				break;
			}
			index++;
		}
		
		headcount--;
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
		
		StringBuilder playersInfo = new StringBuilder();
		Iterator<String> iterator = players.iterator();
		int i = 0;
		while(iterator.hasNext())
			playersInfo.append(" (" + ++i + ")" + iterator.next());
		
		String statusString = status ? "Playing" : "Waiting";
		return "id: " + id + ", name: " + name + ", headcount: " + headcount + ", maxPeople: " + maxPeople
				 + ", map: " + mapString + ", status: " + statusString + "\n               players:" + playersInfo;
	}

}
