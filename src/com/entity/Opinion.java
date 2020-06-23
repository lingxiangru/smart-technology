package com.entity;

import java.io.Serializable;
import java.util.Date;

public class Opinion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer oid;
	private String ocontent;
	private String otelephone;
	private Date odate;
	private String ostatus;
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public String getOcontent() {
		return ocontent;
	}
	public void setOcontent(String ocontent) {
		this.ocontent = ocontent;
	}
	public String getOtelephone() {
		return otelephone;
	}
	public void setOtelephone(String otelephone) {
		this.otelephone = otelephone;
	}
	public Date getOdate() {
		return odate;
	}
	public void setOdate(Date odate) {
		this.odate = odate;
	}
	public String getOstatus() {
		return ostatus;
	}
	public void setOstatus(String ostatus) {
		this.ostatus = ostatus;
	}
	@Override
	public String toString() {
		return "Opinion [oid=" + oid + ", ocontent=" + ocontent + ", otelephone=" + otelephone + ", odate=" + odate
				+ ", ostatus=" + ostatus + "]";
	}
	
}
