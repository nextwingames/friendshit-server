package org.nextwin.friendshit.service;

import org.nextwin.friendshit.dao.MemberDao;
import org.nextwin.friendshit.dto.MemberDto;
import org.nextwin.friendshit.protocol.Protocol;
import org.nextwin.friendshit.protocol.ReceivingLoginPacket;
import org.nextwin.friendshit.protocol.SendingLobbyPacket;
import org.nextwin.friendshit.protocol.SendingLoginPacket;
import org.nextwin.friendshit.room.Room;
import org.nextwin.friendshit.server.MainServer;
import org.nextwin.friendshit.thread.MainServerThread;
import org.nextwin.protocol.Packet;
import org.nextwin.service.Service;

public class LoginService extends Service {
	
	public static final int LOGIN_FAIL_INVALID_ID = 0;
	public static final int LOGIN_FAIL_INVALID_PW = -1;
	public static final int LOGIN_SUCCESS = 1;
	
	private ReceivingLoginPacket receivingLoginPacket;
	private String playerId;
	
	public LoginService(Packet packet) {
		super(packet);
		receivingLoginPacket = (ReceivingLoginPacket)packet;
	}

	/**
	 * 로그인 성공 여부를 클라이언트에게 반환
	 */
	@Override
	public void execute() {
		MemberDto dto = new MemberDto();
		dto.setId(receivingLoginPacket.getId());
		dto.setPw(receivingLoginPacket.getPw());
		
		MemberDao dao = new MemberDao();
		SendingLoginPacket sendingLoginPacket = new SendingLoginPacket();
		int result = dao.login(dto);
		
		sendingLoginPacket.setResult(result);
		sendingLoginPacket.setId(dto.getId());
		sendingLoginPacket.setNickname(dto.getNickname());
		sendingLoginPacket.setTotalGame(dto.getTotalGame());
		sendingLoginPacket.setGold(dto.getGold());
		sendingLoginPacket.setSilver(dto.getSilver());
		sendingLoginPacket.setBronze(dto.getBronze());
		
		networkManager.send(Protocol.LOGIN, sendingLoginPacket);
		
		if(result == LOGIN_SUCCESS) {
			playerId = dto.getId();
			MainServer.connectedUsers.put(playerId, networkManager);
			
			// 첫 번째 페이지의 방 리스트
			Room[] rooms = MainServer.getPageRooms(0);
			networkManager.send(Protocol.LOBBY, new SendingLobbyPacket(rooms));
		}
	}
	
	public String getPlayerId() {
		return playerId;
	}

}
