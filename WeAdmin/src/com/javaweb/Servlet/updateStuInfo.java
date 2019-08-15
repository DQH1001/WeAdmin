package com.javaweb.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dao.ICompsMapperDao;
import com.java.entity.Student;
import com.javaweb.mapper.ICompsMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class selectAll
 */
@WebServlet("/updateStuInfo")
public class updateStuInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateStuInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charSet=utf-8");
		request.setCharacterEncoding("utf-8");
		String ssex=request.getParameter("ssex");
		String stel=request.getParameter("stel");
		String semail=request.getParameter("semail");
		String scity=request.getParameter("scity");
		int sid=Integer.valueOf(request.getParameter("sid"));
		Student stu=new Student();
		stu.setSid(sid);
		stu.setSsex(ssex);
		stu.setStel(stel);
		stu.setSemail(semail);
		stu.setScity(scity);
		ICompsMapperDao cm=new ICompsMapperDao();
		int count;
		count=cm.updateStuInfo(stu);
		PrintWriter pw=response.getWriter();
		if(count>=0){
			pw.print("update ok");
		}else{
			pw.print("update err");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
