package com.udgaman.vachak.repository;

import com.udgaman.vachak.model.Friend;
import com.udgaman.vachak.model.User;

public interface RegisterRepository {
	
	public String registerUser(User user);
	public User initialization(Friend friend);
	public String updateGCM(User user);
	public User getUser(String phone);

}
