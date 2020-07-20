<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../webjars/jquery/1.11.3/jquery.js"></script>
</head>
<body>
<form action="update.action" method="post" enctype="multipart/form-data">
<input type="hidden" name="eid" value="${e.eid }">
姓名：<input name="ename" value="${e.ename }"><br>
性别：
<input type="radio" name="sex" value="男"  ${e.sex eq '男' ? 'checked=checked':'' }>男
<input type="radio" name="sex" value="女"  ${e.sex eq '女' ? 'checked=checked':'' }>女<br>
爱好：
<input type="checkbox" name="hobby" value="篮球" <c:if test="${e.hobby.contains('篮球')}">checked="checked"</c:if>>篮球
<input type="checkbox" name="hobby" value="足球" <c:if test="${e.hobby.contains('足球')}">checked="checked"</c:if>>足球
<input type="checkbox" name="hobby" value="排球" <c:if test="${e.hobby.contains('排球')}">checked="checked"</c:if>>排球<br>
生日：
<input type="date" name="birthday"  value="<fmt:formatDate pattern="yyyy-MM-dd" value="${e.birthday }"></fmt:formatDate>"><br>
头像：
<img src="${e.img }" width="100px" height="100px"><br>
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
	
	var oldDid = "${e.did}";
	
	var dept = eval(data);
	for(var i = 0 ; i <dept.length;i++){
		var did = dept[i].did;
		var dname = dept[i].dname;
		if(oldDid==did){
			$("#dept").append("<option selected value='"+did+"'>"+dname+"</option>");
		}else{
			$("#dept").append("<option value='"+did+"'>"+dname+"</option>");
		}
	}
});

$.get("findSheng.action",function(data){
	var addr = "${e.addr}";
	// 北京-昌平-马池口
	// addr.split("-");
	// addr.indexOf("北京")!=-1;
		
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