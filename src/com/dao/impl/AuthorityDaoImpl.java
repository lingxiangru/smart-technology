package com.dao.impl;

import static com.util.JDBC.executeQuery;
import static com.util.JDBC.executeUpdate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.AuthorityDao;
import com.entity.Authority;

public class AuthorityDaoImpl implements AuthorityDao {

	@Override
	public int insert(Authority a) throws SQLException {
		String sql = "insert into t_authority values(seq_authority.nextval,?,?,?)";
		return executeUpdate(sql, a.getAauthority(),a.getAparentid(),a.getAlocation());
	}

	@Override
	public int update(Authority a) throws SQLException {
		String sql = "update t_authority set (a_authority,a_parentid,a_location) = (select ?,?,? from dual) where a_id = ?";
		return executeUpdate(sql, a.getAauthority(),a.getAparentid(),a.getAlocation(),a.getAid());
	}

	@Override
	public int delete(Integer id) throws SQLException {
		String sql = "delete from t_authority where a_id = ?";
		return executeUpdate(sql, id);
	}
	
	@Override
	public int delchild(Integer pid) throws SQLException {
		String sql = "delete from t_authority where a_parentid = ?";
		return executeUpdate(sql, pid);
	}

	@Override
	public List<Authority> select() throws SQLException {
		String sql = "select * from t_authority where a_parentid = 0 order by a_id";
		ResultSet rs = executeQuery(sql);
		List<Authority> list = new ArrayList<Authority>();
		while(rs.next()) {
			Authority a = new Authority();
			a.setAid(rs.getInt(1));
			a.setAauthority(rs.getString(2));
			a.setAparentid(rs.getInt(3));
			a.setAlocation(rs.getString(4));
			list.add(a);
		}
		return list;
	}

	@Override
	public Authority select(Integer id) throws SQLException {
		String sql = "select * from t_authority where a_id = ?";
		ResultSet rs = executeQuery(sql,id);
		Authority a = null;
		if(rs.next()) {
			a = new Authority();
			a.setAid(rs.getInt(1));
			a.setAauthority(rs.getString(2));
			a.setAparentid(rs.getInt(3));
			a.setAlocation(rs.getString(4));
		}
		return a;
	}

	@Override
	public List<Authority> selectchild(Integer pid) throws SQLException {
		String sql = "select * from t_authority where a_parentid = ? order by a_id";
		ResultSet rs = executeQuery(sql,pid);
		List<Authority> list = new ArrayList<Authority>();
		while(rs.next()) {
			Authority a = new Authority();
			a.setAid(rs.getInt(1));
			a.setAauthority(rs.getString(2));
			a.setAparentid(rs.getInt(3));
			a.setAlocation(rs.getString(4));
			list.add(a);
		}
		return list;
	}

	@Override
	public List<Authority> selectByUid(Integer uid,Integer aid) throws SQLException {
		String sql = "select * from t_authority a left join (select * from t_permissionstable where u_id = ?) p on a.a_id = p.a_id where a_parentid = ?";
		ResultSet rs = executeQuery(sql,uid,aid);
		List<Authority> list = new ArrayList<Authority>();
		while(rs.next()) {
			Authority a = new Authority();
			a.setAid(rs.getInt(1));
			a.setAauthority(rs.getString(2));
			a.setAparentid(rs.getInt(3));
			a.setAlocation(rs.getString(4));
			a.setChecked(rs.getString(5)==null?false:true);
			list.add(a);
		}
		return list;
	}

}
