package com.java.entity;

import java.util.List;

public class Types {
	private int tid;
	private String tname,test;
	private List<Comps> li;
	public Types() {}
	public Types(int tid, String tname, String test) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.test = test;
	}
	
	public Types(int tid, String tname) {
		super();
		this.tid = tid;
		this.tname = tname;
	}
	
	public List<Comps> getLi() {
		return li;
	}
	public void setLi(List<Comps> li) {
		this.li = li;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	@Override
	public String toString() {
		return "Types [tid=" + tid + ", tname=" + tname + ", test=" + test + "]";
	}
	
}
