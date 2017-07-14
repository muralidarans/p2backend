package com.niit.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.Dao.JobDao;
import com.niit.model.Error;
import com.niit.model.Job;
import com.niit.model.Users;

@RestController
public class JobController {
	@Autowired
	private JobDao jobDao;
	@RequestMapping(value="/saveAllJob",method=RequestMethod.POST)
public ResponseEntity<?> saveJob(@RequestBody Job job,HttpSession session){
Users users=(Users)session.getAttribute("user");
if(users==null){
	Error error=new Error(3,"Unauthorized User");
	return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
}
try{
	if(users.getRole().equals("admin")){
		
		/*
		 * 
			 LocalDateTime now = LocalDateTime.now();

        System.out.println("Before : " + now);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = now.format(formatter);
		 */
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		job.setPostedon(now.format(formatter));
		jobDao.saveJob(job);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
else
{
	Error error=new Error(4,"Acess denied");
	return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
}
}
catch(Exception e){
	Error error= new Error(1,"Unable to insert job details"+e.getMessage());
	return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
}
}
	@RequestMapping(value="/getAllJobs",method=RequestMethod.GET)
public ResponseEntity<?>getAllJobs(HttpSession session){
		Users users=(Users)session.getAttribute("user");
		if(users==null){
			Error error=new Error(3,"Unauthorized User");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		List<Job> jobs=jobDao.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
		
	}
}