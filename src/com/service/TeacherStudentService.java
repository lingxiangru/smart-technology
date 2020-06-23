package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.TeacherStudentDao;
import com.dao.impl.TeacherStudentDaoImpl;
import com.entity.TeacherStudent;

public interface TeacherStudentService {
	TeacherStudentDao tsd = new TeacherStudentDaoImpl();

	public boolean append(TeacherStudent ts) throws SQLException;

	public boolean remove(Integer id) throws SQLException;

	public TeacherStudent getTeacherStudent(Integer id) throws SQLException;

	public List<TeacherStudent> getTeacherStudent(int type) throws SQLException;
	
	public List<TeacherStudent> getTeacherStudentPage(int type,int begin, int end) throws SQLException;
	
	public List<TeacherStudent> getTeacherStudentPage(int begin, int end) throws SQLException;
	
	public int getCount(int type) throws SQLException;
	
	public int getCount() throws SQLException;
	
	public boolean alter(TeacherStudent ts) throws SQLException;
}
