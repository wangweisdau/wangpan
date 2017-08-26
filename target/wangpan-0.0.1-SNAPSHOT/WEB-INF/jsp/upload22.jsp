<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>选择上传内容</title>
</head>
<body>

 用户：${sessionScope.SharedWarehouseUserName}<br/>
 私有空间：( ${sessionScope.usedspace}M / ${sessionScope.sumspace}M)<br/>
 请操作：${sessionScope.statustemp}<br/>
 <% 
 session.removeAttribute("statustemp"); 
 %>
请选择上传内容：
    <form action="upload.do"  method="post" enctype="multipart/form-data">
    <input type="file" name="f">
    <input type="submit" value="上传">
    <input type="checkbox" value="ok" name="personalupload">私有<br/>
    </form>
    <br/>
    

    
   <a href="index.do">返回首页</a>
</body>
</html>