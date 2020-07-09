package cn.jiyun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jiyun.pojo.Stu;
import cn.jiyun.service.StuSerivce;

@Controller
@RequestMapping("stu")
public class stuController {
		@Autowired
		private StuSerivce ss;
		@RequestMapping("findall")
		public String findALL(){
			return "show2";
		}
	@RequestMapping("toshow")
	@ResponseBody
	public List<Stu> toshow(){
		List<Stu> slist=ss.findAll();
		System.out.println(slist);
		return slist;
	}
}
