package com.udgaman.vachak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.udgaman.vachak.repository.ProfileRepository;
import com.udgaman.vachak.util.UpdateProfileStatus;

@Service
public class ProfileServiceImpl implements IProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;

	@Transactional
	@Override
	public void updateProfile(String profileInfo) {
		Gson gson = new Gson();
		UpdateProfileStatus updateProfile = gson.fromJson(profileInfo, UpdateProfileStatus.class);
        this.profileRepository.updateProfile(updateProfile);
	}

	@Transactional
	@Override
	public void updateStatus(String profileInfo) {
		Gson gson = new Gson();
		UpdateProfileStatus updateProfile = gson.fromJson(profileInfo, UpdateProfileStatus.class);
        this.profileRepository.updateStatus(updateProfile);
		
	}

}
