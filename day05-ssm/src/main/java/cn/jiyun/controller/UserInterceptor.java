package cn.jiyun.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// 什么时候拦截，什么时候放行
		// 所有的登陆啊，注册啊，都放行
		String requestURI = request.getRequestURI();
		if(requestURI.contains("user")){
			return true;
		}
		// 登陆成功，也不进行拦截
		Object user = request.getSession().getAttribute("user");
		if(user!=null){
			return true;
		}
		
		
		response.sendRedirect(request.getContextPath()+"/user/toLogin.action");
		return false;
	}

}
