package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.entity.Profession;

public interface ProfessionDao {
	public int insert(Profession p) throws SQLException;

	public int update(Profession p) throws SQLException;

	public int delete(Integer id) throws SQLException;

	public List<Profession> select() throws SQLException;

	public Profession select(Integer id) throws SQLException;
}
