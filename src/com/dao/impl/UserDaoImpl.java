package com.dao.impl;

import com.dao.UserDao;
import com.entity.Authority;
import com.entity.User;
import static com.util.JDBC.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDaoImpl implements UserDao {

	@Override
	public int insert(User u) throws SQLException {
		String sql = "insert into t_user values(seq_user.nextval,?,?)";
		return executeUpdate(sql, u.getUname(),u.getUpwd());
	}

	@Override
	public int delete(Integer id) throws SQLException {
		String sql = "delete from t_user where u_id = ?";
		return executeUpdate(sql, id);
	}

	@Override
	public List<User> select() throws SQLException {
		String sql = "select * from t_user order by u_id";
		List<User> list = new ArrayList<User>();
		ResultSet rs = executeQuery(sql);
		while(rs.next()) {
			User u = new User();
			u.setUid(rs.getInt(1));
			u.setUname(rs.getString(2));
			u.setUpwd(rs.getString(3));
			list.add(u);
		}
		return list;
	}

	@Override
	public User select(Integer id) throws SQLException {
		String sql = "select * from t_user where u_id = ?";
		ResultSet rs = executeQuery(sql,id);
		User u = null;
		if(rs.next()) {
			u = new User();
			u.setUid(rs.getInt(1));
			u.setUname(rs.getString(2));
			u.setUpwd(rs.getString(3));
		}
		return u;
	}

	@Override
	public User select(String name, String pwd) throws SQLException {
		String sql = "select * from t_user where u_name = ? and u_pwd = ?";
		ResultSet rs = executeQuery(sql,name,pwd);
		User u = null;
		if(rs.next()) {
			u = new User();
			u.setUid(rs.getInt(1));
			u.setUname(rs.getString(2));
			u.setUpwd(rs.getString(3));
			List<Authority> authoritys = new ArrayList<Authority>();
			String newsql = "select a.a_id,a.a_authority,a.a_parentid,a.a_location from "
					+ "(select * from t_user natural join t_permissionstable natural join t_authority) a "
					+ "where u_id = ? order by a.a_id";
			ResultSet newrs = executeQuery(newsql, u.getUid());
			while(newrs.next()) {
				Authority a = new Authority();
				a.setAid(newrs.getInt(1));
				a.setAauthority(newrs.getString(2));
				a.setAparentid(newrs.getInt(3));
				a.setAlocation(newrs.getString(4));
				authoritys.add(a);
			}
			List<Authority> newAuthoritys = authoritys.stream().map(a->{
				if(a.getAparentid()==0) {
					List<Authority> children = authoritys.stream().filter(child->child.getAparentid()==a.getAid()).collect(Collectors.toList());
					a.setChildren(children);
				}
				return a;
			}).filter(a->a.getAparentid()==0).collect(Collectors.toList());
			u.setAuthoritys(newAuthoritys);
		}
		return u;
	}

	@Override
	public int update(User u) throws SQLException {
		String sql = "update t_user set (u_name,u_pwd) = (select ?,? from dual) where u_id = ?";
		return executeUpdate(sql, u.getUname(),u.getUpwd(),u.getUid());
	}

}
