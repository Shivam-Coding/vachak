package com.udgaman.vachak.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.udgaman.vachak.model.Friend;
import com.udgaman.vachak.model.User;
import com.udgaman.vachak.repository.RegisterRepository;
import com.udgaman.vachak.util.OnlineStatus;

@Service
public class RegisterImpl implements IRegister {
	
	@Autowired
	private RegisterRepository registerRepository;


	@Transactional
	public String register(User user) {
		String result = "fail";
		try{
			result = this.registerRepository.registerUser(user);
			OnlineStatus.initialize(user.getPhone(), "offline");
			}catch(Exception e){
				e.printStackTrace();
			}
		return result;
	}

	@Transactional
	public List<User> initialization(String contacts) {
		List<User> users = new ArrayList<User>();
		Gson gson = new Gson();
		Type listType = new TypeToken<HashSet<Friend>>(){}.getType();
		Set<Friend> friends = gson.fromJson(contacts, listType);
		for(Friend friend: friends){
		User u = this.registerRepository.initialization(friend);
		if(u != null){
			users.add(u);
		}
		}
		
		return users;
	}

	@Override
	@Transactional
	public String updateDCM(User user) {
		
		User u = this.registerRepository.getUser(user.getPhone());
		if(u != null){
		u.setGcm(user.getGcm());
		return this.registerRepository.updateGCM(u);
		}else{
			return "fail";
		}
	}

	
	

}
