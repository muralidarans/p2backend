package com.niit.Dao;

import com.niit.model.ProfilePicture;

public interface ProfileUploadDao {
	void save(ProfilePicture profilePicture);
	ProfilePicture getProfilePic(String username);
}
