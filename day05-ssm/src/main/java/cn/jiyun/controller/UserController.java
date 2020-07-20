package cn.jiyun.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jiyun.pojo.User;
import cn.jiyun.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	
	@Autowired
	UserService userService;
	
	
	@RequestMapping("toLogin")
	public String toLogin(){
		return "login";
	}
	
	@RequestMapping("login")
	public String login(User user,Model model,HttpServletRequest request){
		// 得到登陆信息之后，验证登陆信息是否正确！
		List<User> list = userService.findUsernameAndPassword(user);
		
		if(list.size()>0){
			request.getSession().setAttribute("user", list.get(0));
			// 登陆成功！
			return "redirect:/emp/findAll.action";
			
		}else{
			model.addAttribute("msg","账号或密码错误！");
			return "login";
		}
		
	}
	
	@RequestMapping("toRegist")
	public String toRegist(){
		return "regist";
	}
	
	@RequestMapping("findUsername")
	@ResponseBody
	public String findUsername(String username){
		// 得到ajax传过来的值，并去数据库进行查询
		List<User> list = userService.findUsername(username);
//		return list.size()+"";
		
		if(list.size()>0){
			return "1";
		}else{
			return "0";
		}
		
	}
	
	@RequestMapping("regist")
	public String regist(User user){
		
		// 添加，添加完成之后，告诉我，这条信息的id是多少
		
		userService.add(user);
		System.out.println(user.getUid());
		return"redirect:/user/toLogin.action";
	}
	
}
