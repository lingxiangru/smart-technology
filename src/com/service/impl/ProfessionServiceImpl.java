package com.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.entity.Profession;
import com.service.ProfessionService;

public class ProfessionServiceImpl implements ProfessionService {

	@Override
	public List<Profession> getProfession() throws SQLException {
		return pd.select();
	}

	@Override
	public Profession getProfession(Integer id) throws SQLException {
		return pd.select(id);
	}

	@Override
	public boolean append(Profession p) throws SQLException {
		if (pd.insert(p) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean alter(Profession p) throws SQLException {
		if (pd.update(p) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Integer id) throws SQLException {
		if (pd.delete(id) > 0) {
			return true;
		}
		return false;
	}

}
