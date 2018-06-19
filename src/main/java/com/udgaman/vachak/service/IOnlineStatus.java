package com.udgaman.vachak.service;

import com.udgaman.vachak.util.OnlineStatusUpdate;

public interface IOnlineStatus {
	
	public void updateStatus(String updateStatus);
	public OnlineStatusUpdate getOnlineStatus(String onlineStatus);
	public void updateTypingStatus(String updateStatus);

}
