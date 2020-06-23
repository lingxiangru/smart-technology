package com.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.entity.News;
import com.service.NewsService;

public class NewsServiceImpl implements NewsService {

	@Override
	public boolean append(News n) throws SQLException {
		if (nd.insert(n) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Integer id) throws SQLException {
		if (nd.delete(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<News> getNews(String type) throws SQLException {
		return nd.select(type);
	}

	@Override
	public int getCount(String type) throws SQLException {
		return nd.selectCount(type);
	}

	@Override
	public List<News> getNewsPage(String type, int begin, int end) throws SQLException {
		return nd.select(type, begin, end);
	}

	@Override
	public boolean alter(News n) throws SQLException {
		if (nd.update(n) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public News getNews(Integer id) throws SQLException {
		return nd.select(id);
	}

}
