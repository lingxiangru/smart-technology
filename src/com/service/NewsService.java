package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.NewsDao;
import com.dao.impl.NewsDaoImpl;
import com.entity.News;

public interface NewsService {
	NewsDao nd = new NewsDaoImpl();

	public boolean append(News n) throws SQLException;

	public boolean remove(Integer id) throws SQLException;

	public News getNews(Integer id) throws SQLException;

	public List<News> getNews(String type) throws SQLException;
	
	public List<News> getNewsPage(String type,int begin, int end) throws SQLException;
	
	public int getCount(String type) throws SQLException;
	
	public boolean alter(News n) throws SQLException;
	
}
