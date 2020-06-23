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

import com.entity.User;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserService us = new UserServiceImpl();
	
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
		case "login":
			login(req, resp);
			break;
		case "logout":
			logout(req, resp);
			break;
		case "getUser":
			getUser(req, resp);
			break;
		case "showUser":
			showUser(req, resp);
			break;
		case "add":
			add(req, resp);
			break;
		case "del":
			del(req, resp);
			break;
		case "setEdit":
			setEdit(req, resp);
			break;
		case "getEdit":
			getEdit(req, resp);
			break;
		case "alter":
			alter(req, resp);
			break;
		}
	}

	private void alter(HttpServletRequest req, HttpServletResponse resp) {
		try {
			@SuppressWarnings("unchecked")
			HashMap<String,Object> map = gson.fromJson(req.getParameter("data"), HashMap.class);
			User u = new User();
			u.setUid(Integer.parseInt(map.get("id").toString()));
			u.setUname(map.get("name").toString());
			u.setUpwd(map.get("pwd").toString());
			if(us.alter(u)) {
				resp.getWriter().print(true);
			}else {
				resp.getWriter().print(false);
			}
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getEdit(HttpServletRequest req, HttpServletResponse resp) {
		try {
			User u = (User)req.getSession().getAttribute("userEdit");
			resp.getWriter().print(gson.toJson(u));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void setEdit(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Integer id = Integer.parseInt(req.getParameter("id"));
			User u =  us.getUser(id);
			if(u!=null) {
				req.getSession().setAttribute("userEdit", u);
				resp.getWriter().print(true);
			}else {
				resp.getWriter().print(false);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void del(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Integer id = Integer.parseInt(req.getParameter("id"));
			if(us.remove(id)) {
				resp.getWriter().print(true);
			}else {
				resp.getWriter().print(false);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest req, HttpServletResponse resp) {
		try {
			@SuppressWarnings("unchecked")
			HashMap<String,Object> map = gson.fromJson(req.getParameter("data"), HashMap.class);
			User u = new User();
			u.setUname(map.get("name").toString());
			u.setUpwd(map.get("pwd").toString());
			if(us.append(u)) {
				resp.getWriter().print(true);
			}else {
				resp.getWriter().print(false);
			}
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void showUser(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<User> list = us.show();
			if(list!=null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", 0);
				map.put("msg", "用户数据");
				map.put("count", list.size());
				map.put("data", list);
				req.getSession().setAttribute("userList", list);
				resp.getWriter().print(gson.toJson(map));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void login(HttpServletRequest req, HttpServletResponse resp) {
		String name = req.getParameter("user");
		String pwd = req.getParameter("password");
		try {
			User u = us.login(name, pwd);
			if (u != null) {
				req.getSession().setAttribute("user", u);
				resp.getWriter().print("0");
			} else {
				resp.getWriter().print("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logout(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().removeAttribute("user");
		try {
			resp.getWriter().print("<script>location.href='" + req.getContextPath() + "/back/login.html'</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getUser(HttpServletRequest req, HttpServletResponse resp) {
		User u = (User) req.getSession().getAttribute("user");
		try {
			if (u != null) {
				Gson gson = new Gson();
				resp.getWriter().print(gson.toJson(u));
			} else {
				resp.getWriter().print("null");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
