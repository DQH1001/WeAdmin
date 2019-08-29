package com.java.dao;

import java.text.DecimalFormat;
import java.util.*;

import org.apache.ibatis.session.SqlSession;

import com.java.entity.*;
import com.javaweb.mapper.ICompsMapper;
import com.javaweb.mybatis.DBTools;

public class ICompsMapperDao {
	private SqlSession sess=null;
	private ICompsMapper cm=null;

	public static void main(String[] args) {
		ICompsMapperDao im=new ICompsMapperDao();
		System.out.println(im.selectCompsAllForT222());
		//Map<String,List> list=new HashMap<String, List>();
		//List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		//list=im.getProANDComps();
		//System.out.println(list.toString());	
//		int count=im.insertLike(23, 17);
//		System.out.println(count);
		//im.InsertScoresList();
	}
	public ICompsMapperDao() {
		
		sess=DBTools.getSession();
		cm=sess.getMapper(ICompsMapper.class);
	}
	//��������scores����������ֵ,��Ҫ����ʹ��
//	public void InsertScoresList() {
//		List<Scores> sco=new ArrayList<Scores>();
//		int[] compIds= {1,7,11,12,13};
//		/*
//		 * ��˾1 רҵ 1,3,4,5,6
//		 *    7    1,3,4,5,13
//		 *    11   1,2,3,7,12
//		 *    12   1,2,4,8,11
//		 *    13   2,4,3,10,11
//		 */
//		int[] pros1= {1,3,4,5,6};
//		int[] pros2= {1,3,4,5,13};
//		int[] pros3= {1,2,3,7,12};
//		int[] pros4= {1,2,4,8,11};
//		int[] pros5= {2,4,3,10,11};
//		//Random r=null;
//		Scores score=null;
//		int index1;
//		int index2;
//		int cid;
//		int pid;
//		for(int sid=200;sid<=400;sid++) {
//			//r=new Random();
//			index1=(int)(Math.random()*compIds.length);
//			cid=compIds[index1];
//			if(cid==1) {
//				index2=(int)(Math.random()*pros1.length);
//				pid=pros1[index2];
//			}else if(cid==7) {
//				index2=(int)(Math.random()*pros2.length);
//				pid=pros2[index2];
//			}else if(cid==11) {
//				index2=(int)(Math.random()*pros3.length);
//				pid=pros3[index2];
//			}else if(cid==12) {
//				index2=(int)(Math.random()*pros4.length);
//				pid=pros4[index2];
//			}else {
//				index2=(int)(Math.random()*pros5.length);
//				pid=pros5[index2];
//			}
//			score=new Scores();
//			score.setS_cid(cid);
//			score.setS_pid(pid);
//			score.setS_sid(sid);
//			sco.add(score);
////			System.out.println(score.getS_cid()+" / "+score.getS_pid()+" / "+score.getS_sid());
//		}
//		int count=0;
//    	count=cm.InsertScoresList(sco);
//    	sess.commit();  		
//    	System.out.println(count);
//	}
	//��ѯһ��ѧ��������Ϣ
	public Student selectStuInfoBysid(int sid) {
		Student stu=new Student();
			stu.setSid(sid);
		Student student=cm.selectStuInfoBysid(stu);
		return student;
	}
	//���¹�˾���״̬
	public int updateNoCheckCompsBycid(int cid) {
		int count=0;
    	try {    		
    		count=cm.updateNoCheckCompsBycid(cid);
    		sess.commit();  		
    	}catch (Exception e) {
			e.printStackTrace();
			sess.rollback();
		} 
    	return count;
	}
	//��ѯ����δ��˹�˾
	public List<Map<String, Object>> selectNoCheckCompsAll(){
		List<Comps> comps=new ArrayList<Comps>();
		comps=cm.selectNoCheckCompsAll();
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		for(Comps com:comps) {
			map=new HashMap<String, Object>();
			map.put("cid", com.getCid());
			map.put("cname", com.getCname());
			map.put("cdetail", com.getCdetail());
			map.put("c_pros", com.getC_pros());
			map.put("numbers", com.getNumbers());
			map.put("pros", com.getPros());
			map.put("clogo", com.getClogo());
			listMap.add(map);
		}
		return listMap;
	}
	//��ѯ���й�˾����
	public List<Map<String,Object>> selectNumsAndPros(){
		List<Comps> comps=new ArrayList();
		comps=cm.selectNumsAndPros();
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		for(Comps com:comps) {
			map=new HashMap<String, Object>();
			map.put("cid", com.getCid());
			map.put("cname", com.getCname());
			map.put("cdetail", com.getCdetail());
			map.put("c_pros", com.getC_pros());
			map.put("numbers", com.getNumbers());
			map.put("pros", com.getPros());
			map.put("clogo", com.getClogo());
			listMap.add(map);
		}
		return listMap;
	}
	//��ѯ���й�˾��רҵ
	public Map<String,List> getProANDComps(){
		Map<String,List> mp=new HashMap<String, List>();
		List<Projects> list=new ArrayList<Projects>();
		list=cm.selectProjectsAll();
		List<Map<String,Object>> ls=new ArrayList();
		Map<String,Object> map=null;
		for (Projects pro : list) {
			map=new HashMap<String, Object>();
			map.put("pid", pro.getPid());
			map.put("pname", pro.getPname());
			ls.add(map);
		}
		mp.put("projs", ls);
		ls=new ArrayList();
		List<Comps> list1=new ArrayList<Comps>();
		list1=cm.selectCompsAll();
		for (Comps comp : list1) {
			map=new HashMap<String, Object>();
			map.put("cid", comp.getCid());
			map.put("cname", comp.getCname());
			ls.add(map);
		}
		mp.put("comps", ls);
		return mp;
	}
	//����cid��ѯ��˾���л���
	public List<Map<String,Object>> selectCompWordsBycid(int cid){
		List<Words> words=new ArrayList();
		words=cm.selectCompWordsBycid(cid);
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		for(Words word:words) {
			map=new HashMap<String, Object>();
			map.put("wid", word.getWid());
			map.put("wtitle", word.getWtitle());
			map.put("wcontent", word.getWcontent());
			map.put("wdate", word.getWdate());
			map.put("w_cid", word.getW_cid());
			map.put("w_sid", word.getW_sid());
			map.put("wimages", word.getWimages());
			map.put("wauthor", word.getWauthor());
			map.put("wcount", word.getWcount());
			map.put("whid", word.getWhid());
			map.put("slogo", "upload/"+word.getStu().getSlogo());
			//map.put("clogo", "images/clogo/"+word.getComp().getClogo());
			map.put("sname", word.getStu().getSname());
			listMap.add(map);
		}
		return listMap;
	}
	//���¹�˾���ⱻ�ظ�����
	public boolean updateCompWordsBywhid(int whid) {
		int count=0;
    	try {    		
    		count=cm.updateCompWordsBywhid(whid);
    		sess.commit();  		
    	}catch (Exception e) {
			e.printStackTrace();
			sess.rollback();
		} 
    	return count>0;
	}
	//��������
	public boolean insertCompWords(Words word) {
		int count=0;
    	try {    		
    		count=cm.insertCompWords(word);
    		sess.commit();  		
    	}catch (Exception e) {
			e.printStackTrace();
			sess.rollback();
		} 
    	return count>0;
	}
	//�޸�ѧ��ͷ��
	public int uploadStuSlogo(Student stu) {
		int count=-1;
    	try {    		
    		count=cm.uploadStuSlogo(stu);
    		sess.commit();  		
    	}catch (Exception e) {
			e.printStackTrace();
			sess.rollback();
		} finally {
			sess.close();
		}
    	return count;
	}
	//�޸�ѧ����������
	public int updateStuInfo(Student stu) {
		int count=-1;
    	try {    		
    		count=cm.updateStuInfo(stu);
    		sess.commit();  		
    	}catch (Exception e) {
			e.printStackTrace();
			sess.rollback();
		} finally {
			sess.close();
		}
    	return count;
	}
	//�޸�ѧ������ǩ��������
		public int updateStuInfo1(Student stu) {
			int count=-1;
	    	try {    		
	    		count=cm.updateStuInfo1(stu);
	    		sess.commit();  		
	    	}catch (Exception e) {
				e.printStackTrace();
				sess.rollback();
			} finally {
				sess.close();
			}
	    	return count;
		}
	//��ѯѧ���ղصĹ�˾
	public Map<String,Object> selectLikeAll(int sid,int cid){
		Map<String,Object> mp=new HashMap();
		//��װcomp����
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		//����sql����ֵ
		List<Comps> comps=new ArrayList<Comps>();
		//������װ
		Map<String,Object> map=null;
		comps=cm.selectLikeAll(sid);
		//�жϸù�˾�Ƿ��ղع�
		int like=0;
		for (Comps comp : comps) {
			if(cid==comp.getCid()) {
				like=1;
			}
			map=new HashMap<String, Object>();
			map.put("cid", comp.getCid());
			map.put("clogo", "images/clogo/"+comp.getClogo());
			map.put("cname", comp.getCname());
			map.put("pros",comp.getC_pros());
			listMap.add(map);
		}
		mp.put("listMap", listMap);
		mp.put("like", like);
		return mp;
	}
	//ѧ���ղع�˾
		public int insertLike(int sid,int cid1) {
			String cid=","+cid1+",";
			int count=0;
	    	try {    		
	    		count=cm.insertLike(sid,cid);
	    		sess.commit();  		
	    	}catch (Exception e) {
				e.printStackTrace();
				sess.rollback();
			} 
	    	return count;
		}
	//ѧ��ȡ���ղصĹ�˾
	public int deleteLike(int sid,int cid1) {
		String cid=","+cid1+",";
		int count=0;
    	try {    		
    		count=cm.deleteLike(sid,cid);
    		sess.commit();  		
    	}catch (Exception e) {
			e.printStackTrace();
			sess.rollback();
		} 
    	return count;
	}
	//��������ѧ����Ϣ
	public int SaveExcelStus(List<Student> stus) {
		int count=0;
    	try {    		
    		count=cm.InsertStusList(stus);
    		sess.commit();  		
    	}catch (Exception e) {
			e.printStackTrace();
			sess.rollback();
		} 
    	return count;
	}
	//��ѯѧ��������Ϣ
	public List<Map<String,Object>> selectStusChooseInfo(){
		List<Map<String,Object>> lm=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		List<Student> stus=new ArrayList<Student>();
		stus=cm.selectStusChooseInfo();
		for (Student stu : stus) {
			map=new HashMap<String, Object>();
			map.put("sid", stu.getSid());
			map.put("sname", stu.getSname());
			map.put("sdate", stu.getSdate());
			map.put("sclass", stu.getClasses().getClname());
			map.put("slogo", "upload/"+stu.getSlogo());
			map.put("cname", stu.getCname());
			map.put("pname", stu.getPname());
			map.put("ssex", stu.getSsex());
			map.put("stel", stu.getStel());
			lm.add(map);
		}
		return lm;
	}
	//��ѯ��������ҳ��ѧ������
	public List<Map<String,Object>> getProfileWords(int spfsid){
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		List<Spfwords> ls=new ArrayList<Spfwords>();
		ls=cm.getProfileWords(spfsid);
		Map<String,Object> map=null;
		for (Spfwords spfwords : ls) {
			map=new HashMap<String, Object>();
			map.put("spflogo", spfwords.getSpflogo());
			map.put("spfdate", spfwords.getSpfdate());
			map.put("spftest", spfwords.getSpftest());
			listMap.add(map);
		}
		return listMap;
		
	}
	//��ѯ��˾���뱻ѡ������ʾchartͼ
//	public Map<String,Object> selectScoreforform(){
//		List<Scores> scores=new ArrayList();
//		Map<String,Object> mp=new HashMap<String, Object>();
//		List<String> ls1=new ArrayList<String>();
//		List<Integer> ls2=new ArrayList<Integer>();
//		scores=cm.selectScoreforform();
//		for (Scores score : scores) {
//			ls1.add(score.getComps().getCname());
//			ls2.add(score.getNumber());
//		}		
//		mp.put("cnames", ls1);
//		mp.put("numbers", ls2);
//		return mp;
//	}
	public Map<String,Object> selectScoreforform(){
		List<Scores> scores=new ArrayList();
		scores=cm.selectScoreforform();
		//������listmap
		Map<String,Object> mp=new HashMap<String, Object>();
		//1.categories:List<String> ��ֵ
		List<String> ls1=new ArrayList<String>();
		//2.data: ��ֵ
		List<Map<String,Object>> ls2=new ArrayList<>();
		Map<String,Object> map1=null;
		//3.drilldown:{series:ls3}��ֵ
		List<Map<String,Object>> ls3=new ArrayList<>();
		Map<String,Object> map2=null;
		List<List<Object>> list=new ArrayList<List<Object>>();
		
		for (Scores score : scores) {
			ls1.add(score.getComps().getCname());
			
			map1=new HashMap<String, Object>();			
			map1.put("name", score.getComps().getCname());
			map1.put("y", score.getNumber());
			map1.put("drilldown", score.getComps().getCname());
			ls2.add(map1);
			
			map2=new HashMap<String, Object>();			
			map2.put("name", score.getComps().getCname());
			map2.put("id", score.getComps().getCname());
			list=new ArrayList<List<Object>>();
			//System.out.println(score.getS_cid()+" / "+score.getComps().getC_pros());
			list=selectProjectsByCid2(score.getS_cid(),score.getComps().getC_pros());
			map2.put("data", list);
			ls3.add(map2);
			
		}		
		mp.put("categories", ls1);
		mp.put("data", ls2);
		mp.put("series", ls3);
		return mp;
	}
	//id��ѯ��˾רҵ������һ����������ݷ�װ
		public List<List<Object>> selectProjectsByCid2(int cid,String projects){
			List<Projects> pros=new ArrayList();
			Comps com=new Comps();
			com.setCid(cid);
			com.setC_pros(projects);
			//System.out.println(com.getC_pros()+" / "+com.getCid());
			pros=cm.selectProjectsByCid(com);
			List<List<Object>> listMap=new ArrayList<>();
			List<Object> ls=new ArrayList<Object>();
			for(Projects pro:pros) {
				ls=new ArrayList<Object>();
				ls.add(pro.getPname());
				ls.add(pro.getNumbers());
				listMap.add(ls);
			}
			//System.out.println(listMap.toString());
			return listMap;
		}
	//id��ѯ��˾רҵ
	public List<Map<String,Object>> selectProjectsByCid(int cid,String projects){
		List<Projects> pros=new ArrayList();
		Comps com=new Comps(cid,0,null,null);
		com.setC_pros(projects);
		pros=cm.selectProjectsByCid(com);
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		for(Projects pro:pros) {
			map=new HashMap<String, Object>();
			map.put("pid", pro.getPid());
			map.put("pname", pro.getPname());
			map.put("pdetail", pro.getPdetail());
			map.put("numbers", pro.getNumbers());
			map.put("ptest", pro.getPtest());
			map.put("plogo", pro.getPlogo());
			listMap.add(map);
		}
		return listMap;
	}
	
	//����cid��ѯ�ù�˾Ա������רҵ��ѡ�����Լ���˾���屻ѡ���� ,�����ȡ�����չʾ
	public List<Map<String,Object>> selectUsersBycid(int cid){
		List<Users> users=new ArrayList();		
		users=cm.selectUsersBycid(cid);
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		String color=null;
		String pc="0.00";
		for(Users user:users) {
			if(user.getCnum()==0) {
				pc="10";
			}else {
				DecimalFormat df=new DecimalFormat("0.00");
				pc=df.format(((float)user.getPnum()/user.getCnum())*100);
			}
			color=CheckColor(pc);
			double pc1=Double.valueOf(pc);
			pc=pc1+"%";
			map=new HashMap<String, Object>();
			map.put("uid", user.getUid());
			map.put("uname", user.getUname());
			map.put("u_cid", user.getU_cid());
			map.put("ulogo", "images/photos/"+user.getUlogo());
			map.put("pc", pc);
			map.put("pcwidth","width:"+pc);
			map.put("color", color);
			map.put("upwd", user.getUpwd());
			map.put("udate", user.getUdate());
			map.put("urose", user.getUrose());
			map.put("u_pros", user.getU_pros());
		
			listMap.add(map);
		}
		return listMap;
	}
	private String CheckColor(String numb) {
		double num=Double.valueOf(numb);
		if(num<=20.00) {
			return "progress-bar progress-bar-danger";
		}else if(num<=40.00&&num>=20.00) {
			return "progress-bar progress-bar-warning";
		}else if(num<=60.00&&num>=40.00) {
			return "progress-bar progress-bar-warning";
		}else{
			return "progress-bar progress-bar-success";
		}
	}
	//id��ѯ��˾,����ҳ
	public Map<String,Object> selectComCid(int cid){
		Comps com=new Comps();		
		com=cm.selectComCid(cid);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("cid", com.getCid());
		map.put("cname", com.getCname());
		map.put("cdetail", com.getCdetail());
		map.put("cdate", com.getCdate());
		map.put("c_pros", com.getC_pros());
		map.put("numbers", com.getNumbers());
		map.put("pros", com.getPros());
		map.put("clogo", "images/clogo/"+com.getClogo());
		map.put("cimgs", com.getCimgs());
		map.put("ctext", "txt/"+com.getCtext());
		map.put("ctvchoose", com.getCtvchoose());
		map.put("cvideo", "video/"+com.getCvideo());
		return map;
	}
	//id��ѯ��˾רҵ,��ϸҳ
		public List<Map<String,Object>> selectProByCid(Comps com){
			List<Projects> pros=new ArrayList();
			
			pros=cm.selectProjectsByCid(com);
			List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
			Map<String,Object> map=null;
			for(Projects pro:pros) {
				map=new HashMap<String, Object>();
				map.put("pid", pro.getPid());
				map.put("pname", pro.getPname());
				map.put("pdetail", pro.getPdetail());
				map.put("numbers", pro.getNumbers());
				map.put("ptest", pro.getPtest());
				map.put("plogo", "images/plogo/"+pro.getPlogo());
			
				listMap.add(map);
			}
			return listMap;
		}
	//ģ����ѯ��˾
	public List<Map<String,Object>> selectNearlyComps(String cid,String cname,String numbers,String c_pros){
		List<Comps> comps=new ArrayList();
		Comps co=new Comps();
		if(!cid.equals(null)&&!cid.equals("")) {
			co.setCid(Integer.valueOf(cid));
		}
		co.setCname("%"+cname+"%");		
		if(!numbers.equals(null)&&!numbers.equals("")) {
			co.setNumbers(Integer.valueOf(numbers));
		}
		if(!c_pros.equals("0")) {			
			co.setC_pros("%"+c_pros+"%");
		}
		comps=cm.selectNearlyComps(co);
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		for(Comps com:comps) {
			map=new HashMap<String, Object>();
			map.put("cid", com.getCid());
			map.put("cname", com.getCname());
			map.put("cdetail", com.getCdetail());
			map.put("c_pros", com.getC_pros());
			map.put("numbers", com.getNumbers());
			map.put("pros", com.getPros());
			map.put("clogo", com.getClogo());
			listMap.add(map);
		}
		return listMap;
	}
	//ģ����ѯѧ����Ϣ
		public List<Map<String,Object>> selectNearlyStus(String sid,String sname,String cid,String pid){
			List<Student> stus=new ArrayList();
			Student co=new Student();
			if(!sid.equals(null)&&!sid.equals("")) {
				co.setSid(Integer.valueOf(sid));
			}
			co.setSname("%"+sname+"%");		
			if(!cid.equals("0")) {
				co.setCcid(cid);
			}
			if(!pid.equals("0")) {	
				co.setPpid(pid);
			}
			//System.out.println(co.getCcid()+" / "+co.getPpid()+" / "+co.getSid()+" / "+co.getSname());
			stus=cm.selectNearlyStus(co);
			List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
			Map<String,Object> map=null;
			for(Student stu:stus) {
				map=new HashMap<String, Object>();
				map.put("sid", stu.getSid());
				map.put("sname", stu.getSname());
				map.put("sdate", stu.getSdate());
				map.put("sclass", stu.getClasses().getClname());
				map.put("slogo", "upload/"+stu.getSlogo());
				map.put("cname", stu.getCname());
				map.put("pname", stu.getPname());
				listMap.add(map);
			}
			return listMap;
		}
	//ѧ��ע����֤
	public Student RegistrationChecked(String sname) {
		Student stu=new Student();
		stu.setSname(sname);
		Student student=cm.RegistrationChecked(stu);
		return student;
	}
	//ѧ��ע��
	public int stuRegistration(String sname,String spwd,int s_clid,String slogo) {
		Student stu=new Student();
		stu.setSname(sname);
		stu.setSpwd(spwd);
		stu.setS_clid(s_clid);
		stu.setSlogo(slogo);
		int count=0;
    	try {    		
    		cm.stuRegistration(stu);
    		sess.commit();  		
    	}catch (Exception e) {
			e.printStackTrace();
			sess.rollback();
		} finally {
			sess.close();
		}
    	return count;
	}
	//��������ҳ��������
	public int insertPost(Spfwords word) {
		int count=0;
    	try {    		
    		count=cm.insertPost(word);
    		sess.commit();  		
    	}catch (Exception e) {
			e.printStackTrace();
			sess.rollback();
		} 
    	return count;
	}
	//��ʦ��¼��֤
	public Teachers loginCheckedTea(String sname, String spwd) {
		Teachers tea=new Teachers();
		tea.setTeaname(sname);		
		tea.setTeapwd(spwd);
		Teachers teacher=cm.loginCheckedTea(tea);
		return teacher;
	}
	//ѧ����¼��֤
	public Student loginChecked(String sname,String spwd) {
		Student stu=new Student();
		//if(sname!=null&&!sname.equals("")) {
			stu.setSname(sname);
		//}
		//if(spwd!=null&&!spwd.equals("")) {
			stu.setSpwd(spwd);
		//}		
		Student student=cm.loginChecked(stu);
		return student;
	}
	
	public int insertScores(Scores score) {
		int count=0;
    	try {    		
    		count=cm.insertScores(score);
    		sess.commit();  		
    	}catch (Exception e) {
			e.printStackTrace();
			sess.rollback();
		} finally {
			sess.close();
		}
    	return count;
	}
	public int updateScores(Scores score) {
		int count=0;
    	try {    		
    		count=cm.updateScores(score);
    		sess.commit();  		
    	}catch (Exception e) {
			e.printStackTrace();
			sess.rollback();
		} finally {
			sess.close();
		}
    	return count;
	}
	//��ѯ�����������Ϣ
	public Scores selectScoresAll(int sid){
		Scores score=new Scores();
		score=cm.selectScoresAll(sid);
		return score;
	}
	
	//��ѯ���й�˾רҵ
	public List<Map<String, Object>> selectProjectsAll() {
		List<Projects> projects=new ArrayList();
		//��ѯ���й�˾����
		projects=cm.selectProjectsAll();
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		for(Projects project:projects) {
			map=new HashMap<String, Object>();
			map.put("pid", project.getPid());
			map.put("pname", project.getPname());
			listMap.add(map);
		}
		return listMap;
	}
	//��ѯ���а༶
	public List<Map<String, Object>> selectClassesAll() {
		List<Classes> classes=new ArrayList();
		classes=cm.selectClassesAll();
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		for(Classes cla:classes) {
			map=new HashMap<String, Object>();
			map.put("clid", cla.getClid());
			map.put("clname", cla.getClname());
			listMap.add(map);
		}
		return listMap;
	}
	//Ժ�����ܣ�һ���޸�
		public List<Map<String, Object>> selectCompsAllForT222() {
			Comps comp=new Comps();
			comp.setNumbers(0);
			List<Comps> comps=new ArrayList();
			comps=cm.selectNearlyComps(comp);
			//�洢ԭ��������
			int count=0;
			//��������
			int i=0;
			int num;
			//�洢��˾ԭ����
			int[] nums=new int[comps.size()];
			//��ѯ������
			List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
			Map<String,Object> map=null;
			System.out.println("ԭʼֵ");
			for(Comps com:comps) {
				num=com.getNumbers();
				map=new HashMap<String, Object>();
				map.put("infoid", com.getCid());
				map.put("uname", com.getCname());
				map.put("basicNum", num);
				map.put("iattendance", num+com.getCnumChange());
				map.put("cnumChange", com.getCnumChange());
				listMap.add(map);
//				if(num%10>=5) {
//					count+=num%10-10;
//					System.out.println("��˾id��"+com.getCid()+" / ��˾������"+num+" / ��������10������"+(num%10-10));
//				}else {
//					count+=num%10;
//					System.out.println("��˾id��"+com.getCid()+" / ��˾������"+num+" / ��������10������"+(num%10));
//				}
				//ԭ�ȸ���˾������
				count+=num;
				//����������
				nums[i]=num;
				System.out.print(num+" / ");
				i++;
				
			}
			System.out.println(count);
			//�������߶�ʮһ���Ȼ���ʮ�ı���
			int[] nums2=nums;
			//�ĺ��������
			int countAfter=0;
			for (int n=0;n<nums2.length;n++) {
				if(nums2[n]%10>=5) {
					nums2[n]=(nums2[n]/10+1)*10;
				}else {
					nums2[n]=(nums2[n]/10)*10;
				}
				countAfter+=nums[n];
			}
			//��ʾһ���Ըĺ��ֵ
			System.out.println("�޸ĺ�");
			for (int j = 0; j < nums2.length; j++) {
				
				System.out.print(nums2[j]+" / ");
			}
			System.out.println(countAfter);
			
			//�ڽ���������ٵ��������·�װһ��
			if(count-countAfter==0) {
				return fuzhufengzhuang(listMap,nums2);
			}else if(count-countAfter>0) {
				int cha=count-countAfter;
				while(cha>0) {
					//�ҳ���Сֵ
					int min=0;
					for (int j = 0; j < nums2.length; j++) {
						if(j==0) {
							min=nums2[j];
						}
						if(min>nums2[j]) {
							min=nums2[j];
						}
					}
					System.out.println("���·�װ����ǰ�������Сֵ��"+min);
					//��һ����Сֵ��������
					for (int j = 0; j < nums2.length&&cha>0; j++) {
						if(nums2[j]==min) {
							if(cha>=10) {
								nums2[j]+=10;
								cha=cha-10;
							}else{
								nums2[j]+=cha;
								cha=0;
							}
						}
					}
				}
				return fuzhufengzhuang(listMap,nums2);
			}else {
				int cha=count-countAfter;
				while(cha<0) {
					//�ҳ����ֵ
					int max=0;
					for (int j = 0; j < nums2.length; j++) {
						if(j==0) {
							max=nums2[j];
						}
						if(max<nums2[j]) {
							max=nums2[j];
						}
					}
					System.out.println("���·�װ����ǰ��������ֵ��"+max);
					//��һ�����ֵ��������
					for (int j = 0; j < nums2.length&&cha<0; j++) {
						if(nums2[j]==max) {
							if(cha<=-10) {
								nums2[j]-=10;
								cha=cha+10;
							}else{
								nums2[j]+=cha;
								cha=0;
							}
						}
					}
				}
				return fuzhufengzhuang(listMap,nums2);
			}
		}
		//������һ����������ݷ�װ
		private List<Map<String, Object>> fuzhufengzhuang(List<Map<String, Object>> listMap, int[] nums2) {
			//Map���ϲ��ö�̬�ӳ��ȣ���������Ϊ�����޸ģ��������һ���µ�listmap���´洢
			Map<String,Object> m=null;
			List<Map<String,Object>> ls=new ArrayList<Map<String,Object>>();
			//��������
			int i=0;
			for (Map<String, Object> map : listMap) {
				m=new HashMap<String, Object>();
				m.put("infoid", map.get("infoid"));
				m.put("uname", map.get("uname"));
				m.put("basicNum", map.get("basicNum"));
				m.put("iattendance", nums2[i]);				
				m.put("cnumChange", nums2[i]-(int)(map.get("basicNum")));
//				map.put("basicNum", num);
//				map.put("iattendance", num+com.getCnumChange());
//				map.put("cnumChange", com.getCnumChange());
				ls.add(m);
				i++;
			}
			return ls;
		}
	//Ժ�����ܣ���ʾ��ǰ��ҵ��ѡ�����������޸�
	public List<Map<String, Object>> selectCompsAllForT2() {
		List<Comps> comps=new ArrayList();
		comps=cm.selectNumsAndPros();
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=null;
		for(Comps com:comps) {
			if(com.getNumbers()>0) {
				map=new HashMap<String, Object>();
				map.put("infoid", com.getCid());
				map.put("uname", com.getCname());
				map.put("basicNum", com.getNumbers());
				map.put("iattendance", com.getNumbers()+com.getCnumChange());
				map.put("cnumChange", com.getCnumChange());
				listMap.add(map);
			}
		}
		return listMap;
	}
	public int updateYzTable(List<Comps> listMap) {
		int count=-1;
    	count=cm.updateYzTable(listMap);
    	sess.commit();  
		return count;
	}

	
}
