<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>共享仓库主页</title>

<style type="text/css">
.top {
	width: 100%;
	height: 200px;
}

.top>.title {
	height: 64px;
	float: right;
}

.top>.title>span {
	line-height: 64px;
	float: right;
}

.top>.title>span>a {
	font-size: 15px;
	font-family: "宋体";
	text-decoration: underline;
	color: #333
}

.top>.title>span>.bold {
	font-weight: bold;
}

.top>.title>span>.list {
	font-weight: bold;
	border: 10px;
	height: 60px;
	width: 100px;
	background: #36F;
	color: #fff;
	text-align: center;
}

.list {
	border: 10px;
	height: 60px;
	width: 100px;
	background: #36F;
	color: #fff;
	text-align: center;
}

.pic {
	text-align: center;
	margin-bottom: 60px;
}

.logo {
	height: 160px;
	width: 500px;
	margin: auto auto;
}

.search {
	text-align: center;
}

.input {
	width: 540px;
	height: 36px;
}

.btn {
	border: 0;
	width: 100px;
	height: 40px;
	background: #36F;
	font-size: 15px;
	color: #fff;
}

.foot {
	width: 100%;
	position: absolute;
	bottom: 30px;
}

.foot>.link {
	text-align: center;
	margin-bottom: 10px;
}

.foot>.link>a {
	font-size: 12px;
	font-family: "宋体";
	text-decoration: underline;
}

.copyright {
	text-align: center;
}

p, p>a {
	font-size: 12px;
	font-family: "宋体";
	color: #666;
}
</style>

</head>



<body>


	<div class="top">
		<div class="title">

			<span> ${sessionScope.SharedWarehouseUserName}&nbsp;
			    <a class="bold" href="exit.do">退出</a>&nbsp;
				&nbsp; <a class="bold" href="uploadrequest.do">上传文件</a>&nbsp;
				<a class="bold" href="downloadfiletype.do?type=enjoy&style=enjoyall">下载文件</a>&nbsp;
				<a class="bold" href="setrequest.do">管理员</a>&nbsp; <a class="bold"
				href="http://10.22.63.253/">内网登录</a>&nbsp;<a class="list" href="https://www.duba.com/?f=liebao">更多</a>&nbsp;
			</span>
		</div>
	</div>
	<div class="body">
		<div class="pic">
			<img class="logo" src="images/logo.png" width=400 height=60 />
		</div>
		<div class="search">
			<form action="http://www.baidu.com/baidu">
				<div align="center">
					<label for="search"></label> <input class="input" type=text
						name=word /> <input class="btn" type="submit" value="百度一下"
						name="submit" />
				</div>
			</form>
		</div>

	</div>






	<div class="foot">
		<!-- <div class="link">
			<a href="#">设为主页</a>&nbsp; <a href="#">使用说明</a>&nbsp; <a href="#">关于共享</a>
		</div> -->
		<div class="copyright">

			<p>共享仓库 - Thousands Happy</p>
			<p>
				Designed By 隔壁老王   @2017
			</p>

		</div>
	</div>

	<!-- <div class="foot">
	<p>共享仓库 - Thousands Happy</p>
	<p>Designed By 隔壁老王<a href="http://www.nbu.edu.cn/">Find me</a> 2017</p>
</div> -->

</body>
</html>