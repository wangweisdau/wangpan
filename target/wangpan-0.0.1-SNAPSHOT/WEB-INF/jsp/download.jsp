<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>下载内容页</title>
</head>
<body>

用户：${sessionScope.SharedWarehouseUserName}，请操作：

<br/>
 ${sessionScope.statustemp}
 <% 
 session.removeAttribute("statustemp"); 
 %>
<br/>

<table border="1" align="center" width="80%">
		<tr>
			<td>文件名称</td>
			<td>文件上传时间</td>
			<td>大小</td>
			<td>共享给大家（已共享？）</td>
			<td>下载链接</td>
			<td>删除</td>
			
			
		</tr>
		
		<c:forEach items="${requestScope.user_table}" var="r">
			<tr>
			<td><a href="#">${r.realname }</a></td>
			<td>${r.time }</td>
			<td>${r.filesize}M</td>
			<td>
			<a href='sharefilerequest.do?id=${r.id}&filename=${r.realname}'>共享  ( ${r.shareflag})</a>
			</td>
			<td>
				 <a href='downloadfile.do?id=${r.id}&type=personal'>下载</a>

			</td>
			<td>
				 <a href='delete.do?id=${r.id}&type=personal'>删除</a>

			</td>
			
			
		</tr>
		</c:forEach>
		
	</table>
	
	<a href="index.do">返回首页   </a><br/> 
	 <a href="downloadfiletype.do?type=enjoy&style=enjoyall">返回共享资源</a>
</body>
</html>