package com.udgaman.vachak.service;

import java.util.List;

import com.udgaman.vachak.model.Message;

public interface IMessage {
	
	public int upStreamMessage(String message);
	public List<Message> downStreamMessage(String retrieve);
	public Message getMessage(String retrieve);
	public void submitSeenReport(String retrieve);

}
