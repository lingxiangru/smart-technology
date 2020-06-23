package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.NewsDao;
import com.entity.News;

import static com.util.JDBC.*;

public class NewsDaoImpl implements NewsDao {

	@Override
	public int insert(News n) throws SQLException {
		String sql = "insert into t_news values(seq_news.nextval,?,?,?,?,?,sysdate,?)";
		return executeUpdate(sql, n.getNtitle(), n.getNintr(), n.getNcontent(), n.getNimg(), n.getNpublisher(),
				n.getNtype());
	}

	@Override
	public int delete(Integer id) throws SQLException {
		String sql = "delete from t_news where n_id = ?";
		return executeUpdate(sql, id);
	}

	@Override
	public News select(Integer id) throws SQLException {
		String sql = "select * from t_news where n_id = ?";
		ResultSet rs = executeQuery(sql, id);
		News n = null;
		if (rs.next()) {
			n = new News();
			n.setNid(rs.getInt(1));
			n.setNtitle(rs.getString(2));
			n.setNintr(rs.getString(3));
			n.setNcontent(rs.getString(4));
			n.setNimg(rs.getString(5));
			n.setNpublisher(rs.getString(6));
			n.setNdate(rs.getDate(7));
			n.setNtype(rs.getString(8));
		}
		return n;
	}

	@Override
	public List<News> select(String type) throws SQLException {
		String sql = "select * from t_news where n_type = ? order by n_date desc";
		ResultSet rs = executeQuery(sql, type);
		List<News> list = new ArrayList<News>();
		while (rs.next()) {
			News n = new News();
			n.setNid(rs.getInt(1));
			n.setNtitle(rs.getString(2));
			n.setNintr(rs.getString(3));
			n.setNcontent(rs.getString(4));
			n.setNimg(rs.getString(5));
			n.setNpublisher(rs.getString(6));
			n.setNdate(rs.getDate(7));
			n.setNtype(rs.getString(8));
			list.add(n);
		}
		return list;
	}

	@Override
	public List<News> select(String type, int begin, int end) throws SQLException {
		String sql = "select * from (select n.*,rownum r from" + " (select * from t_news where n_type = ?"
				+ " order by n_id desc) n) where r>? and r<=?";
		ResultSet rs = executeQuery(sql, type, begin, end);
		List<News> list = new ArrayList<News>();
		while (rs.next()) {
			News n = new News();
			n.setNid(rs.getInt(1));
			n.setNtitle(rs.getString(2));
			n.setNintr(rs.getString(3));
			n.setNcontent(rs.getString(4));
			n.setNimg(rs.getString(5));
			n.setNpublisher(rs.getString(6));
			n.setNdate(rs.getDate(7));
			n.setNtype(rs.getString(8));
			list.add(n);
		}
		return list;
	}

	@Override
	public int selectCount(String type) throws SQLException {
		String sql = "select count(*) from t_news where n_type = ?";
		ResultSet rs = executeQuery(sql, type);
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public int update(News n) throws SQLException {
		String sql = "update t_news set (n_title,n_intr,n_content,n_img,n_publisher,n_date,n_type)=(select ?,?,?,?,?,sysdate,? from dual) where n_id = ?";
		return executeUpdate(sql, n.getNtitle(), n.getNintr(), n.getNcontent(), n.getNimg(), n.getNpublisher(),
				n.getNtype(), n.getNid());
	}

	@Override
	public List<News> selectByDate(String type, int begin, int end) throws SQLException {
		String sql = "select * from (select n.*,rownum r from" + " (select * from t_news where n_type = ?"
				+ " order by n_date desc) n) where r>? and r<=?";
		ResultSet rs = executeQuery(sql, type, begin, end);
		List<News> list = new ArrayList<News>();
		while (rs.next()) {
			News n = new News();
			n.setNid(rs.getInt(1));
			n.setNtitle(rs.getString(2));
			n.setNintr(rs.getString(3));
			n.setNcontent(rs.getString(4));
			n.setNimg(rs.getString(5));
			n.setNpublisher(rs.getString(6));
			n.setNdate(rs.getDate(7));
			n.setNtype(rs.getString(8));
			list.add(n);
		}
		return list;
	}

}
