package com.udgaman.vachak.HibernateDAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.udgaman.vachak.model.User;
import com.udgaman.vachak.repository.InitialRepository;

@Repository
public class InitialDAO implements InitialRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<String> onlineStatus() {
		Query query = em.createQuery("from User");
		List<User> obj = query.getResultList();
		List<String> users = new ArrayList<String>();
		for(User u : obj ){
			users.add(u.getPhone());
		}
		return users;
	}

}
