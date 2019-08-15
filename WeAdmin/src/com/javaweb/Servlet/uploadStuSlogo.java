package com.javaweb.Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dao.ICompsMapperDao;
import com.java.entity.Comps;
import com.java.entity.Student;
import com.java.entity.Words;
import com.javaweb.mapper.ICompsMapper;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class selectAll
 */
@WebServlet("/uploadStuSlogo")
public class uploadStuSlogo extends HttpServlet {
	//1.SmartUpload创建需要servletconfig配置参数
		private ServletConfig config=null;
		@Override
		public void init(ServletConfig config) throws ServletException {
			// TODO Auto-generated method stub
//			super.init(config);
			this.config=config;
		}
		
		private static final long serialVersionUID = 1L;
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");	
			/*
			 * SmartUpload轻量级，但是思路是包裹上传的request对象，然后封装为smartupload操作对象执行，否则
			 * request没法获取enctype="multipart/form-data"表单提交的参数
			 * 
			 */
			SmartUpload su=new SmartUpload();
			//绑定 r c r 需要init加载
			su.initialize(config, request, response);
			//设置size，
			su.setTotalMaxFileSize(4096*2048);
			try {
				//su对象，创建，包裹，加载提交参数ok 了
				su.upload();
				int sid=Integer.valueOf(su.getRequest().getParameter("sidd"));
				String uploadFile=request.getRealPath("upload");
				System.out.println(uploadFile);
				File upfile=new File(uploadFile);
				if(!upfile.exists()) {
					upfile.mkdir();//create 文件夹
				}
				System.out.println("file count:"+su.getFiles().getCount());
				String fileName=null;
				boolean f=false;
				for (int i = 0; i < su.getFiles().getCount(); i++) {
					//获取二进制文件，su 类型的File对象
					com.jspsmart.upload.File file=su.getFiles().getFile(i);
					System.out.println("path:"+file.getFilePathName()+","+
					file.getFileName()+","+file.getFileExt());
					fileName=file.getFileName();
					if(!fileName.equals("")) {
						//判断扩展名
						int iu=fileName.lastIndexOf(".");
						//截取扩展名
						String fName=fileName.substring(iu, fileName.length());
						if(fName.equals(".png")||fName.equals(".jpg")||fName.equals(".gif")) {
							//执行上传
							file.saveAs("upload/"+fileName,SmartUpload.SAVE_VIRTUAL);
							f=true;
						}
					}
				}
				PrintWriter pw=response.getWriter();
				if(f) {
					Student stu=new Student();
					stu.setSlogo(fileName);
					stu.setSid(sid);
					int count=-1;					
					ICompsMapperDao cm=new ICompsMapperDao();
					count=cm.uploadStuSlogo(stu);
						if(count>=0){
							Map<String,Object> map=new HashMap<String, Object>();
							map.put("msg","update ok");
							map.put("slogo",fileName);
							JSONObject js=JSONObject.fromObject(map);
							pw.print(js);
						}else{
							pw.print("update err");
						}
				}else {
					pw.print("更新失败");
				}					
				
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//上传的所有表单内容，显示，二进制文件删除Tomcat发布文件夹内。
//			String name=request.getParameter("sname").trim();
//			System.out.println("ch:0 "+name);
//			Stus st=new StusModel().SearchStusBysname(name);
//			
			
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

}

