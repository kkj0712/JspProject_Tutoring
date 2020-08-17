package com.wmember.model;

public class WCartDTO {
	private int cartnum;
	private int classnum;
	private String classname;
	private String userid;
	
	public int getCartnum() {
		return cartnum;
	}
	public void setCartnum(int cartnum) {
		this.cartnum = cartnum;
	}
	public int getClassnum() {
		return classnum;
	}
	public void setClassnum(int classnum) {
		this.classnum = classnum;
	}
	public String getClassname() {
		return classname == null ? "" : classname.trim();
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getUserid() {
		return userid == null ? "" : userid.trim();
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}