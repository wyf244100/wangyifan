<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../webjars/jquery/1.11.3/jquery.js"></script>
</head>
<body>
<form action="add.action" method="post" enctype="multipart/form-data">
姓名：<input name="ename"><br>
性别：<input type="radio" name="sex" value="男" checked="checked">男
<input type="radio" name="sex" value="女" >女<br>
爱好：
<input type="checkbox" name="hobby" value="篮球">篮球
<input type="checkbox" name="hobby" value="足球">足球
<input type="checkbox" name="hobby" value="排球">排球<br>
生日：
<input type="date" name="birthday"><br>
头像：
<input type="file" name="file"><br>
部门：
<select name="did" id="dept">
<option>---请选择---</option>
</select><br>
地址：
<select name="sheng" id="sheng">
<option>---请选择---</option>
</select>
<select name="shi" id="shi">
<option>---请选择---</option>
</select>
<select name="qu" id="qu">
<option>---请选择---</option>
</select><br>
<input type="submit" value="添加">
</form>


<script type="text/javascript">
// 图片  部门动态获取  省市区的联动
$.get("findDept.action",function(data){
	var dept = eval(data);
	for(var i = 0 ; i <dept.length;i++){
		var did = dept[i].did;
		var dname = dept[i].dname;
		$("#dept").append("<option value='"+did+"'>"+dname+"</option>");
	}
});

$.get("findSheng.action",function(data){
	var sheng = eval(data);
	for(var i = 0 ; i <sheng.length;i++){
		var cid = sheng[i].cid;
		var cname = sheng[i].cname;
		$("#sheng").append("<option value='"+cid+"'>"+cname+"</option>");
	}
});
$("#sheng").change(function(){
	
	$("#shi").text("");
	$("#shi").append("<option>---请选择---</option>");
	$("#qu").text("");
	$("#qu").append("<option>---请选择---</option>");
	
	var shengId = $(this).val();
	$.post("findShi.action",{cid:shengId},function(data){
		var shi = eval(data);
		for(var i = 0 ; i <shi.length;i++){
			var cid = shi[i].cid;
			var cname = shi[i].cname;
			$("#shi").append("<option value='"+cid+"'>"+cname+"</option>");
		}
	});
});
$("#shi").change(function(){
	
	$("#qu").text("");
	$("#qu").append("<option>---请选择---</option>");
	
	var shengId = $(this).val();
	$.post("findShi.action",{cid:shengId},function(data){
		var shi = eval(data);
		for(var i = 0 ; i <shi.length;i++){
			var cid = shi[i].cid;
			var cname = shi[i].cname;
			$("#qu").append("<option value='"+cid+"'>"+cname+"</option>");
		}
	});
});

</script>

</body>
</html>