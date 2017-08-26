<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>上传</title>
<style type="text/css">
			#preview {
				margin-left: 40px;
				width: 200px;
				height: 200px;
				border: 1px solid #E1E1E1;
			}
			
			#imghead {
				filter: progid: DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);
			}
			
			header {
				width: 100%;
				border: 1px solid #e1e1e1;
				margin: 0 auto;
				background: #fff;
			}
			
			
			h2 {
				margin: 60px;
				color: #7c7c7c;
			}
			
			.left {
				float: left;
				width: 50%;
				border-right: 10px dashed #ccc;
			}
			
			.right {
				float: right;
				width: 30%;
				text-align: center;
				border-radius: 14px;
			}
			
			.right a {
				margin-top: 20px;
				margin-right: 90px;
				float: right;
				width: 70%;
				font-size: 18px;
				letter-spacing: 3px;
				padding: 2px;
			}
			
			#but {
				float: right;
				margin-top: 10px;
				margin-right: 25%;
				background: #7C7C7C;
				color: #fff;
				width: 250px;
				height: 50px;
				line-height: 50px;
				text-align: center;
				border-radius: 14px;
			}
			
			p {
				height: 340px;
				width: 500px;
				color: #7C7C7C;
			}
			
			.hover {
				background: #134364;
				color: #fff;
			}
			
			.hover:hover {
				background: #000;
			}
		</style>
</head>
<body>
<img src="images/banner.png" style="width: 100%;height: 130px;" />

 用户：${sessionScope.SharedWarehouseUserName}<br/>
 私有空间：( ${sessionScope.usedspace}M / ${sessionScope.sumspace}M)<br/>
 <div class="left">
 请操作：         ${sessionScope.statustemp}<br/>
 <% 
 session.removeAttribute("statustemp"); 
 %>
 <form action="upload.do"  method="post" enctype="multipart/form-data">
 <h2>请选择上传内容：</h2>
    <input type="file" name="f">
    <input type="radio" value="ok" name="personalupload">私有
 <h2>必选属性:(个人资源分类暂缺失)</h2> 		
<h3>电影类</h3>			
<input type="radio" value="movie_kongbu" name="style">恐怖
<input type="radio" value="movie_aiqing" name="style">爱情
<input type="radio" value="movie_kehuan" name="style">科幻
<input type="radio" value="movie_xuanyi" name="style">悬疑
<h3>软件类</h3>
<input type="radio" value="software_zhuanye" name="style">专业类
<input type="radio" value="software_shiyong" name="style">实用类
<input type="radio" value="software_xinqi" name="style">新奇
<h3>文档类</h3>
<input type="radio" value="document_zhuanyelunwen" name="style">专业论文
<input type="radio" value="document_chengxudaima" name="style">程序代码
<input type="radio" value="document_matlab" name="style">matlab资料
<h3>其他</h3>
<input type="radio" value="other_chaolianjie" name="style">超链接
<input type="radio" value="other_other" name="style">其他
    <br/>
    <br/>
    <input type="submit" value="上传">
    </form>
    <br/>
    
   <a href="index.do">返回首页</a>
   
   </div>
   
    <div class="right">
    <h2>描述内容：(待完成)</h2>
   </div>
</body>
</html>