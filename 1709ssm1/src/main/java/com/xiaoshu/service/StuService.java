package com.xiaoshu.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.GradeMapper;
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.entity.Grade;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
@Transactional
public class StuService {

	
	@Autowired
	private StudentMapper sm;
	
	@Autowired
	private GradeMapper gm;

	public List<Grade> findGrade() {
		// TODO Auto-generated method stub
		return gm.selectAll();
	}

	public PageInfo<Student> findAll(Student student, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		List<Student> stuList = sm.findAll(student);
		PageInfo<Student> pageInfo = new PageInfo<Student>(stuList);
		return pageInfo;
	}

	public void deleteStu(int parseInt) {
		// TODO Auto-generated method stub
		sm .deleteByPrimaryKey(parseInt);
	}

	public Object existUserWithUserName(String name) {
		List<Student> stuList = sm.selectName(name);
		return stuList.isEmpty()?null:stuList.get(0);
	}

	public void addStu(Student stu) {
		// TODO Auto-generated method stub
		sm.insert(stu);
	}

	public void updateStu(Student stu) {
		// TODO Auto-generated method stub
		sm.updateByPrimaryKey(stu);
	}

	public List<Student> findStu() {
		// TODO Auto-generated method stub
		return sm.findAll(null);
	}



	public Integer selectGid(String gname) {
		// TODO Auto-generated method stub
		return gm.selectGid(gname);
	}
	
}
