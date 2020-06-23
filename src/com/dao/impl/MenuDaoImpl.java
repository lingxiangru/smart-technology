package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.MenuDao;
import com.entity.Menu;

import static com.util.JDBC.*;

public class MenuDaoImpl implements MenuDao {

	@Override
	public int insert(Menu m) throws SQLException {
		String sql = "insert into t_menu values(seq_menu.nextval,?,?)";
		return executeUpdate(sql, m.getMname(),m.getMlink());
	}

	@Override
	public int delete(Integer id) throws SQLException {
		String sql = "delete from t_menu where m_id = ?";
		return executeUpdate(sql, id);
	}

	@Override
	public List<Menu> select() throws SQLException {
		String sql = "select * from t_menu order by m_id desc";
		ResultSet rs = executeQuery(sql);
		List<Menu> list = new ArrayList<Menu>();
		while(rs.next()) {
			Menu m = new Menu();
			m.setMid(rs.getInt(1));
			m.setMname(rs.getString(2));
			m.setMlink(rs.getString(3));
			list.add(m);
		}
		return list;
	}

	@Override
	public int update(Menu m) throws SQLException {
		String sql = "update t_menu set (m_name,m_link) = (select ?,? from dual) where m_id = ?";
		return executeUpdate(sql, m.getMname(),m.getMlink(),m.getMid());
	}

	@Override
	public Menu select(Integer id) throws SQLException {
		String sql = "select * from t_menu where m_id = ?";
		ResultSet rs = executeQuery(sql,id);
		Menu m = null;
		if(rs.next()) {
			m = new Menu();
			m.setMid(rs.getInt(1));
			m.setMname(rs.getString(2));
			m.setMlink(rs.getString(3));
		}
		return m;
	}

}
