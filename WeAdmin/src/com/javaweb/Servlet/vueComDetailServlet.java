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
@WebServlet("/vueComDetailServlet")
public class vueComDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public vueComDetailServlet() {
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
		
		//该公司的所有信息，封装成map集合
		Map<String,Object> mp=new HashMap<String, Object>();
		mp=cm.selectComCid(cid);
		
		//用来存放其他集合类
		Map<String,Object> map=new HashMap<String, Object>();
		
		//图片信息，封装成list<map>是因为map不允许重复key值，而前台是循环通过map的key(img)获取值,
		//前台代码：v-for：item in ls2 hmtl：:src="item.img"
		//当然前台也可以写成v-for: value in object; :src="value"就可以存储为Map集合(理论上，未尝试)
		List<Map<String,Object>> ls2=new ArrayList();
		Map<String,Object> map2=new HashMap<String, Object>();
		String imgs=(String) mp.get("cimgs");
		String[] strs=imgs.split(",");
		for (String str : strs) {
			map2=new HashMap<String, Object>();
			map2.put("img", "images/imglunbo/"+str);
			ls2.add(map2);
		}
//		map2.put("img", "images/imglunbo/timg1.jpg");
//		ls2.add(map2);
//		
//		map2=new HashMap<String, Object>();
//		map2.put("img", "images/imglunbo/timg2.jpg");
//		ls2.add(map2);
//		
//		map2=new HashMap<String, Object>();
//		map2.put("img", "images/imglunbo/timg3.jpg");
//		ls2.add(map2);
//		
//		map2=new HashMap<String, Object>();
//		map2.put("img", "images/imglunbo/timg4.jpg");
//		ls2.add(map2);
//		
//		map2=new HashMap<String, Object>();
//		map2.put("img", "images/imglunbo/timg5.jpg");
//		ls2.add(map2);
//		
//		map2=new HashMap<String, Object>();
//		map2.put("img", "images/imglunbo/timg6.jpg");
//		ls2.add(map2);
		map.put("ls", ls);
		map.put("mp", mp);
		map.put("ls2", ls2);
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
