package cn.jiyun.mapper;

import java.util.List;

import cn.jiyun.pojo.Employee;

public interface EmployeeMapper {

	List<Employee> findAll();

	void add(Employee employee);

	void del(Integer eid);

	Employee findByid(Integer eid);

	void update(Employee employee);

}
