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
@WebServlet("/uploadWords")
public class uploadWords extends HttpServlet {
	//1.SmartUpload������Ҫservletconfig���ò���
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
			 * SmartUpload������������˼·�ǰ����ϴ���request����Ȼ���װΪsmartupload��������ִ�У�����
			 * requestû����ȡenctype="multipart/form-data"���ύ�Ĳ���
			 * 
			 */
			SmartUpload su=new SmartUpload();
			//�� r c r ��Ҫinit����
			su.initialize(config, request, response);
			//����size��
			su.setTotalMaxFileSize(4096*2048);
			try {
				//su���󣬴����������������ύ����ok ��
				su.upload();
				
				String wtit=su.getRequest().getParameter("wtit");
				String wcont=su.getRequest().getParameter("wcont").trim();
				int sidd=Integer.valueOf(su.getRequest().getParameter("sidd"));
				int cidd=Integer.valueOf(su.getRequest().getParameter("cidd"));
				String widd=su.getRequest().getParameter("widd");
				System.out.println(wtit+" / "+wcont+" / "+sidd+" / "+cidd+" / "+widd);
				String uploadFile=request.getRealPath("upload");
				System.out.println(uploadFile);
				File upfile=new File(uploadFile);
				if(!upfile.exists()) {
					upfile.mkdir();//create �ļ���
				}
				System.out.println("file count:"+su.getFiles().getCount());
				String fileName=null;
				boolean f=true;
				for (int i = 0; i < su.getFiles().getCount(); i++) {
					//��ȡ�������ļ���su ���͵�File����
					com.jspsmart.upload.File file=su.getFiles().getFile(i);
					System.out.println("path:"+file.getFilePathName()+","+
					file.getFileName()+","+file.getFileExt());
					fileName=file.getFileName();
					if(!fileName.equals("")) {
						//�ж���չ��
						int iu=fileName.lastIndexOf(".");
						//��ȡ��չ��
						String fName=fileName.substring(iu, fileName.length());
						if(fName.equals(".png")||fName.equals(".jpg")||fName.equals(".gif")) {
							//ִ���ϴ�
							file.saveAs("upload/"+fileName,SmartUpload.SAVE_VIRTUAL);
							
						}
					}
				}
				if(f) {
					PrintWriter pw=response.getWriter();
					ICompsMapperDao cm=new ICompsMapperDao();
					boolean count=true;
					int whid=0;
					if(!"".equals(widd)&&!widd.equals(null)) {
						whid=Integer.valueOf(widd);
						count=cm.updateCompWordsBywhid(whid);
					}
					if(count) {
						Words word=new Words();
						word.setWtitle(wtit);
						word.setW_cid(cidd);
						word.setW_sid(sidd);
						word.setWhid(whid);
						word.setWcontent(wcont);
						word.setWimages(fileName);
						count=cm.insertCompWords(word);
						if(count){
							pw.print("insert ok");
						}else{
							pw.print("insert err");
						}
					}else {
						pw.print("����ʧ��");
					}					
				}
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//�ϴ������б����ݣ���ʾ���������ļ�ɾ��Tomcat�����ļ����ڡ�
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

