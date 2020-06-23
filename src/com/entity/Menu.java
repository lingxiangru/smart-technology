package com.entity;

import java.io.Serializable;

public class Menu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer mid;
	private String mname;
	private String mlink;
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMlink() {
		return mlink;
	}
	public void setMlink(String mlink) {
		this.mlink = mlink;
	}
	@Override
	public String toString() {
		return "Menu [mid=" + mid + ", mname=" + mname + ", mlink=" + mlink + "]";
	}
	
}
