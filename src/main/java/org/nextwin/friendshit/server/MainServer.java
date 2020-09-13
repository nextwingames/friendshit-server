package org.nextwin.friendshit.server;

import java.net.Socket;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

import org.nextwin.friendshit.room.Room;
import org.nextwin.friendshit.thread.MainServerThread;
import org.nextwin.net.NetworkManager;
import org.nextwin.server.Server;
import org.nextwin.thread.ServerThread;

public class MainServer extends Server {
	
	public static ConcurrentHashMap<String, NetworkManager> connectedUsers = new ConcurrentHashMap<String, NetworkManager>();
	public static ConcurrentHashMap<Integer, Room> rooms = new ConcurrentHashMap<Integer, Room>();
	public static int roomId = 0;
	public static PriorityQueue<Integer> freeRoomId = new PriorityQueue<Integer>();

	public static void main(String[] args) {
		Server server = new MainServer();
		server.go();
	}
	
	@Override
	protected ServerThread createServerThread(Socket socket) {
		rooms.put(0, new Room("dummy", 6, Room.SUPPLY_WEARHOUSE));
		return new MainServerThread(socket);
	}

	@Override
	protected void onTerminate() {
		System.out.println("Main server terminated");	
	}

	@Override
	protected int getPort() {
		return NetworkManager.MAIN_PORT;
	}
	
	/**
	 * 페이지에서 5개의 방 리스트 반환
	 * @param page
	 * @return 5rooms
	 */
	public static Room[] getPageRooms(int page) {
		int length = Math.min(MainServer.rooms.size(), 5);
		Room[] rooms = new Room[length];
		
		int index = page * 5;
		int i = 0;
		
		while(i < length) {
			Room room = MainServer.rooms.get(index);
			index++;
			
			if(room == null)
				continue;
			rooms[i++] = room;
		}
		
		return rooms;
	}
	
	public static void removeRoom(int roomId) {
		rooms.remove(roomId);
		freeRoomId.add(roomId);
	}
	
}
