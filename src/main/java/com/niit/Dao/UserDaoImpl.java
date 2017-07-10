package com.niit.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Users;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;
	public List<Users> getAllUsers() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Users");
		List<Users> user=query.list();
		session.close();
		return user;
	
	}
public void saveUsers(Users user){
	Session session=sessionFactory.openSession();
	session.save(user);
	session.flush();
	session.close();
}
public Users login(Users users) {
	Session session=sessionFactory.openSession();
	Query query=session.createQuery("from Users where username=? and password=? and enabled=?");
	query.setString(0, users.getUsername());
	query.setString(1,users.getPassword());
	query.setBoolean(2, true);
	Users validUsers=(Users)query.uniqueResult();
	session.close();
	return validUsers;
}
public Users updateUser(Users validUser) {
	Session session=sessionFactory.openSession();
	session.update(validUser);
	session.flush();
	session.close();
	return validUser;
}

}
