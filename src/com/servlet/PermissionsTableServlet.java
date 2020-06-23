package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Authority;
import com.entity.PermissionsTable;
import com.google.gson.Gson;
import com.service.AuthorityService;
import com.service.PermissionsTableService;
import com.service.impl.AuthorityServiceImpl;
import com.service.impl.PermissionsTableServiceImpl;

@WebServlet("/permissionsTable")
public class PermissionsTableServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	PermissionsTableService pts = new PermissionsTableServiceImpl();
	AuthorityService as = new AuthorityServiceImpl();
	Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String dos = req.getParameter("do");
		switch (dos) {
		case "add":
			add(req, resp);
			break;
		case "del":
			del(req, resp);
			break;
		}
	}
	
	public void add(HttpServletRequest req, HttpServletResponse resp) {
		try {
			PermissionsTable pt = new PermissionsTable();
			Integer uid = Integer.parseInt(req.getParameter("uid"));
			Integer aid = Integer.parseInt(req.getParameter("aid"));
			pt.setUid(uid);
			pt.setAid(aid);
			//获取当前权限的对应Authority
			Authority a = as.getAuthority(aid);
			//当添加的权限为父权限a.getAparentid()==0
			if(a.getAparentid()==0) {
				//添加父权限
				if(pts.append(pt)) {
					//获取父权限所有子权限
					List<Authority> children = as.getChildAuthority(aid);
					int count = 0;
					//添加所有子权限
					for(Authority ca:children) {
						PermissionsTable cpt = new PermissionsTable();
						cpt.setUid(uid);
						cpt.setAid(ca.getAid());
						if(!pts.append(cpt)) {
							break;
						}
						count++;
					}
					//子权限全部添加完毕count==children.size()
					if(count==children.size()) {
						resp.getWriter().print(true);
					}else {
						resp.getWriter().print(false);
					}
				}else {
					resp.getWriter().print(false);
				}
			}else {
				//判断父权限是否存在
				PermissionsTable ppt = new PermissionsTable();
				ppt.setUid(uid);
				ppt.setAid(a.getAparentid());
				if(pts.isEmpty(ppt)) {
					//存在,直接添加子权限
					if(pts.append(pt)) {
						resp.getWriter().print(true);
					}else {
						resp.getWriter().print(false);
					}
				}else {
					//不存在,分别添加父权限和子权限
					if(pts.append(ppt)) {
						if(pts.append(pt)) {
							resp.getWriter().print(true);
						}else {
							resp.getWriter().print(false);
						}
					}else {
						resp.getWriter().print(false);
					}
				}
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void del(HttpServletRequest req, HttpServletResponse resp) {
		try {
			PermissionsTable pt = new PermissionsTable();
			Integer uid = Integer.parseInt(req.getParameter("uid"));
			Integer aid = Integer.parseInt(req.getParameter("aid"));
			pt.setUid(uid);
			pt.setAid(aid);
			//获取当前权限的对应Authority
			Authority a = as.getAuthority(aid);
			//当添加的权限为父权限a.getAparentid()==0
			if(a.getAparentid()==0) {
				List<Authority> children = as.getChildAuthority(aid);
				List<PermissionsTable> uptlist = pts.getPermissionsTableByUid(uid);
				for(Authority ca:children) {
					for(PermissionsTable upt:uptlist) {
						if(ca.getAid()==upt.getAid()) {
							PermissionsTable del = new PermissionsTable();
							del.setUid(uid);
							del.setAid(upt.getAid());
							if(!pts.remove(del)) {
								resp.getWriter().print(false);
								return;
							}
						}
					}
					if(pts.remove(pt)) {
						resp.getWriter().print(true);
					}else {
						resp.getWriter().print(false);
					}
				}
			}else {
				Authority ca = as.getAuthority(pt.getAid());
				Authority pa = as.getAuthority(ca.getAparentid());
				List<Authority> children = as.getChildAuthority(pa.getAid());
				List<PermissionsTable> uptlist = pts.getPermissionsTableByUid(uid);
				List<PermissionsTable> temp = new ArrayList<PermissionsTable>();
				PermissionsTable ppt = new PermissionsTable();
				ppt.setUid(uid);
				ppt.setAid(pa.getAid());
				for(Authority pca:children) {
					for(PermissionsTable upt:uptlist) {
						if(pca.getAid()==upt.getAid()) {
							temp.add(upt);
						}
					}
				}
				if(temp.size()>1) {
					if(pts.remove(pt)) {
						resp.getWriter().print(true);
					}else {
						resp.getWriter().print(false);
					}
				}else {
					if(pts.remove(pt)) {
						if(pts.remove(ppt)) {
							resp.getWriter().print(true);
						}else {
							resp.getWriter().print(false);
						}
					}else {
						resp.getWriter().print(false);
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
