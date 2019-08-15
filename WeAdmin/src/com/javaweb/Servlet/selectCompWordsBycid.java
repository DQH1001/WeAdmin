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
import com.javaweb.mapper.ICompsMapper;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class selectAll
 */
@WebServlet("/selectCompWordsBycid")
public class selectCompWordsBycid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectCompWordsBycid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charSet=utf-8");
		request.setCharacterEncoding("utf-8");
		int cid=Integer.valueOf(request.getParameter("cid"));
		ICompsMapperDao cm=new ICompsMapperDao();
		List<Map<String,Object>> ls=new ArrayList();
		ls=cm.selectCompWordsBycid(cid);
		JSONArray js=JSONArray.fromObject(ls);
		PrintWriter pw=response.getWriter();
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
