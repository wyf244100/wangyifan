package cn.jiyun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jiyun.mapper.CityMapper;
import cn.jiyun.mapper.EmpMapper;
import cn.jiyun.pojo.City;
import cn.jiyun.pojo.Emp;

@Service
@Transactional
public class EmpServiceImpl implements EmpService{

	@Autowired
	EmpMapper empMapper;
	@Autowired
	CityMapper cityMapper;
	
	@Override
	public List<Emp> findAll(Emp emp) {
		return empMapper.findAll(emp);
	}

	@Override
	public List<City> findSheng() {
		return cityMapper.findSheng();
	}

	@Override
	public List<City> findShi(Integer cid) {
		// TODO Auto-generated method stub
		return cityMapper.findShi(cid);
	}

	@Override
	public String findCityByID(Integer cid) {
		return cityMapper.findCityByID(cid);
	}

	@Override
	public void add(Emp emp) {
		empMapper.add(emp);
	}

	@Override
	public void delete(Integer eid) {
		empMapper.delete(eid);
	}

	@Override
	public Emp findByID(Integer eid) {
		return empMapper.findByID(eid);
	}

	@Override
	public void update(Emp emp) {
		empMapper.update(emp);
	}

	@Override
	public void deleteAll(Integer[] ids) {
		empMapper.deleteAll(ids);
	}

	
}
