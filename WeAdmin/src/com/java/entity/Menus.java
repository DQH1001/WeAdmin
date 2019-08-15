package com.java.entity;

public class Menus {
	private int peid;
	private String pename,pemenus,petest;
	public Menus() {}
	public Menus(int peid, String pename, String pemenus) {
		super();
		this.peid = peid;
		this.pename = pename;
		this.pemenus = pemenus;
	}
	public int getPeid() {
		return peid;
	}
	public void setPeid(int peid) {
		this.peid = peid;
	}
	public String getPename() {
		return pename;
	}
	public void setPename(String pename) {
		this.pename = pename;
	}
	public String getPemenus() {
		return pemenus;
	}
	public void setPemenus(String pemenus) {
		this.pemenus = pemenus;
	}
	public String getPetest() {
		return petest;
	}
	public void setPetest(String petest) {
		this.petest = petest;
	}
	
	
}
