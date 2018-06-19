package com.udgaman.vachak.HibernateDAO;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.udgaman.vachak.model.Country;
import com.udgaman.vachak.model.Friend;
import com.udgaman.vachak.model.User;
import com.udgaman.vachak.repository.RegisterRepository;

@Repository
public class RegisterDAO implements RegisterRepository{
	
	@PersistenceContext
	private EntityManager em;
    

	public String registerUser(User user) {
		
		try {
			Query query = em.createQuery("from User sm where sm.phone=:arg1");
			query.setParameter("arg1", user.getPhone());
			Query query2 = em.createQuery("from Country co where co.name=:arg2");
			query2.setParameter("arg2", user.getCountry().getName());
			List obj2 = query2.getResultList();
			List obj = query.getResultList();	
			 if (obj == null || obj.isEmpty()) {
				 if(!obj2.isEmpty()){
					 user.setCountry(((Country)obj2.get(0)));
				 }
		            this.em.persist(user);
		        } else {
		        	User u = (User)obj.get(0);
		        	user.setId(u.getId());
					user.getCountry().setId(u.getCountry().getId());
		            this.em.merge(user);
		        } } catch (Exception e) {
		        	e.printStackTrace();
		        }
		return user.getId()+"";
		}
		

	public User initialization(Friend friend) {
		try {
			Query query = em.createQuery("from User sm where sm.phone=:arg1");
			query.setParameter("arg1", friend.getUserNumber());
			
			Query query2 = em.createQuery("from User sm where sm.phone=:arg1");
			query2.setParameter("arg1", friend.getNumber());
			List user2 = query2.getResultList();
			
			if(!user2.isEmpty()){
				 ((User)user2.get(0)).getCountry();
			
			User u = (User) query.getSingleResult();
			
			Query query1 = em.createQuery("from Friend sm where sm.number=:arg1");
			query1.setParameter("arg1", friend.getNumber());
			
			List obj = query1.getResultList();
		
			 if (obj == null || obj.isEmpty()) {
				    u.getFriends().add(friend);
					friend.getUsers().add(u);
		            this.em.persist(friend);
		        } else {
		        	Friend f = (Friend)obj.get(0);
		            f.setName(friend.getName());
		            u.getFriends().add(f);
		            f.getUsers().add(u);
		            this.em.merge(f);
		        } 
			 
				 return (User) user2.get(0);
			 }
		} catch (Exception e) {
		        	e.printStackTrace();
		        }
		
		return null;
	}


	@Override
	public String updateGCM(User user) {
		String response = "failed";
		try{
		this.em.merge(user);
		response = "success";
		}catch(Exception e){
			
		}
		return response;
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

	
	
}
