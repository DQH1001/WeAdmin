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
import com.java.entity.Scores;
import com.javaweb.mapper.ICompsMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class selectAll
 */
@WebServlet("/InsertOrUpdateScores")
public class InsertOrUpdateScores extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertOrUpdateScores() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charSet=utf-8");
		request.setCharacterEncoding("utf-8");
		String scid=request.getParameter("scid");
		int s_cid=Integer.valueOf(request.getParameter("cid"));		
		int s_sid=Integer.valueOf(request.getParameter("sid"));
		int s_pid=Integer.valueOf(request.getParameter("pid"));
				
		Map<String,Object> map=new HashMap<String, Object>();
		ICompsMapperDao cm=new ICompsMapperDao();
		PrintWriter pw=response.getWriter();
		Scores score=new Scores(0,s_pid,s_cid,s_sid);
		int count=0;
		if("no".equals(scid)) {
			//insert
			count=cm.insertScores(score);
			System.out.println("insert");
			if(count>0) {
				System.out.println(map.toString());
				map.put("msg", "insert ok");
				map.put("choose", score.getScid());
				JSONObject js=JSONObject.fromObject(map);
				pw.print(js.toString());
			}else {
				JSONObject js=JSONObject.fromObject(map);
				pw.print(js.toString());
			}
		}else {
			System.out.println("update");
			//update
			int sccid=Integer.valueOf(scid);
			score.setScid(sccid);
			count=cm.updateScores(score);
			if(count>0) {				
				map.put("msg", "update ok");
				map.put("choose", score.getScid());
				System.out.println(map.toString());
				JSONObject js=JSONObject.fromObject(map);
				pw.print(js.toString());
			}else {
				map.put("msg", "你已选择过该专业了");
				map.put("choose", score.getScid());
				System.out.println(map.toString());
				JSONObject js=JSONObject.fromObject(map);
				pw.print(js.toString());
			}
		}
		
		//JSONArray js=JSONArray.fromObject(ls);
		
		//pw.print(js.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
