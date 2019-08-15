package com.java.entity;

import java.util.List;

public class Comps {
	private int cid=0,c_tid,numbers=-1,ctvchoose,ccheck;
	private String cname,cdate,c_pros,cdetail,pros,clogo,cimgs,ctext,cvideo;
	private Types type;
	private int currentPage=0,pageSize=5;
//	private List<Projects> listPro=null;
	public Comps() {}
	
	public Comps(int currentPage, int pageSize) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	public Comps(int c_tid, String cname, String cdate) {
		super();
		this.c_tid = c_tid;
		this.cname = cname;
		this.cdate = cdate;
	}

	public Comps(int cid, int c_tid, String cname, String cdate) {
		super();
		this.cid = cid;
		this.c_tid = c_tid;
		this.cname = cname;
		this.cdate = cdate;
	}
	
//	public List<Projects> getListPro() {
//		return listPro;
//	}
//
//	public void setListPro(List<Projects> listPro) {
//		this.listPro = listPro;
//	}
	
	public int getNumbers() {
		return numbers;
	}

	public int getCcheck() {
		return ccheck;
	}

	public void setCcheck(int ccheck) {
		this.ccheck = ccheck;
	}

	public String getCtext() {
		return ctext;
	}

	public void setCtext(String ctext) {
		this.ctext = ctext;
	}

	public String getCvideo() {
		return cvideo;
	}

	public void setCvideo(String cvideo) {
		this.cvideo = cvideo;
	}

	public int getCtvchoose() {
		return ctvchoose;
	}

	public void setCtvchoose(int ctvchoose) {
		this.ctvchoose = ctvchoose;
	}

	public String getCimgs() {
		return cimgs;
	}

	public void setCimgs(String cimgs) {
		this.cimgs = cimgs;
	}

	public String getClogo() {
		return clogo;
	}

	public void setClogo(String clogo) {
		this.clogo = clogo;
	}

	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}

	public String getPros() {
		return pros;
	}

	public void setPros(String pros) {
		this.pros = pros;
	}

	public String getC_pros() {
		return c_pros;
	}

	public void setC_pros(String c_pros) {
		this.c_pros = c_pros;
	}

	public String getCdetail() {
		return cdetail;
	}

	public void setCdetail(String cdetail) {
		this.cdetail = cdetail;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Types getType() {
		return type;
	}

	public void setType(Types type) {
		this.type = type;
	}

	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getC_tid() {
		return c_tid;
	}
	public void setC_tid(int c_tid) {
		this.c_tid = c_tid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	@Override
	public String toString() {
		return "Comps [cid=" + cid + ", c_tid=" + c_tid + ", numbers=" + numbers + ", cname=" + cname + ", cdate="
				+ cdate + ", c_pros=" + c_pros + ", cdetail=" + cdetail + ", pros=" + pros + ", type=" + type
				+ ", currentPage=" + currentPage + ", pageSize=" + pageSize + "]";
	}

	
		
}
