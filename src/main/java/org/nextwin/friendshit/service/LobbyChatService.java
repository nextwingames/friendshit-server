package org.nextwin.friendshit.service;

import java.util.Iterator;

import org.nextwin.friendshit.protocol.Protocol;
import org.nextwin.friendshit.protocol.ReceivingChatPacket;
import org.nextwin.friendshit.protocol.SendingChatPacket;
import org.nextwin.friendshit.server.MainServer;
import org.nextwin.protocol.Packet;
import org.nextwin.service.Service;
import org.nextwin.util.Logger;

public class LobbyChatService extends Service {
	
	private ReceivingChatPacket receivingChatPacket;

	public LobbyChatService(Packet packet) {
		super(packet);
		receivingChatPacket = (ReceivingChatPacket)packet;
	}

	@Override
	public void execute() {
		String nickname = receivingChatPacket.getNickname();
		String message = receivingChatPacket.getMessage();
		Logger.log("CHAT", nickname + ": " + message);
		SendingChatPacket sendingChatPacket = new SendingChatPacket(nickname, message);
		
		// 접속한 모든 플레이어에게 채팅 전달
		Iterator<String> iterator = MainServer.connectedUsers.keySet().iterator();
		while(iterator.hasNext()) {
			MainServer.connectedUsers.get(iterator.next()).send(Protocol.LOBBY_CHAT, sendingChatPacket);
		}
	}

}
