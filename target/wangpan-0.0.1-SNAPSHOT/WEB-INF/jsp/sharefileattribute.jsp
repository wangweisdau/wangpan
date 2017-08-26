<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人转共享</title>
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
				border-right: 1px dashed #ccc;
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
		<header>
			<img src="images/banner.png" style="width: 100%;height: 130px;" />
			<div class="left">
				
准备共享的文件：${requestScope.filename }
				
<h2>选择属性:（单选）</h2> 	
<form action='sharefile.do?shareid=${requestScope.shareid}' method="post"> 	
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
			<br/>
	<input type="submit" class="hover" value="提交" style="border-radius: 14px;margin-left: 50px;width: 150px;height: 50px;" />		
				
						
	</form>	
		<br/>		
			<br/>
			<br/>	
	<a href="downloadfiletype.do?type=personal&style=personalall">   不分享了，返回个人空间</a>
			</div>
			
			
			
			<div class="right">
				<input type="button" id="but" value="帮助中心" />
				<a style="color: #7C7C7C;">如果您在使用的过程中遇到疑问，或者有任何的意见或者建议，欢迎随时向我们反馈，我们会尽快回复您的问题，并依据您的反馈，不断完善。</a>
			</div>
		</header>

	</body>

	
	

</html>