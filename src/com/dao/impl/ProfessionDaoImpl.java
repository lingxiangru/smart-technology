package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.ProfessionDao;
import com.entity.Profession;

import static com.util.JDBC.*;

public class ProfessionDaoImpl implements ProfessionDao {

	@Override
	public int insert(Profession p) throws SQLException {
		String sql = "insert into t_profession values(seq_profession.nextval,?,?,?)";
		return executeUpdate(sql, p.getPname(),p.getPimg(),p.getPcontent());
	}

	@Override
	public int update(Profession p) throws SQLException {
		String sql = "update t_profession set (p_name,p_img,p_content) = (select ?,?,? from dual) where p_id = ?";
		return executeUpdate(sql, p.getPname(),p.getPimg(),p.getPcontent(),p.getPid());
	}

	@Override
	public int delete(Integer id) throws SQLException {
		String sql = "delete from t_profession where p_id = ?";
		return executeUpdate(sql, id);
	}

	@Override
	public List<Profession> select() throws SQLException {
		String sql = "select * from t_profession order by p_id";
		List<Profession> list = new ArrayList<Profession>();
		ResultSet rs = executeQuery(sql);
		while(rs.next()) {
			Profession p = new Profession();
			p.setPid(rs.getInt(1));
			p.setPname(rs.getString(2));
			p.setPimg(rs.getString(3));
			p.setPcontent(rs.getString(4));
			list.add(p);
		}
		return list;
	}

	@Override
	public Profession select(Integer id) throws SQLException {
		String sql = "select * from t_profession where p_id = ?";
		ResultSet rs = executeQuery(sql,id);
		Profession p = null;
		if(rs.next()) {
			p = new Profession();
			p.setPid(rs.getInt(1));
			p.setPname(rs.getString(2));
			p.setPimg(rs.getString(3));
			p.setPcontent(rs.getString(4));
		}
		return p;
	}

}
