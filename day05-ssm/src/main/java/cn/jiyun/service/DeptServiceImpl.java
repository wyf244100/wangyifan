package cn.jiyun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jiyun.mapper.DeptMapper;
import cn.jiyun.pojo.Dept;

@Service
@Transactional
public class DeptServiceImpl implements DeptService{

	@Autowired
	DeptMapper deptMapper;
	
	@Override
	public List<Dept> findAll() {
		return deptMapper.findAll();
	}

}
