package com.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.entity.Menu;
import com.service.MenuService;

public class MenuServiceImpl implements MenuService {

	@Override
	public List<Menu> getMenu() throws SQLException {
		return md.select();
	}

	@Override
	public boolean append(Menu m) throws SQLException {
		int row = 0;
		row = md.insert(m);
		if(row>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Integer id) throws SQLException {
		int row = md.delete(id);
		if(row>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean alter(Menu m) throws SQLException {
		int row = md.update(m);
		if(row>0) {
			return true;
		}
		return false;
	}

	@Override
	public Menu getMenu(Integer id) throws SQLException {
		return md.select(id);
	}

}
