package com.mobile.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dao.MobileModel;

@Controller("mobileController")
@RequestMapping("/mobile")
public class MobileController {
	
	@Autowired
	private MobileModel mm;
	
	/*
	 * public void setMm(MobileModel mm) { this.mm = mm; }
	 */
	public MobileController() {
		System.out.println("bbbbb");
	}
	/*
	 * public void setMm(@Qualifier("mobileModel")MobileModel mm) { this.mm=mm; }
	 */
	//mobile/swiper
	@RequestMapping(value="/swiper")
	@ResponseBody
	public List<Map<String,Object>> ImageSwiper(HttpServletRequest req) {
		try {
			req.setCharacterEncoding("utf-8");
			List<Map<String,Object>> l=this.mm.SwiperImage();			
			return l;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}
