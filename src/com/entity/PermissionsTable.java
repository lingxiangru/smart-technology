package com.entity;

import java.io.Serializable;

public class PermissionsTable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer uid;
	private Integer aid;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	@Override
	public String toString() {
		return "PermissionsTable [uid=" + uid + ", aid=" + aid + "]";
	}
	
}
