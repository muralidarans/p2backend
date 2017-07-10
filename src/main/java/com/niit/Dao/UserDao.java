package com.niit.Dao;

import java.util.List;

import com.niit.model.Users;

public interface UserDao {

	List<Users> getAllUsers();
	void saveUsers(Users user);
	Users login(Users users);
	Users updateUser(Users validUser);
}
