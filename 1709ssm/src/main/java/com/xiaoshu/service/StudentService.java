package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.gradeMapper;
import com.xiaoshu.dao.studentMapper;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.grade;
import com.xiaoshu.entity.student;
import com.xiaoshu.entity.UserExample.Criteria;

import tk.mybatis.mapper.entity.Example;

@Service
public class StudentService {

	@Autowired
	private studentMapper sm;
	@Autowired
	private gradeMapper gm;

	public List<grade> findgrade() {
		// TODO Auto-generated method stub
		return gm.selectAll();
	}

	public PageInfo<student> findStudent(student student, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		List<student> userList = sm.selectStudent(student);
		PageInfo<student> pageInfo = new PageInfo<student>(userList);
		return pageInfo;
	}

	public student existStuWithName(String name) {
		// TODO Auto-generated method stub
		return sm.existStuWithName(name);
	}

	public void addStu(student student) {
		// TODO Auto-generated method stub
		sm.insert(student);
	}

	public void updateStu(student student) {
		// TODO Auto-generated method stub
		sm.updateByPrimaryKeySelective(student);
	}

	public void deleteStu(int parseInt) {
		// TODO Auto-generated method stub
		sm.deleteByPrimaryKey(parseInt);
	}

	public List<student> findAll() {
		return sm.selectStudent(null);
	}

	public int findId(String grade) {
		// TODO Auto-generated method stub
		
		return gm.selectId(grade);
	}

	
	
	
	
}
