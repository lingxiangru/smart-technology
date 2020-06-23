package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Authority;
import com.google.gson.Gson;
import com.service.AuthorityService;
import com.service.impl.AuthorityServiceImpl;

@WebServlet("/authority")
public class AuthorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AuthorityService as = new AuthorityServiceImpl();
	Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=utf-8");
		String dos = req.getParameter("do");
		switch (dos) {
		case "getAuthority":
			getAuthority(req, resp);
			break;
		case "getAllAuthority":
			getAllAuthority(req, resp);
			break;
		case "getUserAuthority":
			getUserAuthority(req, resp);
			break;
		case "setParent":
			setParent(req, resp);
			break;
		case "getChild":
			getChild(req, resp);
			break;
		case "del":
			del(req, resp);
			break;
		case "add":
			add(req, resp);
			break;
		case "alter":
			alter(req, resp);
			break;
		case "setEdit":
			setEdit(req, resp);
			break;
		case "getEdit":
			getEdit(req, resp);
			break;
		}
	}

	private void getUserAuthority(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Integer uid = Integer.parseInt(req.getParameter("id"));
			List<Authority> list = as.getUserAuthority(uid);
			for(Authority a:list) {
				a.setChecked(false);
			}
			String result = gson.toJson(list);
			resp.getWriter().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getAllAuthority(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<Authority> list = as.getAllAuthority();
			resp.getWriter().print(gson.toJson(list));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setParent(HttpServletRequest req, HttpServletResponse resp) {
		Integer pid = Integer.parseInt(req.getParameter("id"));
		Authority parent;
		try {
			parent = as.getAuthority(pid);
			if(parent!=null) {
				req.getSession().setAttribute("parentAuthority", parent);
				resp.getWriter().print(true);
			}else {
				resp.getWriter().print(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getEdit(HttpServletRequest req, HttpServletResponse resp) {
		Authority a = (Authority) req.getSession().getAttribute("authorityEdit");
		try {
			resp.getWriter().print(gson.toJson(a));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setEdit(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Integer id = Integer.parseInt(req.getParameter("id"));
			Authority a = as.getAuthority(id);
			if(a!=null) {
				req.getSession().setAttribute("authorityEdit", a);
				resp.getWriter().print(true);
			}else {
				resp.getWriter().print(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void alter(HttpServletRequest req, HttpServletResponse resp) {
		try {
			@SuppressWarnings("unchecked")
			HashMap<String, Object> map = gson.fromJson(req.getParameter("data"), HashMap.class);
			Authority a  = new Authority();
			a.setAid(Integer.parseInt(map.get("id").toString()));
			a.setAauthority(map.get("authority").toString());
			a.setAparentid(Integer.parseInt(map.get("parentid").toString()));
			a.setAlocation(map.get("location").toString());
			a.setChildren(as.getChildAuthority(a.getAid()));
			if(a.getAid()!=null) {
				if(as.alter(a)) {
					resp.getWriter().print(true);
				}else {
					resp.getWriter().print(false);
				}
			}else {
				resp.getWriter().print(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest req, HttpServletResponse resp) {
		Authority a = gson.fromJson(req.getParameter("data"), Authority.class);
		try {
			if(a!=null) {
				if(as.append(a)) {
					resp.getWriter().print(true);
				}else {
					resp.getWriter().print(false);
				}
			}else {
				resp.getWriter().print(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getChild(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Authority parent = (Authority)req.getSession().getAttribute("parentAuthority");
			if(parent!=null) {
				List<Authority> list = as.getChildAuthority(parent.getAid());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", 0);
				map.put("msg", "子级权限");
				map.put("count", list.size());
				map.put("data", list);
				req.getSession().setAttribute("childAuthorityList", list);
				resp.getWriter().print(gson.toJson(map));
			}else {
				resp.getWriter().print("<script>alert('查询失败!');</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void del(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Integer id = Integer.parseInt(req.getParameter("id"));
			Authority a = as.getAuthority(id);
			if (a.getAparentid() == 0) {
				if (as.removeChild(a.getAid())) {
					if(as.remove(a.getAid())) {
						resp.getWriter().print(true);
					}else {
						resp.getWriter().print(false);
					}
				}else {
					resp.getWriter().print(false);
				}
			}else {
				if(as.remove(a.getAid())) {
					resp.getWriter().print(true);
				}else {
					resp.getWriter().print(false);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getAuthority(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<Authority> list = as.getParentAuthority();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("msg", "父级权限");
			map.put("count", list.size());
			map.put("data", list);
			req.getSession().setAttribute("parentAuthorityList", list);
			resp.getWriter().print(gson.toJson(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
