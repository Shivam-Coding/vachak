package com.udgaman.vachak.HibernateDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.udgaman.vachak.model.User;
import com.udgaman.vachak.repository.ProfileRepository;
import com.udgaman.vachak.util.UpdateProfileStatus;

@Repository
public class ProfileDAO implements ProfileRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void updateProfile(UpdateProfileStatus updateProfile) {
		Query query = em.createQuery("from User sm where sm.phone=:arg1");
		query.setParameter("arg1", updateProfile.getPhone());
		User user = (User) query.getSingleResult();
		user.setProfile(updateProfile.getPicture());
		user.setThumbnail(updateProfile.getThumbnail());
	}

	@Override
	public void updateStatus(UpdateProfileStatus updateProfile) {
		Query query = em.createQuery("from User sm where sm.phone=:arg1");
		query.setParameter("arg1", updateProfile.getPhone());
		User user = (User) query.getSingleResult();
		user.setStatus(updateProfile.getStatus());
		
	}
	
	

}
