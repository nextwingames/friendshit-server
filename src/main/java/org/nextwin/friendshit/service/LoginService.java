package org.nextwin.friendshit.service;

import org.nextwin.dao.MemberDao;
import org.nextwin.dto.MemberDto;
import org.nextwin.friendshit.protocol.Protocol;
import org.nextwin.friendshit.protocol.ReceivingLoginPacket;
import org.nextwin.friendshit.protocol.SendingLoginPacket;
import org.nextwin.protocol.Packet;
import org.nextwin.service.Service;

public class LoginService extends Service {
	
	public static final int LOGIN_FAIL_INVALID_ID = 0;
	public static final int LOGIN_FAIL_INVALID_PW = -1;
	public static final int LOGIN_SUCCESS = 1;
	
	private ReceivingLoginPacket receivingLoginPacket;
	
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
		sendingLoginPacket.setResult(dao.login(dto));
		sendingLoginPacket.setId(dto.getId());
		sendingLoginPacket.setNickname(dto.getNickname());
		sendingLoginPacket.setTotalGame(dto.getTotalGame());
		sendingLoginPacket.setGold(dto.getGold());
		sendingLoginPacket.setSilver(dto.getSilver());
		sendingLoginPacket.setBronze(dto.getBronze());
		
		networkManager.send(Protocol.LOGIN, sendingLoginPacket);
	}

}
