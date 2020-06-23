package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.PermissionsTableDao;
import com.dao.impl.PermissionsTableDaoImpl;
import com.entity.PermissionsTable;

public interface PermissionsTableService {
	PermissionsTableDao ptd = new PermissionsTableDaoImpl();

	public boolean append(PermissionsTable pt) throws SQLException;

	public boolean remove(PermissionsTable pt) throws SQLException;
	
	public boolean isEmpty(PermissionsTable pt) throws SQLException;
	
	public List<PermissionsTable> getPermissionsTableByUid(Integer uid) throws SQLException;
}
