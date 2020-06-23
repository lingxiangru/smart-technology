package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.PermissionsTableDao;
import com.entity.PermissionsTable;
import static com.util.JDBC.*;

public class PermissionsTableDaoImpl implements PermissionsTableDao{

	@Override
	public int insert(PermissionsTable pt) throws SQLException {
		String sql = "insert into t_permissionsTable values(?,?)";
		return executeUpdate(sql, pt.getUid(),pt.getAid());
	}

	@Override
	public int delete(PermissionsTable pt) throws SQLException {
		String sql = "delete from t_permissionsTable where u_id = ? and a_id = ?";
		return executeUpdate(sql, pt.getUid(),pt.getAid());
	}

	@Override
	public PermissionsTable select(PermissionsTable pt) throws SQLException {
		String sql = "select * from t_permissionsTable where u_id = ? and a_id = ?";
		ResultSet rs = executeQuery(sql, pt.getUid(),pt.getAid());
		PermissionsTable result = null;
		while(rs.next()) {
			result = new PermissionsTable();
			result.setUid(rs.getInt(1));
			result.setAid(rs.getInt(2));
		}
		return result;
	}

	@Override
	public List<PermissionsTable> select(Integer uid) throws SQLException {
		String sql = "select * from t_permissionsTable where u_id = ?";
		ResultSet rs = executeQuery(sql, uid);
		List<PermissionsTable> list = new ArrayList<PermissionsTable>();
		while(rs.next()) {
			PermissionsTable pt = new PermissionsTable();
			pt.setUid(rs.getInt(1));
			pt.setAid(rs.getInt(2));
			list.add(pt);
		}
		return list;
	}

}
