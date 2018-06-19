package com.udgaman.vachak.repository;

import java.util.List;

import com.udgaman.vachak.model.Message;
import com.udgaman.vachak.model.User;

public interface MessageRepository {
	
	public int upStreamMessage(Message receiveMessage);
	public User getUser(String phone);
	public List<Message> downStreamMeassage(String phone);
	public void updateMessage(Message message);
	public Message getMessage(int id);
	public List<Message> seenReportMessage(String from, String to);

}
