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

import com.entity.Profession;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.service.ProfessionService;
import com.service.impl.ProfessionServiceImpl;


@WebServlet("/profession")
public class ProfessionServlet extends HttpServlet{
	
	ProfessionService ps = new ProfessionServiceImpl();
	Gson gson = new Gson();

	private static final long serialVersionUID = 1L;
	
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
		case "getProfession":
			getProfession(req, resp);
			break;
		case "del":
			del(req, resp);
			break;
		case "delete":
			delete(req, resp);
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
		case "setDetail":
			setDetail(req, resp);
			break;
		case "getDetail":
			getDetail(req, resp);
			break;
		case "details":
			details(req, resp);
			break;
		}
	}

	private void details(HttpServletRequest req, HttpServletResponse resp) {
		try {
			if(req.getParameter("id")==null) {
				Profession p = (Profession)req.getSession().getAttribute("professiontbDetail");
				resp.getWriter().print(gson.toJson(p));
			}else {
				Integer id = Integer.parseInt(req.getParameter("id"));
				Profession p = ps.getProfession(id);
				req.getSession().setAttribute("professiontbDetail", p);
				resp.getWriter().print("<script>location.href='/znkj/home/majorsDetails.html';</script>");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String jsonParam = req.getParameter("ids");
			List<Profession> params =  gson.fromJson(jsonParam, new TypeToken<List<Profession>>() {}.getType());
			int length = params.size();
			int[] results = new int[length];
			boolean flag = true;
			for(int i=0,j=0;i<length;i++) {
				int id = params.get(i).getPid();
				if (ps.remove(id)) {
					// 删除成功
					results[j++] = id;
				}else {
					flag = false;
					break;
				}
			}
			if(flag) {
				resp.getWriter().print(gson.toJson(results));
			}else {
				resp.getWriter().print(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getDetail(HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.getWriter().print(gson.toJson((Profession)req.getSession().getAttribute("professionDetail")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setDetail(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Integer id = Integer.parseInt(req.getParameter("id"));
			Profession p = ps.getProfession(id);
			if(p!=null) {
				req.getSession().setAttribute("professionDetail", p);
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
		try {
			resp.getWriter().print(gson.toJson((Profession)req.getSession().getAttribute("professionEdit")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setEdit(HttpServletRequest req, HttpServletResponse resp) {
		Integer id = Integer.parseInt(req.getParameter("id"));
		try {
			Profession p = ps.getProfession(id);
			if(p!=null) {
				req.getSession().setAttribute("professionEdit", p);
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
		Profession p = new Profession();
		p.setPid(Integer.parseInt(req.getParameter("id")));
		p.setPname(req.getParameter("name"));
		p.setPimg(req.getParameter("cover"));
		p.setPcontent(req.getParameter("content"));
		try {
			boolean flag = ps.alter(p);
			if(flag) {
				req.getSession().setAttribute("professionDetail", ps.getProfession(p.getPid()));
				resp.getWriter().print("<script>alert('修改成功!');location.href='/znkj/back/profession/detail.html';</script>");
			}else {
				resp.getWriter().print("<script>alert('修改失败!');location.href='/znkj/back/profession/edit.html';</script>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest req, HttpServletResponse resp) {
		Profession p = new Profession();
		p.setPname(req.getParameter("name"));
		p.setPimg(req.getParameter("cover"));
		p.setPcontent(req.getParameter("content"));
		try {
			boolean flag = ps.append(p);
			if(flag) {
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
		try {
			String jsonParam = req.getParameter("id");
			Profession param = ps.getProfession(Integer.parseInt(jsonParam));
			if (ps.remove(param.getPid())) {
				// 删除成功
				resp.getWriter().print("ID: "+param.getPid());
			}else {
				resp.getWriter().print(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getProfession(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<Profession> list = ps.getProfession();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("msg", "专业介绍数据");
			map.put("count", list.size());
			map.put("data", list);
			req.getSession().setAttribute("professionList", list);
			resp.getWriter().print(gson.toJson(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
