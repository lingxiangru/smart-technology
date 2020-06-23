package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.entity.PermissionsTable;

public interface PermissionsTableDao {
	public int insert(PermissionsTable pt) throws SQLException;

	public int delete(PermissionsTable pt) throws SQLException;
	
	public PermissionsTable select(PermissionsTable pt) throws SQLException;
	
	public List<PermissionsTable> select(Integer uid) throws SQLException;
}
