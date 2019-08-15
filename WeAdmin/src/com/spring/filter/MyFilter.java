package com.spring.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter implements Filter{
	String enCode=null;
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//    	this.enCode=filterConfig.getInitParameter("encode");
    	System.out.println("init---:"+enCode);
    }
    @Override
    public void destroy() {
    	enCode=null;
    	System.out.println("--destroy");    	
    }
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		//设置字符集会导致pc端乱码
//		arg0.setCharacterEncoding(enCode);
//		arg1.setCharacterEncoding(enCode);
		arg2.doFilter(arg0, arg1);
	}
	
}
