package com.udgaman.vachak.util;

import java.util.HashMap;
import java.util.Map;

public class OnlineStatus {
	
	
	public static Map<String, OnlineUsers> onlineStatus = new HashMap<String, OnlineUsers>();
	
	
	public static  void initialize(String user, String state){
	       
		onlineStatus.put(user, new OnlineUsers(state));
	       
	   }
	
//	public static void addFriendsStatus(String user, String friend){
//		OnlineUsers ou = onlineStatus.get(user);
//		ou.getFriends().add(friend);
//		onlineStatus.put(user, ou);
//		
//	}
	
	
	public static void updateFriends(String phone, OnlineUsers onlineUsers){
		onlineStatus.put(phone, onlineUsers);
	}
	
	public static void updateUserStatus(String user, String status){
		OnlineUsers ou = onlineStatus.get(user);
		ou.setStatus(status);
		onlineStatus.put(user, ou);
	}
	   
	   public static OnlineUsers getData(String user){
	       return onlineStatus.get(user);
	   }
	   

}
