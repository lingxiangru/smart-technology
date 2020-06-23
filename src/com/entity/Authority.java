package com.entity;

import java.io.Serializable;
import java.util.List;

public class Authority implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer aid;
	private String aauthority;
	private Integer aparentid;
	private String alocation;
	private List<Authority> children;
	private String title;
	private boolean spread = true;
	private boolean checked;
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isSpread() {
		return spread;
	}
	public void setSpread(boolean spread) {
		this.spread = spread;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
		this.id = aid;
	}
	public String getAauthority() {
		return aauthority;
	}
	public void setAauthority(String aauthority) {
		this.aauthority = aauthority;
		this.title = aauthority;
	}
	public Integer getAparentid() {
		return aparentid;
	}
	public void setAparentid(Integer aparentid) {
		this.aparentid = aparentid;
	}
	public String getAlocation() {
		return alocation;
	}
	public void setAlocation(String alocation) {
		this.alocation = alocation;
	}
	
	public List<Authority> getChildren() {
		return children;
	}
	public void setChildren(List<Authority> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "Authority [aid=" + aid + ", aauthority=" + aauthority + ", aparentid=" + aparentid + ", alocation="
				+ alocation + ", children=" + children + ", title=" + title + ", spread=" + spread + ", checked="
				+ checked + ", id=" + id + "]";
	}
	
}
