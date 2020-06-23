package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.entity.News;

public interface NewsDao {
	public int insert(News n) throws SQLException;

	public int delete(Integer id) throws SQLException;

	public News select(Integer id) throws SQLException;

	public int selectCount(String type) throws SQLException;

	public List<News> select(String type) throws SQLException;

	public List<News> select(String type, int begin, int end) throws SQLException;

	public List<News> selectByDate(String type, int begin, int end) throws SQLException;
	
	public int update(News n) throws SQLException;
}
