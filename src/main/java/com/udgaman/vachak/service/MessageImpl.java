package com.udgaman.vachak.service;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.udgaman.vachak.model.GCMMessage;
import com.udgaman.vachak.model.Message;
import com.udgaman.vachak.model.SendGCM;
import com.udgaman.vachak.model.User;
import com.udgaman.vachak.repository.MessageRepository;
import com.udgaman.vachak.util.ListContants;
import com.udgaman.vachak.util.RetrieveInfo;

@Service
public class MessageImpl implements IMessage {
	
	@Autowired
	private MessageRepository messageRepository;

	@Override
	@Transactional
	public int upStreamMessage(String message) {
		
		Set<String> deliveryReportToUser = new HashSet<String>();
		Gson gson = new Gson();
		Message receiveMessage = gson.fromJson(message, Message.class);
		receiveMessage.setId(0);
		DateTime date = new DateTime(new DateTime().toString(), DateTimeZone.UTC);
		receiveMessage.setSent(date.toString());
		User u = this.messageRepository.getUser(receiveMessage.getFromPhoneNumber());
		u.getMessages().add(receiveMessage);
		receiveMessage.getUsers().add(u);
		int i = this.messageRepository.upStreamMessage(receiveMessage);
		User user = this.messageRepository.getUser(receiveMessage.getToPhoneNumber());
		if(user != null ){
			deliveryReportToUser.add(user.getGcm());
		}
		sendAlert(deliveryReportToUser, ListContants.newMessageCode,"");
		return i;
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void sendAlert(Set<String> gcmCode, int code, String info){
		
		for(String s : gcmCode){
		
		try{
			
		Gson gson = new Gson();
		GCMMessage gcm = new GCMMessage();
		gcm.setInfo(info);
		gcm.setCode(code);
		SendGCM sg = new SendGCM();
		sg.setData(gcm);
		sg.setTo(s);
		String message = new String(gson.toJson(sg));

        URL url = new URL(ListContants.firebaseCM);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type","application/json");
        connection.setRequestProperty("Authorization", ListContants.serverID);
        connection.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(message);  
        writer.flush();
        System.out.println(connection.getResponseCode());
        new InputStreamReader(connection.getInputStream());
        writer.close();
        
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		}
		
	}

	

	@Override
	@Transactional
	public List<Message> downStreamMessage(String retrieve) {
		
		Set<String> deliveryReportToUser = new HashSet<String>();
		
		Gson gson = new Gson();
		RetrieveInfo retrieveInfo = gson.fromJson(retrieve, RetrieveInfo.class);
		DateTime date = new DateTime(new DateTime().toString(), DateTimeZone.UTC);
		
		
		List<Message> result = new LinkedList<Message>();
		
		DateTime date1 = new DateTime(date,DateTimeZone.forID(retrieveInfo.getTimeZone()));
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd MMM YYYY");
		DateTimeFormatter fmt1 = DateTimeFormat.forPattern("hh:mm a");
		String delivery = date1.toString(fmt)+";"+date1.toString(fmt1);
		
		
		List<Message> messages = this.messageRepository.downStreamMeassage(retrieveInfo.getPhoneNumber());
		for(Message msg : messages){
			Message m = new Message();
			
			m.setId(msg.getId());
			m.setToPhoneNumber(msg.getToPhoneNumber());
			m.setFromPhoneNumber(msg.getFromPhoneNumber());
			m.setMessage(msg.getMessage());
			m.setType(msg.getType());
			m.setSent(msg.getSent());
			m.setSeen(msg.getSeen());
			m.setDelivery(delivery);
			
			result.add(m);
			
			msg.setDelivery(date.toString());
			
			User user = this.messageRepository.getUser(msg.getFromPhoneNumber());
			if(user != null ){
				deliveryReportToUser.add(user.getGcm());
			
			}
			
			
		}
		
		 sendAlert(deliveryReportToUser,ListContants.deliveryMessageCode,retrieveInfo.getPhoneNumber());
		
		return result;
	}

	@Override
	@Transactional
	public Message getMessage(String retrieve) {
		Gson gson = new Gson();
		RetrieveInfo retrieveInfo = gson.fromJson(retrieve, RetrieveInfo.class);
		Message msg = this.messageRepository.getMessage(retrieveInfo.getId());
		
		
		
		if(msg != null){
		Message message = new Message();
		message.setId(msg.getId());
		message.setFromPhoneNumber(msg.getFromPhoneNumber());
		message.setToPhoneNumber(msg.getToPhoneNumber());
		message.setType(msg.getType());
		message.setMessage(msg.getMessage());
		
		
		DateTime date1 = new DateTime(msg.getSent(),DateTimeZone.forID(retrieveInfo.getTimeZone()));
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd MMM YYYY");
		DateTimeFormatter fmt1 = DateTimeFormat.forPattern("hh:mm a");
		
		message.setSent(date1.toString(fmt)+";"+date1.toString(fmt1));
		
		if(msg.getDelivery().equals("0")){
			message.setDelivery("-;-");
		}else{
			
			DateTime date2 = new DateTime(msg.getDelivery(),DateTimeZone.forID(retrieveInfo.getTimeZone()));
			DateTimeFormatter fmtDeliver = DateTimeFormat.forPattern("dd MMM YYYY");
			DateTimeFormatter fmtDeliver1 = DateTimeFormat.forPattern("hh:mm a");
			
			message.setDelivery(date2.toString(fmtDeliver)+";"+date2.toString(fmtDeliver1));
			
		}
		
		if(msg.getSeen().equals("0")){
			message.setSeen("-;-");
		}else{
			
			DateTime date3 = new DateTime(msg.getSeen(),DateTimeZone.forID(retrieveInfo.getTimeZone()));
			DateTimeFormatter fmtSeen = DateTimeFormat.forPattern("dd MMM YYYY");
			DateTimeFormatter fmtSeen1 = DateTimeFormat.forPattern("hh:mm a");
			
			message.setSeen(date3.toString(fmtSeen)+";"+date3.toString(fmtSeen1));
			
		}
		
		return message;
		}
		return null;
	}

	@Transactional
	@Override
	public void submitSeenReport(String retrieve) {
		Gson gson = new Gson();
		RetrieveInfo retrieveInfo = gson.fromJson(retrieve, RetrieveInfo.class);
		DateTime date = new DateTime(new DateTime().toString(), DateTimeZone.UTC);
		List<Message> messages = this.messageRepository.seenReportMessage(retrieveInfo.getFriendNumber(), retrieveInfo.getPhoneNumber());
		for(Message msg : messages){
			msg.setSeen(date.toString());
		}
	}
	
}
