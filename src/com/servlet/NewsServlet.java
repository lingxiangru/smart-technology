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

import com.entity.News;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.service.NewsService;
import com.service.impl.NewsServiceImpl;

@WebServlet("/news")
public class NewsServlet extends HttpServlet {
	NewsService ns = new NewsServiceImpl();
	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

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
		case "getNews":
			getNews(req, resp);
			break;
		case "getNewsPage":
			getNewsPage(req, resp);
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
		case "getNotice":
			getNotice(req, resp);
			break;
		case "getNew":
			getNew(req, resp);
			break;
		case "getSurvey":
			getSurvey(req, resp);
			break;
		case "details":
			details(req, resp);
			break;
		}
	}

	private void details(HttpServletRequest req, HttpServletResponse resp) {
		try {
			if(req.getParameter("id")==null) {
				News n = (News)req.getSession().getAttribute("newstbDetails");
				resp.getWriter().print(gson.toJson(n));
			}else {
				Integer id = Integer.parseInt(req.getParameter("id"));
				News n = ns.getNews(id);
				req.getSession().setAttribute("newstbDetails", n);
				resp.getWriter().print("<script>location.href='/znkj/home/newsDetails.html'</script>");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void getSurvey(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<News> list;
			if(req.getParameter("count")!=null) {
				int count = Integer.parseInt(req.getParameter("count"));
				list = ns.getNewsPage("概况", 0, count);
			}else {
				list = ns.getNews("概况");
			}
			String json = gson.toJson(list);
			resp.getWriter().print(json);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void getNew(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<News> list;
			if(req.getParameter("count")!=null) {
				int count = Integer.parseInt(req.getParameter("count"));
				list = ns.getNewsPage("新闻", 0, count);
			}else {
				list = ns.getNews("新闻");
			}
			String json = gson.toJson(list);
			resp.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void getNotice(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<News> list;
			if(req.getParameter("count")!=null) {
				int count = Integer.parseInt(req.getParameter("count"));
				list = ns.getNewsPage("公告", 0, count);
			}else {
				list = ns.getNews("公告");
			}
			String json = gson.toJson(list);
			resp.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void getDetail(HttpServletRequest req, HttpServletResponse resp) {
		News n = (News) req.getSession().getAttribute("newsDetail");
		try {
			resp.getWriter().print(gson.toJson(n));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void getEdit(HttpServletRequest req, HttpServletResponse resp) {
		News n = (News) req.getSession().getAttribute("newsEdit");
		try {
			resp.getWriter().print(gson.toJson(n));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setEdit(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			News n = ns.getNews(id);
			if(n!=null) {
				req.getSession().setAttribute("newsEdit", n);
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
	
	private void setDetail(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			News n = ns.getNews(id);
			if(n!=null) {
				req.getSession().setAttribute("newsDetail", n);
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
		News n = new News();
		n.setNid(Integer.parseInt(req.getParameter("id")));
		n.setNtitle(req.getParameter("title"));
		n.setNintr(req.getParameter("intr"));
		n.setNpublisher(req.getParameter("publisher"));
		n.setNimg(req.getParameter("cover"));
		n.setNcontent(req.getParameter("content"));
		n.setNtype(req.getParameter("type"));
		try {
			boolean flag = ns.alter(n);
			if(flag) {
				req.getSession().setAttribute("newsDetail", ns.getNews(n.getNid()));
				resp.getWriter().print("<script>alert('修改成功!');location.href='/znkj/back/news/detail.html';</script>");
			}else {
				resp.getWriter().print("<script>alert('修改失败!');location.href='/znkj/back/news/edit.html';</script>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest req, HttpServletResponse resp) {
		News n = new News();
		n.setNtitle(req.getParameter("title"));
		n.setNtype(req.getParameter("type"));
		n.setNpublisher(req.getParameter("publisher"));
		n.setNimg(req.getParameter("cover"));
		n.setNintr(req.getParameter("intr"));
		n.setNcontent(req.getParameter("content"));
		try {
			boolean flag = ns.append(n);
			if(flag) {
				resp.getWriter().print("<script>alert('添加成功!');location.href='/znkj/back/news/add.html';</script>");
			}else {
				resp.getWriter().print("<script>alert('添加失败!');location.href='/znkj/back/news/add.html';</script>");
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
			News param = gson.fromJson(jsonParam, News.class);
			if (ns.remove(param.getNid())) {
				// 删除成功
				resp.getWriter().print("ID: "+param.getNid());
			}else {
				resp.getWriter().print(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String jsonParam = req.getParameter("ids");
			List<News> params =  gson.fromJson(jsonParam, new TypeToken<List<News>>() {}.getType());
			int length = params.size();
			int[] results = new int[length];
			boolean flag = true;
			for(int i=0,j=0;i<length;i++) {
				int id = params.get(i).getNid();
				if (ns.remove(id)) {
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

	public void getNews(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String type = req.getParameter("type");
			String name = type + "数据";
			List<News> list = ns.getNews(type);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("msg", type);
			map.put("count", list.size());
			map.put("data", list);
			req.getSession().setAttribute(name, list);
			resp.getWriter().print(gson.toJson(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getNewsPage(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String type = req.getParameter("type");
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
			List<News> list = ns.getNewsPage(type, begin, end);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 0);
			map.put("msg", type);
			map.put("count", ns.getCount(type));
			map.put("data", list);
			resp.getWriter().print(gson.toJson(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
