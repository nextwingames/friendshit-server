package org.nextwin.friendshit.protocol;

import org.nextwin.friendshit.room.Room;
import org.nextwin.protocol.Packet;

public class SendingLobbyPacket implements Packet {

	private int page;
	private int length;
	private int[] ids;
	private String[] names;
	private int[] headcounts;
	private int[] maxPeoples;
	private boolean[] statuses;
	
	public SendingLobbyPacket(Room[] rooms) {
		length = rooms.length;
		
		ids = new int[length];
		names = new String[length];
		headcounts = new int[length];
		maxPeoples = new int[length];
		statuses = new boolean[length];
		
		for(int i = 0; i < length; i++) {
			ids[i] = rooms[i].getId();
			names[i] = rooms[i].getName();
			headcounts[i] = rooms[i].getHeadcount();
			maxPeoples[i] = rooms[i].getMaxPeople();
			statuses[i] = rooms[i].getStatus();
		}
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public int[] getIds() {
		return ids;
	}
	
	public void setIds(int[] ids) {
		this.ids = ids;
	}
	
	public String[] getNames() {
		return names;
	}
	
	public void setNames(String[] names) {
		this.names = names;
	}
	
	public int[] getHeadcounts() {
		return headcounts;
	}
	
	public void setHeadcounts(int[] headcounts) {
		this.headcounts = headcounts;
	}
	
	public int[] getMaxPeoples() {
		return maxPeoples;
	}

	public void setMaxPeoples(int[] maxPeoples) {
		this.maxPeoples = maxPeoples;
	}
	
	public boolean[] getStatuses() {
		return statuses;
	}	

	public void setStatuses(boolean[] statuses) {
		this.statuses = statuses;
	}
	
}
