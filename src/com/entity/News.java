package com.entity;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer nid;
	private String ntitle;
	private String nintr;
	private String ncontent;
	private String nimg;
	private String npublisher;
	private Date ndate;
	private String ntype;
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNintr() {
		return nintr;
	}
	public void setNintr(String nintr) {
		this.nintr = nintr;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNimg() {
		return nimg;
	}
	public void setNimg(String nimg) {
		this.nimg = nimg;
	}
	public String getNpublisher() {
		return npublisher;
	}
	public void setNpublisher(String npublisher) {
		this.npublisher = npublisher;
	}
	public Date getNdate() {
		return ndate;
	}
	public void setNdate(Date ndate) {
		this.ndate = ndate;
	}
	public String getNtype() {
		return ntype;
	}
	public void setNtype(String ntype) {
		this.ntype = ntype;
	}
	@Override
	public String toString() {
		return "News [nid=" + nid + ", ntitle=" + ntitle + ", nintr=" + nintr + ", ncontent=" + ncontent + ", nimg="
				+ nimg + ", npublisher=" + npublisher + ", ndate=" + ndate + ", ntype=" + ntype + "]";
	}
	
}
