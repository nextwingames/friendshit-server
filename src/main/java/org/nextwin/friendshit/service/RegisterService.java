package org.nextwin.friendshit.service;

import org.nextwin.dao.MemberDao;
import org.nextwin.dto.MemberDto;
import org.nextwin.friendshit.protocol.Protocol;
import org.nextwin.friendshit.protocol.ReceivingRegisterPacket;
import org.nextwin.friendshit.protocol.SendingRegisterPacket;
import org.nextwin.service.Service;

public class RegisterService extends Service {
	
	private ReceivingRegisterPacket receivingRegisterPacket;
	
	public RegisterService(ReceivingRegisterPacket packet) {
		receivingRegisterPacket = packet;
	}

	@Override
	public void execute() {
		MemberDto dto = new MemberDto();
		dto.setId(receivingRegisterPacket.getId());
		dto.setNickname(receivingRegisterPacket.getNickname());
		dto.setPw(receivingRegisterPacket.getPw());
		dto.setMail(receivingRegisterPacket.getMail());
		
		MemberDao dao = new MemberDao();
		SendingRegisterPacket sendingRegisterPacket = new SendingRegisterPacket();
		System.out.print("Id: " + dto.getId() + ", Nickname: " + dto.getNickname() + " --- ");
		// 실패
		if(dao.register(dto) == 0) {
			sendingRegisterPacket.setIsSuccess(false);
			System.out.println("Failed to register");
		}
		// 성공
		else { 
			sendingRegisterPacket.setIsSuccess(true);
			System.out.println("Success to register");
		}
		sendingRegisterPacket.setId(dto.getId());
		
		networkManager.send(Protocol.REGISTER, sendingRegisterPacket);			
	}

}
