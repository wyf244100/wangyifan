package com.xiaoshu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.aspectj.weaver.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.entity.Attachment;
import com.xiaoshu.entity.Grade;
import com.xiaoshu.entity.Log;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.Role;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.User;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.service.StuService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.TimeUtil;
import com.xiaoshu.util.WriterUtil;

@Controller
@RequestMapping("stu")
public class StuController {

	@Autowired
	private StuService ss;
	@Autowired
	private OperationService operationService;
	
	@RequestMapping("stuIndex")
	public String index(HttpServletRequest request,Integer menuid) throws Exception{
		List<Grade> glist = ss.findGrade();
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		request.setAttribute("operationList", operationList);
		request.setAttribute("roleList", glist);
		return "stu";
	}
	
	@RequestMapping(value="stuList",method=RequestMethod.POST)
	public void userList(Student student,HttpServletResponse response,String offset,String limit) throws Exception{
		try {
			
			
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			PageInfo<Student> userList= ss.findAll(student,pageNum,pageSize);
			
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("total",userList.getTotal() );
			jsonObj.put("rows", userList.getList());
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("用户展示错误",e);
			throw e;
			
		}
	}
	@RequestMapping("deleteStu")
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
	//		logger.error("删除用户信息错误",e);
			result.put("errorMsg", "对不起，删除失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	// 新增或修改
		@RequestMapping("reserveStu")
		public void reserveUser(HttpServletRequest request,Student stu,HttpServletResponse response){
			Integer id = stu.getId();
			JSONObject result=new JSONObject();
			try {
				if (id != null) {   // userId不为空 说明是修改
					if(ss.existUserWithUserName(stu.getName())==null){  // 没有重复可以添加
					ss.updateStu(stu);
					result.put("success", true);
				} else {
					result.put("success", true);
					result.put("errorMsg", "该用户名被使用");
				}
					
				}else {   // 添加
					if(ss.existUserWithUserName(stu.getName())==null){  // 没有重复可以添加
						ss.addStu(stu);
						result.put("success", true);
					} else {
						result.put("success", true);
						result.put("errorMsg", "该用户名被使用");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			//	logger.error("保存用户信息错误",e);
				result.put("success", true);
				result.put("errorMsg", "对不起，操作失败");
			}
			WriterUtil.write(response, result.toString());
		}
		@RequestMapping("outStu")
		public void backup(HttpServletRequest request,HttpServletResponse response){
			JSONObject result = new JSONObject();
			try {
				String time = TimeUtil.formatTime(new Date(), "yyyyMMddHHmmss");
			    String excelName = "手动备份"+time;
			
				List<Student> list = ss.findStu();
				String[] handers = {"学生id","学生名","性别","生日","爱好","职位"};
				// 1导入硬盘
				ExportExcelToDisk(request,handers,list, excelName);
				
				
				result.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				result.put("", "对不起，备份失败");
			}
			WriterUtil.write(response, result.toString());
		}
		
		
		
		// 导出到硬盘
		@SuppressWarnings("resource")
		private void ExportExcelToDisk(HttpServletRequest request,
				String[] handers, List<Student> list, String excleName) throws Exception {
			
			try {
				HSSFWorkbook wb = new HSSFWorkbook();//创建工作簿
				HSSFSheet sheet = wb.createSheet("操作记录备份");//第一个sheet
				HSSFRow rowFirst = sheet.createRow(0);//第一个sheet第一行为标题
				rowFirst.setHeight((short) 500);
				for (int i = 0; i < handers.length; i++) {
					sheet.setColumnWidth((short) i, (short) 4000);// 设置列宽
				}
				//写标题了
				for (int i = 0; i < handers.length; i++) {
				    //获取第一行的每一个单元格
				    HSSFCell cell = rowFirst.createCell(i);
				    //往单元格里面写入值
				    cell.setCellValue(handers[i]);
				}
				for (int i = 0;i < list.size(); i++) {
				    //获取list里面存在是数据集对象
				    Student stu = list.get(i);
				    //创建数据行
				    HSSFRow row = sheet.createRow(i+1);
				    //设置对应单元格的值
				    row.setHeight((short)400);   // 设置每行的高度
				    //"学生id","学生名","性别","生日","爱好","职位"
				    row.createCell(0).setCellValue(i+1);
				    row.createCell(1).setCellValue(stu.getName());
				    row.createCell(2).setCellValue(stu.getSex());
				    
				    row.createCell(3).setCellValue(TimeUtil.formatTime(stu.getBirthday(), "yyyy-MM-dd"));
				    row.createCell(4).setCellValue(stu.getHobby());
				    row.createCell(5).setCellValue(stu.getGrade().getGname());

				}
				//写出文件（path为文件路径含文件名）
					OutputStream os;
					File file = new File("d://"+excleName+".xls");
					
					if (!file.exists()){//若此目录不存在，则创建之  
						file.createNewFile();  
					//	logger.debug("创建文件夹路径为："+ file.getPath());  
		            } 
					os = new FileOutputStream(file);
					wb.write(os);
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
		}

		//导入
				@RequestMapping("inStu")
				public void inStu(HttpServletRequest request,MultipartFile file,HttpServletResponse response){
					JSONObject result = new JSONObject();

					try {
						
							HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
							HSSFSheet sheet = wb.getSheetAt(0);
							int lastRowNum = sheet.getLastRowNum();
							System.out.println(lastRowNum);
							ArrayList<Student>list = new ArrayList<>();
							for (int i = 1; i <= lastRowNum; i++) {
								Student stu = new Student();
								HSSFRow row = sheet.getRow(i);
								stu.setName(row.getCell(1).getStringCellValue()); 
								stu.setSex(row.getCell(2).getStringCellValue()); 
								
								stu.setBirthday(TimeUtil.ParseTime(row.getCell(3).getStringCellValue(), "yyyy-MM-dd")); 
								stu.setHobby(row.getCell(4).getStringCellValue()); 
								String gname=row.getCell(5).getStringCellValue();
								Integer gid= ss.selectGid(gname);
								stu.setGid(gid); 
								System.out.println(stu.toString()+"sssss");
								ss.addStu(stu);
							}
							result.put("success", true);
						}
					 catch (Exception e) {
						e.printStackTrace();
					//	logger.error("保存用户信息错误",e);
						result.put("success", true);
						result.put("errorMsg", "对不起，操作失败");
					}
					WriterUtil.write(response, result.toString());
				}

}
