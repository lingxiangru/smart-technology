package com.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 * 文件上传工具类
 * @author 29069
 *----------------------------未完善
 */
public class UploadUtil {
	public String servletUploadFile(HttpServletRequest request, String folder, String paramName)
			throws IOException, ServletException {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String nowStr = sdf.format(now);
		Part part = request.getPart(paramName);
		String disposition = part.getHeader("Content-Disposition");
		int index = disposition.lastIndexOf(".");
		String type = disposition.substring(index+1,disposition.length());
		String fileName = UUID.randomUUID().toString()+"."+type;
		InputStream is = part.getInputStream();
		String serverPath = request.getServletContext().getRealPath(folder);
		String filePath = serverPath+"/"+type+"/"+nowStr+"/"+fileName;
		FileOutputStream fos = new FileOutputStream(filePath);
		System.out.println("服务器upload文件夹路径:"+serverPath);
		byte[] byt = new byte[1024];
		int length = 0;
		while((length=is.read(byt))!=-1) {
			fos.write(byt,0,length);
		}
		fos.close();
		is.close();
		return filePath;//返回文件路径
	}
}
