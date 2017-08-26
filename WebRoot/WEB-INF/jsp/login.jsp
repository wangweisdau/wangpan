<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录 - 共享仓库 </title>
	<link rel="stylesheet" type="text/css" href="style/register-login.css">
</head>
<body>


<div id="box"></div>
<div class="cent-box">
	<div class="cent-box-header">
		<h1 class="main-title hide">共享仓库</h1>
		<h2 class="sub-title">享受共享快乐 - Thousands Happy</h2>
	</div>


${requestScope.msg}
<form action="login.do" name="f" method="POST">

	<div class="cont-main clearfix">
		<div class="index-tab">
			<div class="index-slide-nav">
				<a href="hello.do" class="active">登录</a>
				<a href="registerrequest.do">注册</a>
				<div class="slide-bar"></div>				
			</div>
		</div>


		<div class="login form">
			<div class="group">
				<div class="group-ipt email">
					<input type="text" name="username" id="email" class="ipt" placeholder="输入用户名" required>
				</div>
				<div class="group-ipt password">
					<input type="password" name="password" id="password" class="ipt" placeholder="登录密码" required>
				</div>
				<!-- <div class="group-ipt verify">
					<input type="text" name="verify" id="verify" class="ipt" placeholder="输入验证码" required>
					<img src="http://zrong.me/home/index/imgcode?id=" class="imgcode">
				</div> -->
			</div>
		</div>
         <div class="button">
			<button type="submit" class="login-btn register-btn" id="button">登录</button>
		</div>
		

		<div class="remember clearfix">
			<label class="remember-me"><span class="icon"><span class="zt"></span></span><input type="checkbox" name="autologin" value="ok"  id="remember-me" class="remember-mecheck" checked>记住我</label>
			<label class="forgot-password">
				<a href="forgetpassword.do">忘记密码？</a>
			</label>
		</div>
		
	</div>
	</form>
	
</div>




<div class="footer">
	<p>共享仓库 - Thousands Happy</p>
	<p>Designed By 隔壁老王<a href="http://www.nbu.edu.cn/">Find me</a> 2017</p>
</div>

<script src='js/particles.js' type="text/javascript"></script>
<script src='js/background.js' type="text/javascript"></script>
<script src='js/jquery.min.js' type="text/javascript"></script>
<script src='js/layer/layer.js' type="text/javascript"></script>
<script src='js/index.js' type="text/javascript"></script>
<script>
	$('.imgcode').hover(function(){
		layer.tips("看不清？点击更换", '.verify', {
        		time: 6000,
        		tips: [2, "#3c3c3c"]
    		})
	},function(){
		layer.closeAll('tips');
	}).click(function(){
		$(this).attr('src','http://zrong.me/home/index/imgcode?id=' + Math.random());
	});
	$("#remember-me").click(function(){
		var n = document.getElementById("remember-me").checked;
		if(n){
			$(".zt").show();
		}else{
			$(".zt").hide();
		}
	});
</script>
</body>
</html>