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

import com.entity.TeacherStudent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.service.TeacherStudentService;
import com.service.impl.TeacherStudentServiceImpl;


@WebServlet("/teacherStudent")
public class TeacherStudentServlet extends HttpServlet {

	TeacherStudentService tss = new TeacherStudentServiceImpl();
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
		case "getTeacherStudentPage":
			getTeacherStudentPage(req, resp);
			break;
		case "getTeacherStudent":
			getTeacherStudent(req, resp);
			break;
		case "add":
			add(req, resp);
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
		case "del":
			del(req, resp);
			break;
		case "delete":
			delete(req, resp);
			break;
		case "alter":
			alter(req, resp);
			break;
		case "details":
			details(req, resp);
			break;
		}
	}
	
	private void details(HttpServletRequest req, HttpServletResponse resp) {
		try {
			if(req.getParameter("id")==null) {
				TeacherStudent ts = (TeacherStudent)req.getSession().getAttribute("teacherStudenttbDetails");
				resp.getWriter().print(gson.toJson(ts));
			}else {
				Integer id = Integer.parseInt(req.getParameter("id"));
				TeacherStudent ts = tss.getTeacherStudent(id);
				req.getSession().setAttribute("teacherStudenttbDetails", ts);
				resp.getWriter().print("<script>location.href='/znkj/home/teacherStudentDetails.html';</script>");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getTeacherStudent(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int type = Integer.parseInt(req.getParameter("type"));
			List<TeacherStudent> list = tss.getTeacherStudent(type);
			resp.getWriter().print(gson.toJson(list));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void alter(HttpServletRequest req, HttpServletResponse resp) {
		TeacherStudent ts = new TeacherStudent();
		ts.setTid(Integer.parseInt(req.getParameter("id")));
		ts.setTname(req.getParameter("name"));
		ts.setTimg(req.getParameter("photo"));
		ts.setTcontent(req.getParameter("content"));
		ts.setTtype(Integer.parseInt(req.getParameter("type")));
		try {
			boolean flag = tss.alter(ts);
			if(flag) {
				req.getSession().setAttribute("teacherStudentDetail", tss.getTeacherStudent(ts.getTid()));
				resp.getWriter().print("<script>alert('修改成功!');location.href='/znkj/back/teacherStudent/detail.html';</script>");
			}else {
				resp.getWriter().print("<script>alert('修改失败!');location.href='/znkj/back/teacherStudent/edit.html';</script>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String jsonParam = req.getParameter("ids");
			List<TeacherStudent> params =  gson.fromJson(jsonParam, new TypeToken<List<TeacherStudent>>() {}.getType());
			int length = params.size();
			int[] results = new int[length];
			boolean flag = true;
			for(int i=0,j=0;i<length;i++) {
				int id = params.get(i).getTid();
				if (tss.remove(id)) {
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

	private void del(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Integer id = Integer.parseInt(req.getParameter("id"));
			if (tss.remove(id)) {
				// 删除成功
				resp.getWriter().print("ID: "+id);
			}else {
				resp.getWriter().print(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void getDetail(HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.getWriter().print(gson.toJson((TeacherStudent)req.getSession().getAttribute("teacherStudentDetail")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setDetail(HttpServletRequest req, HttpServletResponse resp) {
		Integer id = Integer.parseInt(req.getParameter("id"));
		try {
			TeacherStudent ts = tss.getTeacherStudent(id);
			if(ts!=null) {
				req.getSession().setAttribute("teacherStudentDetail", ts);
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
			resp.getWriter().print(gson.toJson((TeacherStudent)req.getSession().getAttribute("teacherStudentEdit")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setEdit(HttpServletRequest req, HttpServletResponse resp) {
		Integer id = Integer.parseInt(req.getParameter("id"));
		try {
			TeacherStudent ts = tss.getTeacherStudent(id);
			if(ts!=null) {
				req.getSession().setAttribute("teacherStudentEdit", ts);
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
		TeacherStudent ts = new TeacherStudent();
		ts.setTname(req.getParameter("name"));
		ts.setTtype(Integer.parseInt(req.getParameter("type")));
		ts.setTimg(req.getParameter("photo"));
		ts.setTcontent(req.getParameter("content"));
		try {
			boolean flag = tss.append(ts);
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

	public void getTeacherStudentPage(HttpServletRequest req, HttpServletResponse resp) {
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int pageNum = 1;// 默认值
		if (page != null && !"".equals(page.trim())) {
			pageNum = Integer.parseInt(page);
		}
		int pageSize = 10;// 默认值
		if (limit != null && !"".equals(limit.trim())) {
			pageSize = Integer.parseInt(limit);
		}
		int begin = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;
		try {
			List<TeacherStudent> list = tss.getTeacherStudentPage(begin, end);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("code", 0);
			map.put("msg","师生信息");
			map.put("count", tss.getCount());
			map.put("data", list);
			resp.getWriter().print(gson.toJson(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
