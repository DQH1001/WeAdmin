package com.java.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Type {
    private int tid;
    private String tname,test;  
    private List<Comps> listps=null;
	public Type() {
	}
	/**
	 * 40 人一个班
	 * 120 100 90 78 105 84 
	 * @param args
	 * 一开始数据[]测试，但是2问题不能用[]，第一数据来源是List<Map>，
	 * 人数紧紧对应企业name和专业name，也就是人数还是唯一性的，
	 * 第二后期java数据加工时候肯定需要给数字添加一个状态标识符
	 * 院长修改人数，企业线下增或减部分人
	 */	
	int number[]= {120,100, 90,78,105,84};
	List<Map<String,Object>> list=null;
	boolean numerror=false;//true说明有 error 对应的数字
	public void init() {
		list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String, Object>();		
		map.put("pname","a-java");	//对应的唯一性的企业名字+对应该企业的专业name	
        map.put("numbers",120);       //120   
        list.add(map);
        map=new HashMap<String, Object>();		
		map.put("pname","b-java");		
        map.put("numbers",100);          //   
        list.add(map);
        map=new HashMap<String, Object>();		
		map.put("pname","c-java");		//
        map.put("numbers",84);        //     -4  
        list.add(map);
        map=new HashMap<String, Object>();	//+2	
		map.put("pname","d-java");		
        map.put("numbers",78);        //  
        list.add(map);
        map=new HashMap<String, Object>();		//+18
		map.put("pname","e-java");		
        map.put("numbers",102);  //      
        list.add(map);
        map=new HashMap<String, Object>();//   +8	
		map.put("pname","f-java");		
        map.put("numbers",112);//   
        //map.put("jian",9);
        list.add(map);
	}
	public void me1() {
		if(list==null) {
			init();
		}
		Map<String ,Integer> map=null;
		int jiannum=0,jia=0;
		//分析各个数字的状态(4种状态是哪一种)，其次"告知"最初的集合对象List<Map>的状态值
		//并且计算总体+ or -的数字
		for (Map<String, Object> map2 : list) {
			Iterator<String> itkey2=map2.keySet().iterator();
			while(itkey2.hasNext()) {
				String key2=itkey2.next();
				if("numbers".equals(key2)) {
					int number=Integer.valueOf(map2.get(key2).toString());
					//人数参数传递操作方法，
					//返回map集合是4种状态告知，而且伴随- or + 的数字，也就是返回但是两套信息数字
					map=fetNum(number);	
					Iterator<String> itKey=map.keySet().iterator();
					//map put一组数据
					if(itKey.hasNext()) {
						String key=itKey.next();
						if("jian".equals(key)) {
//							number=number-map.get(key);
//							map2.replace("numbers",number);
							//最初集合List<Map>每一个map put对应的状态，和应该+ or -的数字
							map2.put("jian",map.get(key));
							//计算总体应该+  or  +的总体的数字
							jiannum+=map.get(key);
						}else if("add".equals(key)) {
							//number[i]=number[i]-map.get(key);
							jia+=map.get(key);
							map2.put("add",map.get(key));
						}else if("error".equals(key)) {							
							map2.put("error",map.get(key));// - + 20 待宰
						}else if("ok".equals(key)) {
							map2.put("ok",map.get(key));
						}
					}
				}					
			}			
		}	
		//此时还没有任何数字的调剂，只是告知状态，和计算累加总体该+ - 的数字
		//最终的测试，总体的数字累加
		int n=0;
		//还是调剂之前的遍历测试显示，
		for (Map<String, Object> map2 : list) {
			n+=Integer.valueOf(map2.get("numbers").toString());
			Iterator<String> itkey2=map2.keySet().iterator();
			while(itkey2.hasNext()) {
				String key2=itkey2.next();
				System.out.println(key2+" // "+map2.get(key2));				
			}
			System.out.println("-----");
		}
		//开始计算调剂了
//		调剂的时候必须考虑到最先考虑的因素，也就是入手考虑的因素是什么，总体该+  or -的人数差，
//		考虑总体该+ 人数 是否大于 该+的人数，还是反之，第二考虑100数字是否存在了。
		System.out.println(n+" nn "+jiannum+" j "+jia);//34 j 2		
		//调剂总体是5种可能性
		//总体+ - 是等于第一种，第二、三中
		//加>减  看是否有100人数的待宰
		//第四、五种
		//加<减  看是否有100人数的待宰
		List<Map<String, Object>> ls=null;
		if(jiannum==jia) {//该+ -的人数一样，
			ls=EqualsMethods();//最终不能整除40的数字还是100，其他都能整除40了
		}else if(jiannum<jia) {
			ls=LessMethods(jia,jiannum);
		}else {
			ls=MoreMethods(jia,jiannum);
		}
		//调剂之后的遍历测试显示，
		boolean pei=false;
		//调剂后的新的List<Map>和之前的List<map>比对，
		for (Map<String, Object> map2 : list) {
			pei=false;//如果整除40的调剂是没有参与的，那么新的List<map>是没有对应的记录的所以
			//
			for (Map<String, Object> rep : ls) {
				//pname相等，等于调剂后的数字是匹配原始的人数map集合的
				if(map2.get("pname").equals(rep.get("pname"))) {
                    pei=true;
                    map2.put("tiao",rep.get("numTiao"));
					break;
				}
			}
			if(!pei) {//说明该map集合是整除40的，不需要调剂的数字的，原始的numbers put一次
				map2.put("tiao",map2.get("numbers"));
			}
		}		
		n=0;
		for (Map<String, Object> map2 : list) {
			n+=Integer.valueOf(map2.get("numbers").toString());
			Iterator<String> itkey2=map2.keySet().iterator();
			while(itkey2.hasNext()) {
				String key2=itkey2.next();
				System.out.println(key2+" = "+map2.get(key2));				
			}
			System.out.println("-----");
		}
		System.out.println(n+" nn");
//		for (int i = 0; i < number.length; i++) {
//			map=fetNum(number[i]);
//			Iterator<String> itKey=map.keySet().iterator();
//			if(itKey.hasNext()) {
//				String key=itKey.next();
//				if("jian".equals(key)) {
//					number[i]=number[i]-map.get(key);
//					jiannum+=map.get(key);
//				}else if("add".equals(key)) {
//					//number[i]=number[i]-map.get(key);
//					jia+=map.get(key);
//				}
//			}
//		}	
	}
	//加<减   error的需要增加 最多加20                              2        34
	private List<Map<String, Object>> MoreMethods(int jia, int jiannum) {
		List<Map<String, Object>> ls=new ArrayList<Map<String,Object>>();
		int result=0;
		int num=0;
		Map<String, Object> rep=null;
		if(numerror) {
			//error数字需要+的值
			int nm=(jiannum-jia)>20?20:(jiannum-jia);	
			//总体减去的数字
			result=jia+nm;
			for (Map<String, Object> map2 : list) {
				num=Integer.valueOf(map2.get("numbers").toString());
				Iterator<String> itkey2=map2.keySet().iterator();	
				rep=new HashMap<String, Object>();
				while(itkey2.hasNext()) {
					String key2=itkey2.next();	
					if("error".equals(key2)) {						
//						map2.replace("numbers",num+nm);
//						map2.put("numTiao",num+nm);//ConcurrentModificationException异常
						/*
						 * 如果HashMap做迭代的遍历中，继续添加长度，就会触发该异常了
						 * 也就是当前map集合在迭代的状态中遍历了，所以
						 * 在nextNode()方法中，由于modCount != expectedModCount，报ConcurrentModificationException异常
						 */
						//如果调剂改变数字了，那么放入一个新的map list集合中
						rep.put("pname",map2.get("pname"));//现在  a-java 企业专业对应调剂的人数
						rep.put("numTiao", num+nm);
					}else if("add".equals(key2)) {
						int addnum=Integer.valueOf(map2.get("add").toString());
//						map2.replace("numbers",num+addnum);
						rep.put("pname",map2.get("pname"));
						rep.put("numTiao", num+addnum);
					}else if("jian".equals(key2)) {
						int jian=Integer.valueOf(map2.get("jian").toString());
						rep.put("pname",map2.get("pname"));
						if(result>=jian) {
							result-=jian;
//							map2.replace("numbers",num-jian);
							rep.put("numTiao", num-jian);
						}else {
//							map2.replace("numbers",num-result);
							rep.put("numTiao", num-result);
							result=0;
						}
					}
				}
				ls.add(rep);
			}
		}else {
			//总体添加的数字，没有100数字的调剂
			result=jia;
			for (Map<String, Object> map2 : list) {
				Iterator<String> itkey2=map2.keySet().iterator();	
				rep=new HashMap<String, Object>();
				while(itkey2.hasNext()) {
					String key2=itkey2.next();
					num=Integer.valueOf(map2.get("numbers").toString());
					if("add".equals(key2)) {
						int addnum=Integer.valueOf(map2.get("add").toString());
//						map2.replace("numbers",num+addnum);		
						rep.put("pname",map2.get("pname"));
						rep.put("numTiao", num+addnum);
					}else if("jian".equals(key2)) {
						int jian=Integer.valueOf(map2.get("jian").toString());
						rep.put("pname",map2.get("pname"));
						if(result>=jian) {
							result-=jian;
//							map2.replace("numbers",num-jian);
							rep.put("numTiao", num-jian);
						}else {
//							map2.replace("numbers",num-result);
							rep.put("numTiao", num-result);
							result=0;
						}
					}
				}
				ls.add(rep);
			}
		}
		return ls;
    }
	//100人数待宰，但是数字调剂最多平衡也就是20的，也就是100最多+20  or  -20
	//总体数字：加>减   error的(100人数专业)需要减少 最多减20                              17     14
	private List<Map<String, Object>> LessMethods(int jia,int jiannum) {	
		List<Map<String, Object>> ls=new ArrayList<Map<String,Object>>();
		Map<String, Object> rep=null;
		int result=0;//总体需要+ or -的数字，是需要看是否有100数字的那个企业专业
		//有100数字的专业
		if(numerror) {
			//100数字的那个专业  error数字需要 - 的值，不是最终的值
			int nm=(jia-jiannum)>20?20:(jia-jiannum);	
			/*
			 * 总体添加的数字
			 * 比如 总体需要-的人数10  总体需要+的人数35，那么总体调剂总体+的人数
			 * 肯定20(100人数专业贡献的)+10 也就是+的专业数字只能调剂30  个人，有5个还是不能整除40
			 * 
			 * 调剂的时候，总体需要加的35内的所有人数，能够满足的是30，也就是比如第一个需要加的是8
			 * 那么第二个加的人数比对就是30-8的人数了，当累计要+的数量小于当前需要加的人数的时候
			 * 不能满足了
			 */
			//     总体该-的数字+100人数贡献的数字(最大20)
			result=jiannum+nm;//未必是总体需要+的数字
			for (Map<String, Object> map2 : list) {
				Iterator<String> itkey2=map2.keySet().iterator();
				rep=new HashMap<String, Object>();
				while(itkey2.hasNext()) {
					String key2=itkey2.next();
					//获取全部原始选企业的学生人数
					int num=Integer.valueOf(map2.get("numbers").toString());
					if("error".equals(key2)) {	
						//100人数应该+  -的修改了						
//						map2.replace("numbers",num-nm);
						rep.put("pname",map2.get("pname"));
						rep.put("numTiao", num-nm);
					}else if("add".equals(key2)) {
						//需要+的值
						int addnum=Integer.valueOf(map2.get("add").toString());
						rep.put("pname",map2.get("pname"));						
						//当前总体能够给人数加的值大于当前该人数需要加的值的时候，
						if(result>=addnum) {
							//比如第一个需要加的人数是10，
//							后面一个需要加的人数就得在总体需要加的人数-当前加了的人数之后
							result-=addnum;
//							map2.replace("numbers",num+addnum);
							rep.put("numTiao", num+addnum);
						}else {
//							map2.replace("numbers",num+result);
							rep.put("numTiao", num+result);
							//当result不能满足加的数字了，那么变成0了，就等于累计-为0了
							result=0;
						}							
					}else if("jian".equals(key2)) {
						//因为+>- 所以应该减的人数全部可以满足，获取值，替换map.put
						int jian=Integer.valueOf(map2.get("jian").toString());
						//jian需要减的值， 不是减后的值
//						map2.replace("numbers",num-jian);
						rep.put("pname",map2.get("pname"));
						rep.put("numTiao", num-jian);
					}
				}
				ls.add(rep);
			}
		}else {
			//没有100数字的人数，更具实际，
			//总体添加的数字
			result=jiannum;
			for (Map<String, Object> map2 : list) {
				Iterator<String> itkey2=map2.keySet().iterator();		
				rep=new HashMap<String, Object>();
				while(itkey2.hasNext()) {
					String key2=itkey2.next();
					int num=Integer.valueOf(map2.get("numbers").toString());
					if("add".equals(key2)) {
						int addnum=Integer.valueOf(map2.get("add").toString());
						rep.put("pname",map2.get("pname"));						
						if(result>=addnum) {
							result-=addnum;
//							map2.replace("numbers",num+addnum);
							rep.put("numTiao", num+addnum);
						}else {
//							map2.replace("numbers",num+result);
							rep.put("numTiao", num+result);
							result=0;
						}							
					}else if("jian".equals(key2)) {
						int jian=Integer.valueOf(map2.get("jian").toString());
//						map2.replace("numbers",num-jian);
						rep.put("pname",map2.get("pname"));		
						rep.put("numTiao", num-jian);
					}
				}
				ls.add(rep);
			}
		}
		return ls;
    }
	//加=减  实际可能性不大
	private List<Map<String, Object>> EqualsMethods() {
		List<Map<String, Object>> ls=new ArrayList<Map<String,Object>>();
		Map<String, Object> rep=null;
		for (Map<String, Object> map2 : list) {
			Iterator<String> itkey2=map2.keySet().iterator();
			rep=new HashMap<String, Object>();
			while(itkey2.hasNext()) {
				String key2=itkey2.next();
				int num=Integer.valueOf(map2.get("numbers").toString());
				if("add".equals(key2)) {
					int addnum=Integer.valueOf(map2.get("add").toString());	
//					map2.replace("numbers",num+addnum);//原始的numbers选的人数不变
					rep.put("pname",map2.get("pname"));		
					rep.put("numTiao", num+addnum);
				}else if("jian".equals(key2)) {
					int jian=Integer.valueOf(map2.get("jian").toString());
//					map2.replace("numbers",num-jian);
					rep.put("pname",map2.get("pname"));		
					rep.put("numTiao", num-jian);
				}
			}
			ls.add(rep);
		}
		return ls;
    }
	public Map<String ,Integer> fetNum(int n) {
		Map<String ,Integer> map=new HashMap<String, Integer>();
		int a=n%40;//97:17   87:7
//		map.put( “+ or -      or  100的error”，该+ or -的数字 )
		if(a==0) {
			map.put("ok",0);//120  80   40
//			return 0;
		}else if(a==20) {//100
			map.put("error", n);
			//告知集合有一个100人数专业的数字，因为后期调剂根据是否有该数字进行不同的判断调剂
			numerror=true;
		}else if(a>20) {//78    40-38
			a=40-a;//小值趋向的方向
			map.put("add", a);
		}else if(a<20) {//- 90
//			a=a;
			map.put("jian", a);
		}		
		return map;
	}
	public static void main(String[] args) {
		Type t=new Type();
		t.me1();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

