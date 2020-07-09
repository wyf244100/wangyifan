<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/vue.js"> </script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/axios-0.18.0.js"> </script>
</head>
<body>
<table id="tid" border="1">
	<tr>
		<td>编号</td>
		<td>姓名</td>
		<td>性别</td>
		<td>照片</td>
		<td>生日</td>
		<td>班级</td>
	</tr>
	<tr v-for="stu in slist">
		<td v-text="stu.id"></td>
		<td v-text="stu.name"></td>
		<td v-text="stu.sex"></td>
		<td>照片</td>
		<td v-text="format(stu.sr)"></td>
		<td v-text="stu.clazz.cname">班级</td>
	</tr>
</table>
<script type="text/javascript">
	var table= new Vue({
		el:"#tid",
		data:{
			slist:[]
		},
		created(){
			axios.post("${pageContext.request.contextPath}/stu/toshow.action").then(function(res){
				table.slist = res .data;		
			})
		},
		methods:{
			format(datetime){
				var year=new Date(datetime).getFullYear();
				var month=new Date(datetime).getMonth()+1;
				var date=new Date(datetime).getDate();
				return year+"-"+month+"-"+date
			}
		}
		
	})
</script>
</body>
</html>