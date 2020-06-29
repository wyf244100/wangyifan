package com.xiaoshu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.grade;
import com.xiaoshu.entity.student;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.service.RoleService;
import com.xiaoshu.service.StudentService;
import com.xiaoshu.service.UserService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.TimeUtil;
import com.xiaoshu.util.WriterUtil;

@Controller
@RequestMapping("stu")
public class StudentController extends LogController{
	static Logger logger = Logger.getLogger(StudentController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentService ss;
	
	@Autowired
	private RoleService roleService ;
	
	@Autowired
	private OperationService operationService;
	
	
	@RequestMapping("stuIndex")
	public String index(HttpServletRequest request,Integer menuid) throws Exception{
		List<grade> glist = ss.findgrade();
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		request.setAttribute("operationList", operationList);
		request.setAttribute("glist", glist);
		return "student";
	}
	
	
	@RequestMapping(value="stuList",method=RequestMethod.POST)
	public void userList(student student,HttpServletRequest request,HttpServletResponse response,String offset,String limit) throws Exception{
		try {
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			PageInfo<student> userList= ss.findStudent(student,pageNum,pageSize);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("total",userList.getTotal() );
			jsonObj.put("rows", userList.getList());
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户展示错误",e);
			throw e;
		}
	}
	
	
	// 新增或修改
	@RequestMapping("reserveStu")
	public void reserveUser(HttpServletRequest request,student student,HttpServletResponse response) throws ParseException{
		Integer Id = student.getId();
		JSONObject result=new JSONObject();
		String hobby=student.getHobby();
		if(hobby==null){
			student.setHobby("");
		}
		
		String time = TimeUtil.formatTime(new Date(), "yyyy-MM-dd");
		Date date = TimeUtil.ParseTime(time, "yyyy-MM-dd");
		student.setBirthday(date);
		try {
			if (Id != null) {   // userId不为空 说明是修改
				student stuName = ss.existStuWithName(student.getName());
				if(stuName != null && stuName.getId().compareTo(Id)==0){
					student.setId(Id);
					ss.updateStu(student);
					result.put("success", true);
				}else{
					result.put("success", true);
					result.put("errorMsg", "该用户名被使用");
				}
				
			}else {   // 添加
				if(ss.existStuWithName(student.getName())==null){  // 没有重复可以添加
					ss.addStu(student);
					result.put("success", true);
				} else {
					result.put("success", true);
					result.put("errorMsg", "该用户名被使用");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存用户信息错误",e);
			result.put("success", true);
			result.put("errorMsg", "对不起，操作失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	@RequestMapping("outStu")
	public void outStu(HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			//创建工作workbook
			HSSFWorkbook wb= new HSSFWorkbook();
			//创建工作表 sheet
			HSSFSheet sheet = wb.createSheet();
			//创建标题行 row
			HSSFRow row0 = sheet.createRow(0);
			//创建表头
			String[] title={"用户编号","用户姓名","用户手机号","最后一次修改时间","所属部门"};
			
			for (int i = 0; i < title.length; i++) {
				//将表头写入表中
				row0.createCell(i).setCellValue(title[i]);
			}	
			List<student> slist=ss.findAll();
			for (int i = 0; i < slist.size(); i++) {
				student stu = slist.get(i);
				HSSFRow row1 = sheet.createRow(i+1);
				row1.createCell(0).setCellValue(stu.getId());
				row1.createCell(1).setCellValue(stu.getName());
				row1.createCell(2).setCellValue(stu.getSex());
				
				row1.createCell(3).setCellValue(TimeUtil.formatTime(stu.getBirthday(), "yyyy-MM-dd"));
				//row1.createCell(4).setCellValue(stu.getHobby());
				row1.createCell(4).setCellValue(stu.getGrade().getGname());
				 
			}
			
			FileOutputStream stream = new FileOutputStream(new File("D://h1908b.xls"));
			wb.write(stream);
			wb.close();
			stream.close();
			
			result.put("success", true);	
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			result.put("errorMsg", "对不起，导出失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	@RequestMapping("instu")
	public void reserveUser(HttpServletResponse response,MultipartFile file){
		JSONObject result=new JSONObject();
		try {
				HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
				 
				HSSFSheet sheet = wb.getSheetAt(0);
				int lastRowNum = sheet.getLastRowNum();
				for (int i = 1; i <= lastRowNum; i++) {
					HSSFRow row = sheet.getRow(i);
					student stu = new student();
					String name = row.getCell(1).getStringCellValue();
					stu.setName(name);
					String sex = row.getCell(2).getStringCellValue();
					stu.setSex(sex);
					/*String hobby = row.getCell(4).getStringCellValue();
					stu.setHobby(hobby);*/
					String birthday = row.getCell(3).getStringCellValue();
					stu.setBirthday(TimeUtil.ParseTime(birthday, "yyyy-MM-dd"));
					String  grade = row.getCell(4).getStringCellValue();
					int gid = ss.findId(grade);
					stu.setGid(gid);
					ss.addStu(stu);
				}
				wb.close();
			
				result.put("success", true);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", true);
			result.put("errorMsg", "对不起，操作失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	
	
	@RequestMapping("deleteUser")
	public void delUser(HttpServletRequest request,HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			String[] ids=request.getParameter("ids").split(",");
			for (String id : ids) {
				ss.deleteStu(Integer.parseInt(id));
			}
			result.put("success", true);
			result.put("delNums", ids.length);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除用户信息错误",e);
			result.put("errorMsg", "对不起，删除失败");
		}
		WriterUtil.write(response, result.toString());
	}
}
