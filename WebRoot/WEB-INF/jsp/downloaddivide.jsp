<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %><!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <!DOCTYPE html> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>共享资源下载</title>
<style  type="text/css">
body{
	margin:0;
	font-family:'微软雅黑','Times New Roman', Times, serif;
	}
.navi_head{
	height:50px;
	background-color:#459df5;
}
.navi_body{
	overflow:hidden;
	height:50px;
	background:rgba(36,97,158,0.9);
	transition:height ease 0.5s;
}
.navi_body:hover{
	height:300px;
}

.navi_head>div>span{
	width:150px;
	text-align:center;
	height:300px;
	display:inline-block;
	font-weight:bold;
	color:#FFF;
	font-size:14px;
	vertical-align:top;
}




.navi_head>div>span>p a{
	color:#FFF;
	text-decoration:none;
}
.navi_head>div>span>p a:hover{
	color:#FFF;
	text-decoration:underline;
}

.navi_title{
	font-size:16px;
	line-height:50px;
	margin-top:0;
}

.navi_head>div>span:hover{
	background:rgba(100,100,100,0.2);
}
</style>
</head>
<body>
	<div>
		<div class="navi_body">
			<div class="navi_head">
				<div style="width:80%; margin-left:auto; margin-right:auto;">
					<span>
						<p class="navi_title"> <a href="hello.do">首页</a></p>
					</span>
					<span>
						<p class="navi_title">电影</p>
						<p><a href='downloadfiletype.do?style=movie_kongbu&type=enjoy'>恐怖</a></p>
						<p><a href='downloadfiletype.do?style=movie_kehuan&type=enjoy'>科幻</a></p>
						<p><a href='downloadfiletype.do?style=movie_aiqing&type=enjoy'>爱情</a></p>
						<p><a href='downloadfiletype.do?style=movie_xuanyi&type=enjoy'>悬疑</a></p>
					</span>
					<span>
						<p class="navi_title">软件</p>
						<p><a href='downloadfiletype.do?style=software_zhuanye&type=enjoy'>专业类</a></p>
						<p><a href='downloadfiletype.do?style=software_shiyong&type=enjoy'>实用类</a></p>
						<p><a href='downloadfiletype.do?style=software_xinqi&type=enjoy'>新奇类</a></p>
					</span>
					<span>
						<p class="navi_title">文档</p>
						<p><a href='downloadfiletype.do?style=document_zhuanyelunwen&type=enjoy'>专业论文</a></p>
						<p><a href='downloadfiletype.do?style=document_chengxudaima&type=enjoy'>程序代码</a></p>
						<p><a href='downloadfiletype.do?style=document_matlab&type=enjoy'>matlab资料</a></p>
					</span>
					<span>
						<p class="navi_title">其他</p>
						<p><a href='downloadfiletype.do?style=other_chaolianjie&type=enjoy'>超链接</a></p>
						<p><a href='downloadfiletype.do?style=other_other&type=enjoy'>其他</a></p>
					</span>
					
					<span>
						<p><a href="downloadfiletype.do?type=personal&style=personalall">个人空间</a></p>
					</span>
					
					
				</div>
			</div>
		</div>
	</div>
	
	<br/>
	${requestScope.msg}
	<br/>
	<table border="1" align="center" width="80%">
		<tr>
			<td>文件名称</td>
			<td>文件上传时间</td>
			<td>大小</td>
			<td>下载链接</td>
			
		</tr>
		
		<c:forEach items="${requestScope.user_table}" var="r">
			<tr>
			<td><a href="#">${r.realname }</a></td>
			<td>${r.time }</td>
			<td>${r.filesize}M</td>
			<td>
				 <a href='downloadfile.do?id=${r.id}&type=enjoy'>下载</a>

			</td>
			
			
			
		</tr>
		</c:forEach>
		
	</table>
	
	
	
	
	
	


</body>
</html>