package com.javaweb.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dao.ICompsMapperDao;
import com.java.entity.Student;
import com.java.entity.Teachers;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
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
		String spwd=request.getParameter("pas");
		String chk=request.getParameter("chk");	
		PrintWriter pw=response.getWriter();
		if(chk.equals("stu")) {
			Student stu=cm.loginChecked(sname,spwd);
			if(stu==null) {
				stu=new Student();
				JSONObject js=JSONObject.fromObject(stu);
				pw.print(js.toString());
			}else {
				String []menus=stu.getMenus().split(",");
				stu.setStumenus(menus);
				JSONObject js=JSONObject.fromObject(stu);
				pw.print(js.toString());
			}
		}else {
			//////////
			Teachers tea=cm.loginCheckedTea(sname,spwd);
			if(tea==null) {
				tea=new Teachers();				
				JSONObject js=JSONObject.fromObject(tea);	
				pw.print(js.toString());
			}else {
				String []menus=tea.getMenus().split(",");
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("teaid", tea.getTeaid());
				map.put("teaname", tea.getTeaname());
				map.put("teapwd", tea.getTeapwd());
				map.put("tealogo", tea.getTealogo());
				map.put("teamenus", menus);
				//System.out.println(tea.toString());
				JSONObject js=JSONObject.fromObject(map);	
				pw.print(js.toString());
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

