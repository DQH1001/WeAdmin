package com.javaweb.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dao.ICompsMapperDao;
import com.java.entity.Projects;
import com.java.entity.Student;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@WebServlet("/ProjectsRegistrationChecked")
public class ProjectsRegistrationChecked extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    public ProjectsRegistrationChecked() {
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
		String pname=request.getParameter("pname");
		PrintWriter pw=response.getWriter();
		
		if(pname==null||pname.equals("")) {
			Projects pro=new Projects();
			JSONObject js=JSONObject.fromObject(pro);	
			pw.print(js.toString());	
			return;
		}
		//sname��Ϊ�շ���null(���ݿ�û������)���߶���(���ݿ��и�����)
		Projects pro=cm.ProRegistrationChecked(pname);
		JSONObject js=JSONObject.fromObject(pro);	
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

