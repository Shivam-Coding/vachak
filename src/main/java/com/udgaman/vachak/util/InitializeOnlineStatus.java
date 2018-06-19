package com.udgaman.vachak.util;

import java.util.Set;

public class InitializeOnlineStatus implements Runnable {

	@Override
	public void run() {
		
		while(true){
		
		try {
			Thread.sleep(300000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Set<String> keys = OnlineStatus.onlineStatus.keySet();
		for(String k: keys){
			OnlineStatus.initialize(k, "offline");
		}
		
		}

	}

}
