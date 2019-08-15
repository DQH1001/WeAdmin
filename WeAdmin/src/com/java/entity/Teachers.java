package com.java.entity;

public class Teachers {
	private int teaid;
	private String teaname,teapwd,tealogo,menus;
	public Teachers() {}
	public Teachers(int teaid, String teaname, String teapwd) {
		super();
		this.teaid = teaid;
		this.teaname = teaname;
		this.teapwd = teapwd;
	}
	
	public String getMenus() {
		return menus;
	}
	public void setMenus(String menus) {
		this.menus = menus;
	}
	public int getTeaid() {
		return teaid;
	}
	public void setTeaid(int teaid) {
		this.teaid = teaid;
	}
	public String getTeaname() {
		return teaname;
	}
	public void setTeaname(String teaname) {
		this.teaname = teaname;
	}
	public String getTeapwd() {
		return teapwd;
	}
	public void setTeapwd(String teapwd) {
		this.teapwd = teapwd;
	}
	public String getTealogo() {
		return tealogo;
	}
	public void setTealogo(String tealogo) {
		this.tealogo = tealogo;
	}
	@Override
	public String toString() {
		return "Teachers [teaid=" + teaid + ", teaname=" + teaname + ", teapwd=" + teapwd + ", tealogo=" + tealogo
				+ "]";
	}
	
}
