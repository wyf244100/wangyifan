package cn.jiyun.mapper;

import java.util.List;

import cn.jiyun.pojo.Emp;

public interface EmpMapper {

	List<Emp> findAll(Emp emp);

	void add(Emp emp);

	void delete(Integer eid);

	Emp findByID(Integer eid);

	void update(Emp emp);

	void deleteAll(Integer[] ids);

}
