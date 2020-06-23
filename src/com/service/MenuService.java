package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.MenuDao;
import com.dao.impl.MenuDaoImpl;
import com.entity.Menu;

public interface MenuService {
	MenuDao md = new MenuDaoImpl();

	public List<Menu> getMenu() throws SQLException;

	public Menu getMenu(Integer id) throws SQLException;

	public boolean append(Menu m) throws SQLException;

	public boolean alter(Menu m) throws SQLException;

	public boolean remove(Integer id) throws SQLException;
}
