package com.entity;

import java.io.Serializable;

public class TeacherStudent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tid;
	private String tname;
	private String tcontent;
	private String timg;
	private Integer ttype;
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTcontent() {
		return tcontent;
	}
	public void setTcontent(String tcontent) {
		this.tcontent = tcontent;
	}
	public String getTimg() {
		return timg;
	}
	public void setTimg(String timg) {
		this.timg = timg;
	}
	public Integer getTtype() {
		return ttype;
	}
	public void setTtype(Integer ttype) {
		this.ttype = ttype;
	}
	@Override
	public String toString() {
		return "TeacherStudent [tid=" + tid + ", tname=" + tname + ", tcontent=" + tcontent + ", timg=" + timg
				+ ", ttype=" + ttype + "]";
	}
	
}
