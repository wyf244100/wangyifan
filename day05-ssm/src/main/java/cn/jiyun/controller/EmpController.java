package cn.jiyun.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.jiyun.pojo.City;
import cn.jiyun.pojo.Dept;
import cn.jiyun.pojo.Emp;
import cn.jiyun.service.DeptService;
import cn.jiyun.service.EmpService;

@Controller
@RequestMapping("emp")
public class EmpController {

	@Autowired
	EmpService empService;
	
	@Autowired
	DeptService deptService;
	
	@RequestMapping("findAll")
	public String findAll(Model model,Emp emp,@RequestParam(defaultValue="1",required=true)Integer pageNum){
		// 分页
		PageHelper.startPage(pageNum, 2);
		
		List<Emp> list = empService.findAll(emp);
		
		
		PageInfo<Emp> page = new PageInfo<>(list);
		model.addAttribute("page", page);
		return "show";
	}
	
	@RequestMapping("toadd")
	public String toadd(){
		return "add";
	}
	
	@RequestMapping("findDept")
	@ResponseBody// 把java中的数据，转变成json数据
	public List<Dept> findDept(){
		return deptService.findAll();
	}
	
	@RequestMapping("findSheng")
	@ResponseBody
	public List<City> findSheng(){
		return empService.findSheng();
	}
	@RequestMapping("findShi")
	@ResponseBody
	public List<City> findShi(Integer cid){
		return empService.findShi(cid);
	}
	
	@RequestMapping("add")
	public String add(Emp emp,MultipartFile file,Integer sheng,Integer shi,Integer qu) throws Exception{
		
		String filename = file.getOriginalFilename();
		file.transferTo(new File("f://upload/"+filename));
		
		emp.setImg("/pic/"+filename);
		
		String name1 = empService.findCityByID(sheng);
		String name2 = empService.findCityByID(shi);
		String name3 = empService.findCityByID(qu);
		
		emp.setAddr(name1+"-"+name2+"-"+name3);
		
		// 将emp对象，放入数据库
		empService.add(emp);
		
		return "redirect:/emp/findAll.action";
	}
	
	@RequestMapping("delete")
	public String delete(Integer eid){
		
		empService.delete(eid);
		
		return "redirect:/emp/findAll.action";
	}
	
	@RequestMapping("preUpdate")
	public String preUpdate(Integer eid,Model model){
		
		Emp emp = empService.findByID(eid);
		
		model.addAttribute("e", emp);
		
		return "update";
	}
	
	@RequestMapping("update")
	public String update(Emp emp,MultipartFile file,Integer sheng,Integer shi,Integer qu) throws Exception{
		
		String filename = file.getOriginalFilename();
		file.transferTo(new File("f://upload/"+filename));
		
		emp.setImg("/pic/"+filename);
		
		String name1 = empService.findCityByID(sheng);
		String name2 = empService.findCityByID(shi);
		String name3 = empService.findCityByID(qu);
		
		emp.setAddr(name1+"-"+name2+"-"+name3);
		
		// 将emp对象，放入数据库
		empService.update(emp);
		
		return "redirect:/emp/findAll.action";
	}
	
	@RequestMapping("deleteAll")
	public String deleteAll(Integer[] ids){
		empService.deleteAll(ids);
		return "redirect:/emp/findAll.action";
	}
	
}
