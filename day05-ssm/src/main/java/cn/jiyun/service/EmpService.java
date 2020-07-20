package cn.jiyun.service;

import java.util.List;

import cn.jiyun.pojo.City;
import cn.jiyun.pojo.Emp;

public interface EmpService {

	List<Emp> findAll(Emp emp);

	List<City> findSheng();

	List<City> findShi(Integer cid);

	String findCityByID(Integer cid);

	void add(Emp emp);

	void delete(Integer eid);

	Emp findByID(Integer eid);

	void update(Emp emp);

	void deleteAll(Integer[] ids);

}
