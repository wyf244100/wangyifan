package com.xiaoshu.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.CompanyMapper;
import com.xiaoshu.dao.PersonMapper;
import com.xiaoshu.entity.Company;
import com.xiaoshu.entity.Person;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
@Transactional
public class PerService {

	
	@Autowired
	private PersonMapper pm;
	@Autowired
	private CompanyMapper cm;
	public List<Company> findC() {
		// TODO Auto-generated method stub
		return cm.selectAll();
	}
	public PageInfo<Person> findAll(Person per, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		List<Person> userList = pm.findAll(per);
		PageInfo<Person> pageInfo = new PageInfo<Person>(userList);
		return pageInfo;
	}
	public Object existUserWithUserName(String getpName) {
		
		List<Person> userList = pm.findName(getpName);
		return userList.isEmpty()?null:userList.get(0);
	}
	public void addPer(Person per) {
		// TODO Auto-generated method stub
		pm.insert(per);
	}
	public void update(Person per) {
		// TODO Auto-generated method stub
		pm.updateByPrimaryKey(per);
	}
	public void delPer(int parseInt) {
		// TODO Auto-generated method stub
		pm.deleteByPrimaryKey(parseInt);
	}
	
}
