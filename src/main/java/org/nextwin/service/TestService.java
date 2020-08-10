package org.nextwin.service;

import org.nextwin.protocol.TestPacket;

public class TestService implements Service {
	
	private TestPacket testPacket;
	
	public TestService(TestPacket testPacket) {
		this.testPacket = testPacket;
	}

	public void execute() {
		System.out.println("data : " + testPacket.getData());
		System.out.println("str : " + testPacket.getStr());
	}

}
