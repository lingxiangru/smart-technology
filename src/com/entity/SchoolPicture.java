package com.entity;

import java.io.Serializable;

public class SchoolPicture implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sid;
	private String simg;
	private String sdescription;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSimg() {
		return simg;
	}
	public void setSimg(String simg) {
		this.simg = simg;
	}
	public String getSdescription() {
		return sdescription;
	}
	public void setSdescription(String sdescription) {
		this.sdescription = sdescription;
	}
	@Override
	public String toString() {
		return "SchoolPicture [sid=" + sid + ", simg=" + simg + ", sdescription=" + sdescription + "]";
	}
	
}
