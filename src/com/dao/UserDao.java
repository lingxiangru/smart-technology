package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.entity.User;

public interface UserDao {
	public int insert(User u) throws SQLException;
	
	public int update(User u) throws SQLException;

	public int delete(Integer id) throws SQLException;

	public List<User> select() throws SQLException;

	public User select(Integer id) throws SQLException;

	public User select(String name, String pwd) throws SQLException;
}
