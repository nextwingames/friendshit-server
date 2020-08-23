package org.nextwin.friendshit.service;

import java.io.IOException;

import org.nextwin.friendshit.protocol.Protocol;
import org.nextwin.friendshit.protocol.TestPacket;
import org.nextwin.service.Service;

public class TestService extends Service {
	
	private TestPacket testPacket;
	
	public TestService(TestPacket testPacket) {
		this.testPacket = testPacket;
	}

	public void execute() {
		System.out.println("data : " + testPacket.getData());
		System.out.println("str : " + testPacket.getStr());
		
		testPacket.setData(testPacket.getData() + 1);
		testPacket.setStr("New String");
		
		//networkManager.send(Protocol.REGISTER, object);
	}

}
