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

import com.entity.Menu;
import com.google.gson.Gson;
import com.service.MenuService;
import com.service.impl.MenuServiceImpl;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet{
	
	Gson gson = new Gson();

	private static final long serialVersionUID = 1L;
	MenuService ms = new MenuServiceImpl();
	
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
		case "getMenu":
			getMenu(req, resp);
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
	
	private void getEdit(HttpServletRequest req, HttpServletResponse resp) {
		Menu m = (Menu) req.getSession().getAttribute("menuEdit");
		try {
			resp.getWriter().print(gson.toJson(m));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setEdit(HttpServletRequest req, HttpServletResponse resp) {
		Integer id = Integer.parseInt(req.getParameter("id"));
		try {
			Menu m = ms.getMenu(id);
			if(m!=null) {
				req.getSession().setAttribute("menuEdit", m);
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
		Menu m = new Menu();
		m.setMid(Integer.parseInt(req.getParameter("id")));
		m.setMname(req.getParameter("name"));
		m.setMlink(req.getParameter("link"));
		try {
			if(ms.alter(m)) {
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

	private void add(HttpServletRequest req, HttpServletResponse resp) {
		Menu m = new Menu();
		m.setMname(req.getParameter("name"));
		m.setMlink(req.getParameter("link"));
		try {
			if(ms.append(m)) {
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

	private void del(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			if(ms.remove(id)) {
				resp.getWriter().print("ID: "+id);
			}else {
				resp.getWriter().print(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void getMenu(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<Menu> list = ms.getMenu();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("msg", "菜单数据");
			map.put("count", list.size());
			map.put("data", list);
			req.getSession().setAttribute("menuList", list);
			resp.getWriter().print(gson.toJson(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
