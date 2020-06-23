package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.entity.Authority;

public interface AuthorityDao {
	public int insert(Authority a) throws SQLException;

	public int update(Authority a) throws SQLException;

	public int delete(Integer id) throws SQLException;
	
	public int delchild(Integer pid) throws SQLException;

	public List<Authority> select() throws SQLException;

	public List<Authority> selectByUid(Integer uid,Integer aid) throws SQLException;
	
	public Authority select(Integer id) throws SQLException;

	public List<Authority> selectchild(Integer pid) throws SQLException;
}
