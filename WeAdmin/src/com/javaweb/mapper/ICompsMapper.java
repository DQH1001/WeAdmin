package com.javaweb.mapper;
/**
 * 接口模式开发，只有java接口没有java类型的实现类，
 * 视为xml是当前mapper接口的xml形式的实现类
 * @author Administrator
 * 声明的全部都是公司一个数据库操作对象的接口
 */

import java.util.List;
//import org.apache.ibatis.annotations.Select;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.java.entity.*;

public interface ICompsMapper {
	
	public List<Comps> selectNumsAndPros();
	
	public List<Projects> selectProjectsByCid(Comps comp);
	
	public Student loginChecked(Student stu);
	
	public List<Comps> selectNearlyComps(Comps comp);

	public List<Projects> selectProjectsAll();
	
	public List<Classes> selectClassesAll();
	
	public Student RegistrationChecked(Student stu);	
	
	public void stuRegistration(Student stu);
	
	public Scores selectScoresAll(int sid);
	
	public int updateScores(Scores score);
	
	public int insertScores(Scores score);
	
	public Comps selectComCid(int cid);
	
	public List<Users> selectUsersBycid(int cid);
	
	public List<Words> selectCompWordsBycid(int cid);
	
	public int updateCompWordsBywhid(int whid);
	
	public int insertCompWords(Words word);
	
	public List<Scores> selectScoreforform();
	
	public List<Comps> selectLikeAll(int sid);
	
	public int deleteLike(@Param("sid")int sid,@Param("cid")String cid);

	public int insertLike(@Param("sid")int sid,@Param("cid")String cid);
	
	public int updateStuInfo(Student stu);
	
	public int updateStuInfo1(Student stu);
	
	public int uploadStuSlogo(Student stu);
	
	public List<Student> selectStusChooseInfo();
	
	public List<Comps> selectCompsAll();
	
	public List<Student> selectNearlyStus(Student stu);

	public Teachers loginCheckedTea(Teachers tea);
	
	public List<Spfwords> getProfileWords(int spfsid);

	public int insertPost(Spfwords word);
	
	public int InsertStusList(List<Student> stus);

	public List<Comps> selectNoCheckCompsAll();
	
	public int updateNoCheckCompsBycid(int cid);
	
	public Student selectStuInfoBysid(Student stu);
}
