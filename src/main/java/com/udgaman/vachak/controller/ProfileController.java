package com.udgaman.vachak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udgaman.vachak.service.IProfileService;

@RestController
public class ProfileController {
	
	private final IProfileService iProfileService;
	
	@Autowired
	public ProfileController(IProfileService iProfileService){
		this.iProfileService = iProfileService;
	}
	
	@RequestMapping(value = "/updateprofile" , method = RequestMethod.POST)
	public void updateProfile(@RequestBody String profileInfo){
		this.iProfileService.updateProfile(profileInfo);
	}
	
	@RequestMapping(value = "/updateprofilestatus" , method = RequestMethod.POST)
	public void updateStatus(@RequestBody String profileInfo){
		this.iProfileService.updateStatus(profileInfo);
	}

}
