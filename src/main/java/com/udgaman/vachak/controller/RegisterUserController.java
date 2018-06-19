package com.udgaman.vachak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.udgaman.vachak.model.User;
import com.udgaman.vachak.model.View;
import com.udgaman.vachak.service.IRegister;

@RestController
public class RegisterUserController{
	
	private final IRegister iRegister;
	
	@Autowired
	public RegisterUserController(IRegister iRegister) {
		this.iRegister = iRegister;
	}
	
	@RequestMapping(value="/register",method = RequestMethod.POST )
	public String register(User user) {
		
		return iRegister.register(user);
	}
	
	@JsonView(View.matched.class)
	@RequestMapping(value="/initialization")
	public List<User> initialization(@RequestBody String contacts){
		
		return iRegister.initialization(contacts);
	}
	
	@RequestMapping(value="/updateGCM",method = RequestMethod.POST )
	public String updateGCM(User user){
		return iRegister.updateDCM(user);
		
	}

}
