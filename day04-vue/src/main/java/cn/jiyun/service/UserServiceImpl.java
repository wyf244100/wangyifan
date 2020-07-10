package cn.jiyun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jiyun.mapper.DeptMapper;
import cn.jiyun.mapper.EmployeeMapper;
import cn.jiyun.pojo.Dept;
import cn.jiyun.pojo.Employee;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	DeptMapper deptMapper;
	@Override
	public List<Employee> findAll() {
		return employeeMapper.findAll();
	}
	@Override
	public List<Dept> findDepts() {
		return deptMapper.findDepts();
	}
	@Override
	public void add(Employee employee) {
		employeeMapper.add(employee);
	}
	@Override
	public void del(Integer eid) {
		employeeMapper.del(eid);
	}
	@Override
	public Employee findByid(Integer eid) {
		return employeeMapper.findByid(eid);
	}
	@Override
	public void update(Employee employee) {
		employeeMapper.update(employee);
	}
	
	
	
}
