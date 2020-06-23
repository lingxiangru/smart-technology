package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.TeacherStudentDao;
import com.entity.TeacherStudent;

import static com.util.JDBC.*;

public class TeacherStudentDaoImpl implements TeacherStudentDao {

	@Override
	public int insert(TeacherStudent ts) throws SQLException {
		String sql = "insert into t_teacherStudent values(seq_teacherStudent.nextval,?,?,?,?)";
		return executeUpdate(sql, ts.getTname(),ts.getTcontent(),ts.getTimg(),ts.getTtype());
	}

	@Override
	public int delete(Integer id) throws SQLException {
		String sql = "delete from t_teacherStudent where t_id = ?";
		return executeUpdate(sql, id);
	}

	@Override
	public TeacherStudent select(Integer id) throws SQLException {
		String sql = "select * from t_teacherStudent where t_id = ?";
		TeacherStudent ts = null;
		ResultSet rs = executeQuery(sql, id);
		if(rs.next()) {
			ts = new TeacherStudent();
			ts.setTid(rs.getInt(1));
			ts.setTname(rs.getString(2));
			ts.setTcontent(rs.getString(3));
			ts.setTimg(rs.getString(4));
			ts.setTtype(rs.getInt(5));
		}
		return ts;
	}

	@Override
	public int selectCount(int type) throws SQLException {
		String sql = "select count(*) from t_teacherStudent where t_type = ?";
		ResultSet rs = executeQuery(sql, type);
		if(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	@Override
	public List<TeacherStudent> select(int type) throws SQLException {
		String sql = "select * from t_teacherStudent where t_type = ? order by t_id";
		List<TeacherStudent> list = new ArrayList<TeacherStudent>();
		ResultSet rs = executeQuery(sql, type);
		while(rs.next()) {
			TeacherStudent ts = new TeacherStudent();
			ts.setTid(rs.getInt(1));
			ts.setTname(rs.getString(2));
			ts.setTcontent(rs.getString(3));
			ts.setTimg(rs.getString(4));
			ts.setTtype(rs.getInt(5));
			list.add(ts);
		}
		return list;
	}

	@Override
	public List<TeacherStudent> select(int type, int begin, int end) throws SQLException {
		String sql = "select * from (select n.*,rownum r from"
				+ " (select * from t_teacherStudent where t_type = ?"
				+ " order by t_id) n) where r>? and r<=?";
		List<TeacherStudent> list = new ArrayList<TeacherStudent>();
		ResultSet rs = executeQuery(sql, type,begin,end);
		while(rs.next()) {
			TeacherStudent ts = new TeacherStudent();
			ts.setTid(rs.getInt(1));
			ts.setTname(rs.getString(2));
			ts.setTcontent(rs.getString(3));
			ts.setTimg(rs.getString(4));
			ts.setTtype(rs.getInt(5));
			list.add(ts);
		}
		return list;
	}

	@Override
	public int update(TeacherStudent ts) throws SQLException {
		String sql = "update t_teacherStudent set (t_name,t_content,t_img,t_type) = (select ?,?,?,? from dual) where t_id = ?";
		return executeUpdate(sql, ts.getTname(),ts.getTcontent(),ts.getTimg(),ts.getTtype(),ts.getTid());
	}

	@Override
	public List<TeacherStudent> select(int begin, int end) throws SQLException {
		String sql = "select * from (select n.*,rownum r from"
				+ " (select * from t_teacherStudent"
				+ " order by t_id) n) where r>? and r<=?";
		List<TeacherStudent> list = new ArrayList<TeacherStudent>();
		ResultSet rs = executeQuery(sql,begin,end);
		while(rs.next()) {
			TeacherStudent ts = new TeacherStudent();
			ts.setTid(rs.getInt(1));
			ts.setTname(rs.getString(2));
			ts.setTcontent(rs.getString(3));
			ts.setTimg(rs.getString(4));
			ts.setTtype(rs.getInt(5));
			list.add(ts);
		}
		return list;
	}

	@Override
	public int selectCount() throws SQLException {
		String sql = "select count(*) from t_teacherStudent";
		ResultSet rs = executeQuery(sql);
		if(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

}
