package com.java.entity;

public class Projects {
	
	private int pid,numbers;
	private String pname,pdetail,ptest,plogo;
	public Projects() {}
	public Projects(int pid, String pname, String pdetail, String ptest) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pdetail = pdetail;
		this.ptest = ptest;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public String getPlogo() {
		return plogo;
	}
	public void setPlogo(String plogo) {
		this.plogo = plogo;
	}
	public int getNumbers() {
		return numbers;
	}
	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPdetail() {
		return pdetail;
	}
	public void setPdetail(String pdetail) {
		this.pdetail = pdetail;
	}
	public String getPtest() {
		return ptest;
	}
	public void setPtest(String ptest) {
		this.ptest = ptest;
	}
	
}
