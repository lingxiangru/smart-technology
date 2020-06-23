package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.User;

public interface UserService {
	UserDao ud = new UserDaoImpl();

	public User login(String name, String pwd) throws SQLException;

	public List<User> show() throws SQLException;
	
	public boolean append(User u) throws SQLException;
	
	public boolean remove(Integer id) throws SQLException;
	
	public boolean alter(User u) throws SQLException;
	
	public User getUser(Integer id) throws SQLException;
}
