<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎管理员</title>
</head>
<body>

<h1>仓库管理</h1> 
管理员：${sessionScope.SharedWarehouseUserName}，请操作： 
${sessionScope.statustemp}
 <% 
 session.removeAttribute("statustemp"); 
 %>

<h3>文件管理：</h3> 

<table border="1" align="center" width="70%">
		<tr>
			<td>文件名称</td>
			<td>文件上传时间</td>
			<td>文件大小</td>
			<td>操作按钮</td>
		</tr>
		
		<c:forEach items="${requestScope.user_data}" var="r">
			<tr>
			<td>${r.realname }</td>
			<td>${r.time }</td>
			<td>${r.filesize}M</td>
			<td>
				 <a href='delete.do?id=${r.id}&type=enjoy'>删除</a>

			</td>
		</tr>
		</c:forEach>
		
	</table>
	<br/>
	<br/>

	<h3>管理员推荐：</h3> 
	
	<form action="addadmin.do">
	我推荐<input type="text" name="newadmin" ></input>升级成为管理员！
	<input type="submit" value="推荐他/她">
	</form>
	
	<br/><br/>
	<a href="index.do">返回首页</a>
	
	






</body>
</html>