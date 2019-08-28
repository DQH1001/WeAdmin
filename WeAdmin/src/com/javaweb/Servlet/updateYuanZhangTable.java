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
import com.java.entity.Comps;
import com.java.entity.Student;
import com.javaweb.mapper.ICompsMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class selectAll
 */
@WebServlet("/updateYzTable")
public class updateYuanZhangTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateYuanZhangTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charSet=utf-8");
		request.setCharacterEncoding("utf-8");
		List<Comps> listMap=new ArrayList<Comps>();
		Comps com=null;
		String ids=request.getParameter("ids");
		String changces=request.getParameter("changces");
		String[] idss=ids.split(";");
		String[] changcess=changces.split(";");
		for(int i=0;i<idss.length;i++) {
			com=new Comps();
			com.setCid(Integer.valueOf(idss[i]));
			com.setCnumChange(Integer.valueOf(changcess[i]));
			listMap.add(com);
		}
		ICompsMapperDao cm=new ICompsMapperDao();
		int count=cm.updateYzTable(listMap);
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
