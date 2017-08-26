<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人与共享下载页</title>
<style>

body {
	text-align: center;
}

.nav li {
			float: left;
			list-style: none;
			margin-left: 10px;
			background-color: blue;
		}
		
.site{
	width:500px;
	height:50px;
	margin:0 auto;
}

.box {
	width: 1200px;
	margin: 100px auto;
	border: 1px solid #ccc;
	margin: 0 auto;
}

.bottom div {
	width: 100%;
	height: 500px;
	background-color: green;
	display: none;
}

.purple {
	background-color: purple;
}

.bottom .show {
	display: block;
}

.show {
	padding-top: 150px;
}
</style>
<script>
	window.onload = function() {
		var btns = document.getElementsByTagName("button");
		var divs = document.getElementById("divs").getElementsByTagName("div");
		for (var i = 0; i < btns.length; i++) {
			btns[i].index = i; // 难点
			btns[i].onclick = function() {
				//让所有的 btn 类名清空
				//alert(this.index);
				for (var j = 0; j < btns.length; j++) {
					btns[j].className = "";
					divs[j].className = "";
				}
				// 当前的那个按钮 的添加 类名
				this.className = "purple";
				//先隐藏下面所有的 div盒子
				//留下中意的那个 跟点击的序号有关系的
				divs[this.index].className = "show";
			}
		}
	}
</script>
</head>
<body>
 
	
		 <div class="top">
			<button>共享资源</button>
			<button>个人资源</button>

		</div> 
		<div class="bottom" id="divs">
			<div class="show">
				<table border="0.5" align="center" width="80%">
					<tr>
						<td>文件名称</td>
						<td>文件上传时间</td>
						<td>大小</td>
						<td>下载链接</td>
					</tr>
					<c:forEach items="${requestScope.user_table}" var="r">
						<tr>
							<td><a href='#'>${r.realname }</a></td>
							<td>${r.time }</td>
							<td>${r.filesize}M</td>
							<td><a href='downloadfile.do?id=${r.id}&downloadtype=enjoy'>下载</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>


			<div>
				<table border="0.5" align="center" width="80%">
					<tr>
						<td>文件名称</td>
						<td>文件上传时间</td>
						<td>大小</td>
						<td>下载链接</td>
					</tr>
					<c:forEach items="${requestScope.user_table_pro}" var="r">
						<tr>
							<td><a href='#'>${r.realname }</a></td>
							<td>${r.time }</td>
							<td>${r.filesize}M</td>
							<td><a href='downloadfile.do?id=${r.id}&downloadtype=personal'>下载</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
			
		
		</div>


</body>
</html>