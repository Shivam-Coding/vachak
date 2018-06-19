package com.udgaman.vachak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.udgaman.vachak.util.ListContants;
import com.udgaman.vachak.util.OnlineStatus;
import com.udgaman.vachak.util.OnlineStatusUpdate;
import com.udgaman.vachak.util.OnlineUsers;

@Service
public class OnlineStatusImpl implements IOnlineStatus {

	@Override
	public void updateStatus(String updateStatus) {
		
		Gson gson = new Gson();
		OnlineStatusUpdate statusUpdate = gson.fromJson(updateStatus, OnlineStatusUpdate.class);
		OnlineStatus.updateUserStatus(statusUpdate.getPhonenumber(), statusUpdate.getStatus());
	}

	@Override
	public OnlineStatusUpdate getOnlineStatus(String onlineStatus) {
		
		Gson gson = new Gson();
		OnlineStatusUpdate statusUpdate = gson.fromJson(onlineStatus, OnlineStatusUpdate.class);
		OnlineUsers onlineUsers = OnlineStatus.getData(statusUpdate.getPhonenumber());
		List<String> friends = onlineUsers.getFriends();
		if(friends.contains(statusUpdate.getFriend()))
		{
			statusUpdate.setFriendStatus(ListContants.TYPING);
		}else{
			OnlineUsers ou = OnlineStatus.getData(statusUpdate.getFriend());
			statusUpdate.setFriendStatus(ou.getStatus());
		}
		return statusUpdate;
	}

	@Override
	public void updateTypingStatus(String updateStatus) {
		Gson gson = new Gson();
		OnlineStatusUpdate statusUpdate = gson.fromJson(updateStatus, OnlineStatusUpdate.class);
		OnlineUsers ou = OnlineStatus.getData(statusUpdate.getFriend());
		List<String> friends = ou.getFriends();
		if(statusUpdate.getStatus().equals(ListContants.TYPING)){
			if(!friends.contains(statusUpdate.getPhonenumber())){
				friends.add(statusUpdate.getPhonenumber());
			}
		}else{
			if(friends.contains(statusUpdate.getPhonenumber())){
				friends.remove(statusUpdate.getPhonenumber());
			}
		}
	
		OnlineStatus.updateFriends(statusUpdate.getFriend(), ou);
		
	}
	
	

}
