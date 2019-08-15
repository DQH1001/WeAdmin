package com.javaweb.Servlet;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dao.ICompsMapperDao;
import com.java.entity.Classes;
import com.java.entity.Student;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/*
 * 带文件的注册界面
 */
@WebServlet("/stuRegistration")
public class stuRegistration extends HttpServlet {	
	//1.SmartUpload创建需要servletconfig配置参数
	private ServletConfig config=null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
//		super.init(config);
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
			//如果HTML5 <input type=file 如果没有name=file1 的属性，导致其他的文本和二进制文件都上传null>
			String sname=su.getRequest().getParameter("sname").trim();
			String spwd=su.getRequest().getParameter("spwd").trim();
			int s_clid=Integer.valueOf(su.getRequest().getParameter("classid"));//外键值
			//String ssex=su.getRequest().getParameter("sample-radio");
			//获取上传的路径,指的是WebContent路径下的文件夹
			//"C:\\eclipse-workplace\\WeAdmin\\WebContent\\upload"
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
			if(f) {
				PrintWriter pw=response.getWriter();
				ICompsMapperDao cm=new ICompsMapperDao();
//				String slogo=uploadFile+"\\"+fileName;
				int count=cm.stuRegistration(sname,spwd,s_clid,fileName);
				if(count>0){
					pw.print("ok");
				}else{
					pw.print("err");
				}
			}
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//上传的所有表单内容，显示，二进制文件删除Tomcat发布文件夹内。
//		String name=request.getParameter("sname").trim();
//		System.out.println("ch:0 "+name);
//		Stus st=new StusModel().SearchStusBysname(name);
//		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
