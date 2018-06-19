package com.udgaman.vachak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udgaman.vachak.service.IOnlineStatus;
import com.udgaman.vachak.util.OnlineStatusUpdate;

@RestController
public class OnlineStatusController {
	
	private final IOnlineStatus iOnlineStatus;
	
	@Autowired
	public OnlineStatusController(IOnlineStatus iOnlineStatus){
		this.iOnlineStatus = iOnlineStatus;
	}
	
	@RequestMapping(value = "/updatestatus" , method = RequestMethod.POST)
	public void updateStatus(@RequestBody String updateStatus){
		this.iOnlineStatus.updateStatus(updateStatus);
	}
	
	@RequestMapping(value = "/getfriendstatus" , method = RequestMethod.POST)
	public OnlineStatusUpdate getOnlineStatus(@RequestBody String onlineStatus){
		return this.iOnlineStatus.getOnlineStatus(onlineStatus);
	}
	
	@RequestMapping(value = "/updatetypingstatus" , method = RequestMethod.POST)
	public void updateTypingStatus(@RequestBody String updateStatus){
		this.iOnlineStatus.updateTypingStatus(updateStatus);
	}

}
