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
<form action="regist.action" method="post">
账号：<input name="username" id="username"><span id="s1"></span><br>
密码：<input name="password" id="password"><span id="s2"></span><br>
重复密码：<input name="repassword" id="repassword"><span id="s3"></span><br>
<input type="submit" value="注册">

</form>
<script type="text/javascript">
// 1.账号的长度  2.密码的长度  3.两次密码是否一致  4.ajax验证，账号是否存在！
$("#username").blur(function(){
	var un = $(this).val();
	if(un.length>0){
		$.post("findUsername.action",{username:un},function(data){
			if(data==0){
				$("#s1").text("√");
			}else{
				$("#s1").text("用户名已存在！");
			}
		});
	}else{
		$("#s1").text("账号长度不足！");
	}
});
$("#password").blur(function(){
	var pw = $(this).val();
	if(pw.length>0){
		$("#s2").text("√");
	}else{
		$("#s2").text("密码的长度不足！");
	}
});

$("#repassword").blur(function(){
	var repw = $(this).val();
	var pw = $("#password").val();
	if(pw==repw){
		$("#s3").text("√");
	}else{
		$("#s3").text("两次密码不一致！");
	}
});

$("form").submit(function(){
	var s1 = $("#s1").text();
	var s2 = $("#s2").text();
	var s3 = $("#s3").text();
	if(s1=="√"&& s2 =="√"&& s3=="√"){
		return true;
	}else{
		alert("信息有误！请重新提交！");
		return false;
	}
});

</script>


</body>
</html>