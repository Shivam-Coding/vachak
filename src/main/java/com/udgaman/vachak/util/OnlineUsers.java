package com.udgaman.vachak.util;

import java.util.LinkedList;
import java.util.List;

public class OnlineUsers {
	
	private List<String> friends = new LinkedList<String>();
	private String status;
	
	public OnlineUsers(String status){
		this.status = status;
		this.friends.clear();
	}
	
	
	public List<String> getFriends() {
		return friends;
	}
	public void setFriend(List<String> friends) {
		this.friends = friends;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
