package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.util.UploadImgResult;

@WebServlet("/uploadimg")
@MultipartConfig
public class UploadImgsServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取当前时间转字符串,把图片按当前时间存放
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		String nowStr = sdf.format(now);
		
		//存储上传结果
		UploadImgResult ur = new UploadImgResult();
		ur.setErrno(0);
		String[] filePaths = new String[5];//存储5个图片地址,须和一次上传最大图片数量一致
		int i = 0;
		Collection<Part> parts = req.getParts();
		for(Part part:parts) {
			String disposition = part.getHeader("Content-Disposition");
			int index = disposition.lastIndexOf(".");
			//获取文件类型,按文件类型存放文件
			String type = disposition.substring(index+1,disposition.length()-1);
			String fileName = UUID.randomUUID().toString()+"."+type;
			InputStream is = part.getInputStream();
			String serverPath = req.getServletContext().getRealPath("upload");
			String filePath = serverPath+"/"+nowStr+"/"+type+"/"+fileName;
			String pathname = serverPath+"/"+nowStr+"/"+type;
			File dir = new File(pathname);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(filePath);
			byte[] byt = new byte[1024];
			int length = 0;
			while((length=is.read(byt))!=-1) {
				fos.write(byt,0,length);
			}
			fos.close();
			is.close();
			//保存图片服务器相对路径
			filePaths[i++] = "/znkj/upload/"+nowStr+"/"+type+"/"+fileName;
		}
		ur.setData(filePaths);
		Gson gson = new Gson();
		//转换成JSON响应给客户端
		String resultJson = gson.toJson(ur);
		resp.getWriter().print(resultJson);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
