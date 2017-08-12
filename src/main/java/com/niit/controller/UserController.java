package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.Dao.UserDao;
import com.niit.model.Error;
import com.niit.model.Users;

@RestController
public class UserController {
@Autowired
private UserDao userDao;
@RequestMapping(value="/getallusers",method=RequestMethod.GET)
public ResponseEntity<List<Users>> getAllUsers(){
	List<Users> Users=userDao.getAllUsers();
	if(Users.isEmpty())
	return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
	else
	return new ResponseEntity<List<Users>>(Users,HttpStatus.OK);
	//return new ResponseEntity<T>(data,statuscode)
}
@RequestMapping(value="/saveuser",method=RequestMethod.POST)
public ResponseEntity<?> saveUser(@RequestBody Users user){
	
	try{
		boolean isUserAlreadyExists = false;
		List<Users> existingUsers = userDao.getAllUsers();
		if(existingUsers !=null && existingUsers.size()>0){
			for(Users exisitingUser :existingUsers ){
				if(exisitingUser.getUsername().equalsIgnoreCase(user.getUsername())){
					isUserAlreadyExists = true;
					break;
				}
			}
		}
		if(!isUserAlreadyExists){

			user.setEnabled(true);
			user.setOnline(false);
			userDao.saveUsers(user);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}else{
			Error err = new Error(2,"User Already exitis");
			return new ResponseEntity<Error>(err,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}catch(Exception e) {
		e.printStackTrace();
		Error er = new Error(1, "Cannot Register the user, Reason :" + e.getMessage());
		return new ResponseEntity<Error>(er,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@RequestMapping(value="/updateprofile",method=RequestMethod.PUT)
	public ResponseEntity<?> updateProfile(@RequestBody Users users,HttpSession session)
	{
		Users user=(Users) session.getAttribute("user");
		if(user==null)
		{
			Error error=new Error(6,"Unauthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		userDao.updateUser(users);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
@RequestMapping(value="/login",method=RequestMethod.POST)
public ResponseEntity<?> login(@RequestBody Users users,HttpSession session){
	Users validUser=userDao.login(users);
	if(validUser==null){
		Error error=new Error(3,"Invalid username and password");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	else{
		validUser.setOnline(true);
		validUser=userDao.updateUser(validUser);
		session.setAttribute("user",validUser);
		return new ResponseEntity<Users>(validUser,HttpStatus.OK);
	}
}
@RequestMapping(value="/logout",method=RequestMethod.GET)
public ResponseEntity<?> logout(HttpSession session){
Users users=(Users)session.getAttribute("user");
if(users==null){
Error error=new Error(3,"Unauthorized user.");
return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
}
users.setOnline(false);
userDao.updateUser(users);
session.removeAttribute("user");
session.invalidate();
return new ResponseEntity<Void>(HttpStatus.OK);
}
}

