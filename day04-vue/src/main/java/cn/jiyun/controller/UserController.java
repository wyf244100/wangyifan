package cn.jiyun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jiyun.pojo.Dept;
import cn.jiyun.pojo.Employee;
import cn.jiyun.service.UserService;

@Controller
@ResponseBody
public class UserController {

	@Autowired
	UserService us;
	
	@RequestMapping("findAll")
	public List<Employee> findAll(){
		return us.findAll();
			
	}
	
	@RequestMapping("findDepts")
	public List<Dept> findDepts(){
		return us.findDepts();
	}
	@RequestMapping("add")
	public String add(@RequestBody Employee employee){
		Integer eid = employee.getEid();
		if(eid==null){
			us.add(employee);
		}else{
			us.update(employee);
		}
		return "0";
	}
	@RequestMapping("del")
	public String del(Integer eid){
		us.del(eid);
		return "";
	}
	
	@RequestMapping("findByid")
	public Employee findByid(Integer eid){
		return us.findByid(eid);
	}
}
