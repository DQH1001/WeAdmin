package com.javaweb.Servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.java.entity.*;
import com.java.dao.ICompsMapperDao;
import com.javaweb.mapper.ICompsMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class CompanyListServlet
 */
@WebServlet("/excelStus")
public class ExcelStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcelStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
	    String data=request.getParameter("formdata");
	    System.out.println("data:"+data);
	    //澶栧眰json map鏈哄埗杞寲涓� 銆�...object
	    JSONObject da=JSONObject.fromObject(data);
	    //閫氳繃sheet1鑾峰彇list闆嗗悎 
		JSONArray list=da.getJSONArray("Sheet1");
		List<Student> lstu=new ArrayList<Student>();
		Student stu=null;
		Classes cs=null;
		for (int i=0;i<list.size();i++) {
			/*
			 * {
      "name": "鍐敓",
      "pwd": "67",
      "classes": "澶т簩 涓夌彮",
      "sex": "濂�"
    }
			 */
			JSONObject obj=list.getJSONObject(i);			
			String name=obj.getString("name");
			String pwd=obj.getString("pwd");
			String cls=obj.getString("classes");
			String sex=obj.getString("sex");
			if(sex.equals("女")) {
				sex="2";
			}else {
				sex="1";
			}
			cs=new Classes();			
			cs.setClname(cls);
			stu=new Student();
			stu.setClasses(cs);
			stu.setSname(name);
			stu.setSpwd(pwd);
			stu.setSsex(sex);
			lstu.add(stu);
			System.out.println(name+"-"+pwd+"-"+cls+"-"+sex);
		}
		System.out.println(lstu.size());
		//        JSONObject js=JSONObject.fromObject(map);	  
		ICompsMapperDao sm=new ICompsMapperDao();
		PrintWriter pw=response.getWriter();
		if(sm.SaveExcelStus(lstu)>0) {
			pw.print("ok");
		}else
			pw.print("error");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
