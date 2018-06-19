package com.udgaman.vachak.HibernateDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.udgaman.vachak.model.Message;
import com.udgaman.vachak.model.User;
import com.udgaman.vachak.repository.MessageRepository;

@Repository
public class MessageDAO implements MessageRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public int upStreamMessage(Message receiveMessage) {
		try{
		this.em.persist(receiveMessage);
		}catch(Exception e){
			
		}
		return receiveMessage.getId();
	}

	@Override
	public User getUser(String phone) {
		User user = null;
		try{
			Query query = em.createQuery("from User sm where sm.phone=:arg1");
			query.setParameter("arg1", phone);
			List obj = query.getResultList();	
			 if (!obj.isEmpty() ){
				 user = (User)obj.get(0);
			 }	
		}catch(Exception e){
		e.printStackTrace();
		}
		return user;
	
	}

	@Override
	public List<Message> downStreamMeassage(String phone) {
		List<Message> messages = null;
		try{
			Query query = em.createQuery("from Message sm where sm.toPhoneNumber=:arg1 and sm.delivery=:arg2");
			query.setParameter("arg1", phone);
			query.setParameter("arg2", "0");
			messages = query.getResultList();	
			
		}catch(Exception e){
		e.printStackTrace();
		}
		return messages;
	}

	@Override
	public void updateMessage(Message message) {
		try{
			this.em.merge(message);
			}catch(Exception e){
				e.printStackTrace();
			}
		
	}

	@Override
	public Message getMessage(int id) {
		List<Message> msg;
		try{
			Query query = em.createQuery("from Message sm where sm.id=:arg1");
			query.setParameter("arg1", id);
			
			msg = query.getResultList();
			if( !msg.isEmpty()){
				return msg.get(0);
			}
			
		}catch(Exception e){
		e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Message> seenReportMessage(String from, String to) {
		List<Message> messages = null;
		try{
			Query query = em.createQuery("from Message sm where sm.toPhoneNumber=:arg1 and sm.fromPhoneNumber=:arg2 and sm.seen=:arg3");
			query.setParameter("arg1", to);
			query.setParameter("arg2", from);
			query.setParameter("arg3", "0");
			messages = query.getResultList();	
			
		}catch(Exception e){
		e.printStackTrace();
		}
		return messages;
	}

	
	
}
