<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>密码找回</title>
</head>
<body>
 ${requestScope.msg}
请输入注册邮箱或用户名（至少一项）：
<form action="sendemail.do" >
用户名：<input type="text" name="username"><br/>
    邮箱：<input type="text" name="email">（必填）<br/>
<input type="submit" value="提交"><br/>
</form>




</body>
</html>