package com.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.entity.PermissionsTable;
import com.service.PermissionsTableService;

public class PermissionsTableServiceImpl implements PermissionsTableService {

	@Override
	public boolean append(PermissionsTable pt) throws SQLException {
		if(ptd.insert(pt)>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(PermissionsTable pt) throws SQLException {
		if(ptd.delete(pt)>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty(PermissionsTable pt) throws SQLException {
		if(ptd.select(pt)!=null) {
			return true;
		}
		return false;
	}

	@Override
	public List<PermissionsTable> getPermissionsTableByUid(Integer uid) throws SQLException {
		return ptd.select(uid);
	}

}
