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
<form action="" method="post" id="fm">
<input type="hidden" name="pageNum" id="pn">
请输入姓名：<input name="ename" value="${emp.ename }">
请输入时间：<input type="date" name="birthday" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${emp.birthday }"></fmt:formatDate>">
请输入部门：<select name="did" id="dept">
<option value="0">---请选择---</option>
</select>
<input type="button" value="搜索" onclick="sch()">
</form>
<a href="toadd.action">添加</a>
<form action="deleteAll.action" method="post">
<table border="1">
<tr>
	<td>
		<input onclick="allfun()" type="button" value="全选"> 
		<input onclick="noallfun()" type="button" value="全不选">
	</td>
	<td>编号</td>
	<td>姓名</td>
	<td>性别</td>
	<td>爱好</td>
	<td>生日</td>
	<td>头像</td>
	<td>部门</td>
	<td>地址</td>
	<td>操作</td>
</tr>
<c:forEach items="${page.list }" var="e">
<tr>
	<td><input type="checkbox" name="ids" value="${e.eid }"></td>
	<td>${e.eid }</td>
	<td>${e.ename }</td>
	<td>${e.sex }</td>
	<td>${e.hobby }</td>
	<td><fmt:formatDate value="${e.birthday }" pattern="yyyy-MM-dd"/></td>
	<td><img src="${e.img }" width="50px" height="50px"></td>
	<td>${e.dept.dname }</td>
	<td>${e.addr }</td>
	<td>
		<a href="delete.action?eid=${e.eid }">删除</a>
		<a href="preUpdate.action?eid=${e.eid }">修改</a>
	
	</td>
</tr>
</c:forEach>
</table>
<input type="submit" value="选中删除">
</form>
<input type="button" value="首页" onclick="goPageNum(${page.firstPage})">
<input type="button" value="上一页" onclick="goPageNum(${page.prePage})">
<c:forEach begin="1" end="${page.lastPage}" var="i">
<input type="button" value="${i }" onclick="goPageNum(${i})">
</c:forEach>

<input type="button" value="下一页" onclick="goPageNum(${page.nextPage})">
<input type="button" value="尾页" onclick="goPageNum(${page.lastPage})">


<script type="text/javascript">
// 用来，让form表单，提交页码
function goPageNum(pageNum){
	document.getElementById("pn").value=pageNum;
	document.getElementById("fm").action="findAll.action";
	document.getElementById("fm").submit();
}
//图片  部门动态获取  省市区的联动
$.get("findDept.action",function(data){
	
	var oldDid = "${emp.did}";
	
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

function sch(){
	document.getElementById("fm").action="findAll.action";
	document.getElementById("fm").submit();
}

function allfun(){
	$("input[type='checkbox']").prop("checked",true);
}
function noallfun(){
	$("input[type='checkbox']").prop("checked",false);
}
</script>
</body>
</html>