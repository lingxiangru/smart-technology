package com.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.entity.User;
import com.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User login(String name, String pwd) throws SQLException {
		return ud.select(name, pwd);
	}

	@Override
	public List<User> show() throws SQLException {
		return ud.select();
	}

	@Override
	public boolean append(User u) throws SQLException {
		if(ud.insert(u)>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Integer id) throws SQLException {
		if(ud.delete(id)>0) {
			return true;
		}
		return false;
	}

	@Override
	public User getUser(Integer id) throws SQLException {
		return ud.select(id);
	}

	@Override
	public boolean alter(User u) throws SQLException {
		if(ud.update(u)>0) {
			return true;
		}
		return false;
	}

}
