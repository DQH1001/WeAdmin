package com.javaweb.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dao.ICompsMapperDao;
import com.java.entity.Student;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@WebServlet("/RegistrationChecked")
public class RegistrationChecked extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    public RegistrationChecked() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ICompsMapperDao cm=new ICompsMapperDao();
		String sname=request.getParameter("usn");
		PrintWriter pw=response.getWriter();
		//sname为空,返回页面空对象
		if(sname==null||sname.equals("")) {
			Student stu=new Student();
			JSONObject js=JSONObject.fromObject(stu);	
			pw.print(js.toString());	
			return;
		}
		//sname不为空返回null(数据库没该名字)或者对象(数据库有该名字)
		Student stu=cm.RegistrationChecked(Integer.valueOf(sname));
		JSONObject js=JSONObject.fromObject(stu);	
		pw.print(js.toString());	
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

