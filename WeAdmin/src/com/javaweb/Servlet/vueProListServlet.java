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
import com.javaweb.mapper.ICompsMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class selectAll
 */
@WebServlet("/vueProListServlet")
public class vueProListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public vueProListServlet() {
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
		String c_pros=request.getParameter("pros");
		Comps com=new Comps();
		com.setCid(cid);
		com.setC_pros(c_pros);
		ICompsMapperDao cm=new ICompsMapperDao();
		
		//公司对应的所有专业
		List<Map<String,Object>> ls=new ArrayList();
		ls=cm.selectProByCid(com);
		Map<String,Object> map=new HashMap<String, Object>();		
		map.put("ls", ls);
		JSONObject js=JSONObject.fromObject(map);
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
