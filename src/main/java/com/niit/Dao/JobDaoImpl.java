package com.niit.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Job;
import com.niit.model.Users;

@Repository
public class JobDaoImpl implements JobDao {
@Autowired
private SessionFactory sessionFactory;
public void saveJob(Job job){
	Session session=sessionFactory.openSession();
	session.save(job);
	session.flush();
	session.close();

}

public List<Job> getAllJobs() {
	Session session=sessionFactory.openSession();
	Query query=session.createQuery("from Job");
	List<Job> jobs=query.list();
	session.close();
	return jobs;
}

}
