package org.nextwin.friendshit.service;

import org.nextwin.dao.MemberDao;
import org.nextwin.dto.MemberDto;
import org.nextwin.friendshit.protocol.Protocol;
import org.nextwin.friendshit.protocol.ReceivingRegisterPacket;
import org.nextwin.friendshit.protocol.SendingRegisterPacket;
import org.nextwin.protocol.Packet;
import org.nextwin.service.Service;

public class RegisterService extends Service {
	public static final int REGISTER_FAIL_NICKNAME = 0;
	public static final int REGISTER_FAIL_ID = -1;
	public static final int REGISTER_SUCCESS = 1;
	
	private ReceivingRegisterPacket receivingRegisterPacket;
	
	public RegisterService(Packet packet) {
		super(packet);
		receivingRegisterPacket = (ReceivingRegisterPacket)packet;
	}

	/**
	 * 회원가입 후 성공 여부를 클라이언트에게 반환
	 */
	@Override
	public void execute() {
		MemberDto dto = new MemberDto();
		dto.setId(receivingRegisterPacket.getId());
		dto.setNickname(receivingRegisterPacket.getNickname());
		dto.setPw(receivingRegisterPacket.getPw());
		dto.setMail(receivingRegisterPacket.getMail());
		
		MemberDao dao = new MemberDao();		
		SendingRegisterPacket sendingRegisterPacket = new SendingRegisterPacket();
		sendingRegisterPacket.setResult(dao.register(dto));
		sendingRegisterPacket.setId(dto.getId());
		
		networkManager.send(Protocol.REGISTER, sendingRegisterPacket);			
	}

}
