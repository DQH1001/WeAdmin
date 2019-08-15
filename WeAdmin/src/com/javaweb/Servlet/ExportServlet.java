package com.javaweb.Servlet;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.java.entity.*;
import com.java.dao.ICompsMapperDao;
import com.javaweb.mapper.ICompsMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class CompanyListServlet
 */
@WebServlet("/exportExcel")
public class ExportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)

               throws ServletException, IOException {

    

          this.doPost(request, response);

       }

    

       public void doPost(HttpServletRequest request, HttpServletResponse response)

               throws ServletException, IOException {

    

           //设置响应正文的MIME类型，该类型表示Excel

           request.setCharacterEncoding("gbk");

           response.setContentType("application/vnd.ms-excel");

//           String name = request.getParameter("name");
//
//           String pwd =request.getParameter("pwd");
//
//           String sex = request.getParameter("sex");
//
//           System.out.println(sex);
//
//           String age = request.getParameter("age");
//
//           String email = request.getParameter("email");

           ICompsMapperDao cm=new ICompsMapperDao();
           List<Map<String,Object>> ls=new ArrayList<Map<String,Object>>();
           ls=cm.selectStusChooseInfo();
//           map.put("sid", stu.getSid());
//			map.put("sname", stu.getSname());
//			map.put("sdate", stu.getSdate());
//			map.put("sclass", stu.getClasses().getClname());
//			map.put("slogo", "upload/"+stu.getSlogo());
//			map.put("cname", stu.getCname());
//			map.put("pname", stu.getPname());
           ServletOutputStream out = response.getOutputStream();   //响应输出流对象

           HSSFWorkbook wb = new HSSFWorkbook();                   //创建Excel表格

           HSSFSheet sheet = wb.createSheet("学生投票信息");       //创建工作薄

           sheet.setColumnWidth(4, 5000);                          //设置列宽

           

           HSSFRow titleRow = sheet.createRow(0);                  //创建Excel中的标题栏,第1行

           

           HSSFCell titleCell1 = titleRow.createCell(0);            //在行中创建第1个单元格

           titleCell1.setCellValue("学号");                     //设置第1个单元格的值

           HSSFCell titleCell2= titleRow.createCell(1);             //在行中创建第2个单元格

           titleCell2.setCellValue("姓名");                         //设置第2个单元格的值

           HSSFCell titleCell3 =titleRow .createCell(2);            //在行中创建第3个单元格

           titleCell3.setCellValue("入学日期");                         //设置第3个单元格的值

           HSSFCell titleCell4= titleRow.createCell(3);             //在行中创建第4个单元格

           titleCell4.setCellValue("班级");                         //设置第4个单元格的值

           HSSFCell titleCell5= titleRow.createCell(4);             //在行中创建第5个单元格

           titleCell5.setCellValue("企业");                        //设置第5个单元格的值

           HSSFCell titleCell6= titleRow.createCell(5);             //在行中创建第6个单元格

           titleCell5.setCellValue("专业");                        //设置第6个单元格的值

           
           HSSFRow valueRow = null;
           int i=0;
           for (Map<String,Object> map : ls) {
        	   ++i;
        	   valueRow = sheet.createRow(i);
        	   
        	   HSSFCell idCell = valueRow.createCell(0);             //在第2行中创建单元格

        	   idCell.setCellValue((Integer)map.get("sid"));

               HSSFCell nameCell = valueRow.createCell(1);

               nameCell.setCellValue((String)map.get("sname"));

               HSSFCell dateCell = valueRow.createCell(2);

               dateCell.setCellValue((String)map.get("sdate"));

               HSSFCell classCell = valueRow.createCell(3);

               classCell.setCellValue((String)map.get("sclass"));

               HSSFCell compCell = valueRow.createCell(4);

               compCell.setCellValue((String)map.get("cname"));
               
               HSSFCell proCell = valueRow.createCell(5);

               proCell.setCellValue((String)map.get("pname"));
           }
          
           
           
//           HSSFRow valueRow = sheet.createRow(1);                  //创建第2行
//
//           
//
//           HSSFCell nameCell = valueRow.createCell(0);             //在第2行中创建单元格
//
//           nameCell.setCellValue(name);
//
//           HSSFCell pwdCell = valueRow.createCell(1);
//
//           pwdCell.setCellValue(pwd);
//
//           HSSFCell sexCell = valueRow.createCell(2);
//
//           sexCell.setCellValue(sex);
//
//           HSSFCell ageCell = valueRow.createCell(3);
//
//           ageCell.setCellValue(age);
//
//           HSSFCell emailCell = valueRow.createCell(4);
//
//           emailCell.setCellValue(email);

           

           HSSFCellStyle cellStyle = wb.createCellStyle();
           FileOutputStream fout = new FileOutputStream("C:\\Users\\11040\\Documents\\Tencent Files\\1104029432\\FileRecv/Members.xls");
           wb.write(fout);                                   //将响应流输入到Excel表格中
//           //fout.flush();
           fout.close();
           
//           wb.write(out);                                   //将响应流输入到Excel表格中
//           out.flush();
       }


}
