package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.entity.TeacherStudent;

public interface TeacherStudentDao {
	public int insert(TeacherStudent ts) throws SQLException;

	public int delete(Integer id) throws SQLException;

	public TeacherStudent select(Integer id) throws SQLException;

	public int selectCount(int type) throws SQLException;
	
	public int selectCount() throws SQLException;

	public List<TeacherStudent> select(int type) throws SQLException;

	public List<TeacherStudent> select(int type, int begin, int end) throws SQLException;
	
	public List<TeacherStudent> select(int begin, int end) throws SQLException;

	public int update(TeacherStudent ts) throws SQLException;
}
