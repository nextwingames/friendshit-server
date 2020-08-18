package org.nextwin.service;

import java.io.IOException;

import org.nextwin.protocol.Protocols;
import org.nextwin.protocol.TestPacket;

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
		
		try {
			networkManager.send(Protocols.TEST, testPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
