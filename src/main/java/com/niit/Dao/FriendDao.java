package com.niit.Dao;

import java.util.List;

import com.niit.model.Friend;
import com.niit.model.Users;

public interface FriendDao {
	List<Users> ListOfSuggestedUsers(String username);
	void friendRequest(String fromUsername,String toUsername);
	List<Friend> listOfPendingFriendRequests(String loggedInUsername);
	void updatePendingRequest(String fromId,String toId,char status);
	List<Friend> listOfFriends(String username);
}
