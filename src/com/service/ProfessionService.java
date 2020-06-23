package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.ProfessionDao;
import com.dao.impl.ProfessionDaoImpl;
import com.entity.Profession;

public interface ProfessionService {

	ProfessionDao pd = new ProfessionDaoImpl();

	public List<Profession> getProfession() throws SQLException;

	public Profession getProfession(Integer id) throws SQLException;

	public boolean append(Profession p) throws SQLException;

	public boolean alter(Profession p) throws SQLException;

	public boolean remove(Integer id) throws SQLException;
}
