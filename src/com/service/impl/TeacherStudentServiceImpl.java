package com.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.entity.TeacherStudent;
import com.service.TeacherStudentService;

public class TeacherStudentServiceImpl implements TeacherStudentService {

	@Override
	public boolean append(TeacherStudent ts) throws SQLException {
		int row = tsd.insert(ts);
		if(row>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Integer id) throws SQLException {
		int row = tsd.delete(id);
		if(row>0) {
			return true;
		}
		return false;
	}

	@Override
	public TeacherStudent getTeacherStudent(Integer id) throws SQLException {
		return tsd.select(id);
	}

	@Override
	public List<TeacherStudent> getTeacherStudent(int type) throws SQLException {
		return tsd.select(type);
	}

	@Override
	public List<TeacherStudent> getTeacherStudentPage(int type, int begin, int end) throws SQLException {
		return tsd.select(type,begin,end);
	}

	@Override
	public int getCount(int type) throws SQLException {
		return tsd.selectCount(type);
	}

	@Override
	public boolean alter(TeacherStudent ts) throws SQLException {
		int row = tsd.update(ts);
		if(row>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<TeacherStudent> getTeacherStudentPage(int begin, int end) throws SQLException {
		return tsd.select(begin,end);
	}

	@Override
	public int getCount() throws SQLException {
		return tsd.selectCount();
	}

}
