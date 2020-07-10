package cn.jiyun.service;

import java.util.List;

import cn.jiyun.pojo.Dept;
import cn.jiyun.pojo.Employee;

public interface UserService {

	List<Employee> findAll();

	List<Dept> findDepts();

	void add(Employee employee);

	void del(Integer eid);

	Employee findByid(Integer eid);

	void update(Employee employee);

}
