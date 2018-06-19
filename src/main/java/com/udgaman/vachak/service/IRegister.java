package com.udgaman.vachak.service;

import java.util.List;

import com.udgaman.vachak.model.User;

public interface IRegister {
	
	public String register(User user);
	public List<User> initialization(String contacts);
	public String updateDCM(User user);

}
