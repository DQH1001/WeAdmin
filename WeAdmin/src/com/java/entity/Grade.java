package com.java.entity;

public class Grade {
	private String gname;
	private int gid;
	public Grade(int gid, String gname) {
		this.gname = gname;
		this.gid = gid;		
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	
}
