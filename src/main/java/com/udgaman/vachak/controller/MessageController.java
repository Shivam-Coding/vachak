package com.udgaman.vachak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.udgaman.vachak.model.Message;
import com.udgaman.vachak.model.View;
import com.udgaman.vachak.service.IMessage;

@RestController
public class MessageController {
	
	private final IMessage iMessage;
	
	@Autowired
	public MessageController(IMessage iMessage){
		this.iMessage = iMessage;
	}
	
	@RequestMapping(value = "/upstreammessage" , method = RequestMethod.POST)
	public String receiveMessage(@RequestBody String message){
		int i = this.iMessage.upStreamMessage(message);
		return i+"";
	}
	
	@JsonView(View.message.class)
	@RequestMapping(value = "/downstreammessage" , method = RequestMethod.POST)
	public List<Message> downStreamMessage(@RequestBody String retrieve){
		List<Message> messages = this.iMessage.downStreamMessage(retrieve);
		return messages;
	}
	
	@JsonView(View.message.class)
	@RequestMapping(value = "/getmessagedetail" , method = RequestMethod.POST)
	public Message getMessage(@RequestBody String retrieve){
		return this.iMessage.getMessage(retrieve);
	}
	
	@RequestMapping(value = "/submitseenreport" , method = RequestMethod.POST)
	public void submitSeenReport(@RequestBody String retrieve){
		this.iMessage.submitSeenReport(retrieve);
	}
	

}
