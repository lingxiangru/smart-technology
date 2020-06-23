package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.entity.Menu;

public interface MenuDao {
	public int insert(Menu m) throws SQLException;
	public int update(Menu m) throws SQLException;
	public int delete(Integer id) throws SQLException;
	public List<Menu> select()throws SQLException;
	public Menu select(Integer id)throws SQLException;
}
