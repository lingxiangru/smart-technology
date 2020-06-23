package com.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.entity.Authority;
import com.service.AuthorityService;

public class AuthorityServiceImpl implements AuthorityService {

	@Override
	public boolean append(Authority a) throws SQLException {
		if(ad.insert(a)>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean alter(Authority a) throws SQLException {
		if(ad.update(a)>0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Integer id) throws SQLException {
		if(ad.delete(id)>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Authority> getParentAuthority() throws SQLException {
		return ad.select();
	}

	@Override
	public List<Authority> getChildAuthority(Integer pid) throws SQLException {
		return ad.selectchild(pid);
	}

	@Override
	public Authority getAuthority(Integer id) throws SQLException {
		return ad.select(id);
	}

	@Override
	public boolean removeChild(Integer pid) throws SQLException {
		if(ad.delchild(pid)>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Authority> getAllAuthority() throws SQLException {
		List<Authority> parent = ad.select();
		for(Authority p:parent) {
			List<Authority> children = ad.selectchild(p.getAid());
			p.setChildren(children);
		}
		return parent;
	}

	@Override
	public List<Authority> getUserAuthority(Integer uid) throws SQLException {
		List<Authority> parent = ad.selectByUid(uid, 0);
		for(Authority p:parent) {
			List<Authority> children = ad.selectByUid(uid,p.getAid());
			p.setChildren(children);
		}
		return parent;
	}

}
