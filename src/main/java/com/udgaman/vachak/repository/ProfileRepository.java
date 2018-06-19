package com.udgaman.vachak.repository;

import com.udgaman.vachak.util.UpdateProfileStatus;

public interface ProfileRepository {
	public void updateProfile(UpdateProfileStatus updateProfile);
	public void updateStatus(UpdateProfileStatus updateProfile);
}
