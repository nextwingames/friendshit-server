package org.nextwin.service;

import org.nextwin.net.NetworkManager;

public abstract class Service {
	protected NetworkManager networkManager;
	
	protected Service() {
		networkManager = NetworkManager.getInstance();
	}
	
	public abstract void execute();

}
