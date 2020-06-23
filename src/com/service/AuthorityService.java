package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.AuthorityDao;
import com.dao.impl.AuthorityDaoImpl;
import com.entity.Authority;

public interface AuthorityService {
	AuthorityDao ad = new AuthorityDaoImpl();
	
	public List<Authority> getAllAuthority() throws SQLException;
	
	public List<Authority> getUserAuthority(Integer uid) throws SQLException;
	
	public List<Authority> getParentAuthority() throws SQLException;
	
	public List<Authority> getChildAuthority(Integer pid) throws SQLException;

	public Authority getAuthority(Integer id) throws SQLException;

	public boolean append(Authority a) throws SQLException;

	public boolean alter(Authority a) throws SQLException;

	public boolean remove(Integer id) throws SQLException;
	
	public boolean removeChild(Integer pid) throws SQLException;
}
