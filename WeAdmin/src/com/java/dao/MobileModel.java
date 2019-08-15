package com.java.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component("mobileModel")
public class MobileModel {
	
	private List<Map<String,Object>> ls2=new ArrayList<Map<String,Object>>();
	

	
	public List<Map<String,Object>> SwiperImage(){
		Map<String,Object> map2=new HashMap<String, Object>();
		map2.put("img", "../images/imglunbo/timg01.jpg");
		ls2.add(map2);
		
		map2=new HashMap<String, Object>();
		map2.put("img", "../images/imglunbo/timg02.jpg");
		ls2.add(map2);
		
		map2=new HashMap<String, Object>();
		map2.put("img", "../images/imglunbo/timg03.jpg");
		ls2.add(map2);
		
		map2=new HashMap<String, Object>();
		map2.put("img", "../images/imglunbo/timg04.jpg");
		ls2.add(map2);
		return ls2;
	}
	
}
