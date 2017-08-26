<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证码- 共享仓库 - Thousands Happy</title>
<style type="text/css">
	
	body{background: url(images/gongxiangcangku_demo.png) #fff top center no-repeat;padding: 0px;margin: 0px;font-size: 12px;font-family: "微软雅黑";}

	.link{text-align: right;line-height: 20px;padding-right: 40px;}
	
	.ui-dialog{ 
		width: 380px;height: 280px ;display: none;
		position: absolute;z-index: 9000;
		top: 0px;left: 0px;
		border: 1px solid #D5D5D5;background: #fff;
	}

	.ui-dialog a{text-decoration: none;}

	.ui-dialog-title{
		height: 48px;line-height: 48px; padding:0px 20px;color: #535353;font-size: 16px;
		border-bottom: 1px solid #efefef;background: #f5f5f5;
		cursor: move;
		user-select:none;
	}
	.ui-dialog-closebutton{
		width: 16px;height: 16px;display: block;
		position: absolute;top: 12px;right: 20px;
		background: url(images/close_def.png) no-repeat;cursor: pointer;

	}
	.ui-dialog-closebutton:hover{background:url(images/close_hov.png);}

	.ui-dialog-content{
		padding: 15px 20px;
	}
	.ui-dialog-pt15{
		padding-top: 15px;
	}
	.ui-dialog-l40{
		height: 40px;line-height: 40px;
		text-align: right;
	}

	.ui-dialog-input{
		width: 99%;height: 40px;
		margin: 0px;padding:0px;
		border: 1px solid #d5d5d5;
		font-size: 16px;color: #c1c1c1;
		text-indent: 25px;
		outline: none;
	}
	.ui-dialog-input-username{
		background: url(images/input_username.png) no-repeat 2px ;
	}


	.ui-dialog-submit{
		width: 100%;height: 50px;background: #3b7ae3;border:none;font-size: 16px;color: #fff;
		outline: none;text-decoration: none;
		display: block;text-align: center;line-height: 50px;
	}
	.ui-dialog-submit:hover{
		background: #3f81b0;
	}

	.ui-mask{ 
		width: 100%;height:100%;background: #000;
		position: absolute;top: 0px;height: 0px;z-index: 8000;
		opacity:0.4; filter: Alpha(opacity=40);
	}
</style>
</head>
<body>

<div class="ui-mask" id="mask" onselectstart="return false"></div>

<div class="ui-dialog" id="dialogMove" onselectstart='return false;'>
	<div class="ui-dialog-title" id="dialogDrag"  onselectstart="return false;" >
		
 ${requestScope.msg}
		<!-- <a class="ui-dialog-closebutton" href="javascript:hideDialog();"></a> -->
<a class="ui-dialog-closebutton" href="hello.do"></a>
	</div>
	
	<div class="ui-dialog-content">
	<form action="checkverifycode.do" >
		<div class="ui-dialog-l40 ui-dialog-pt15">
		
			<input class="ui-dialog-input ui-dialog-input-username" type="text" name="checknum" placeholder="输入验证码（五分钟有效）" required  />
		<input class="ui-dialog-submit" type="submit" value="提交"><br/>
		</div>
		</form>


	</div>
	
</div>


<script type="text/javascript">

	var dialogInstace , onMoveStartId , mousePos = {x:0,y:0};	//	用于记录当前可拖拽的对象
	
	// var zIndex = 9000;

	//	获取元素对象	
	function g(id){return document.getElementById(id);}

	//	自动居中元素（el = Element）
	function autoCenter( el ){
		var bodyW = document.documentElement.clientWidth;
		var bodyH = document.documentElement.clientHeight;

		var elW = el.offsetWidth;
		var elH = el.offsetHeight;

		el.style.left = (bodyW-elW)/2 + 'px';
		el.style.top = (bodyH-elH)/2 + 'px';
		
	}

	//	自动扩展元素到全部显示区域
	function fillToBody( el ){
		el.style.width  = document.documentElement.clientWidth  +'px';
		el.style.height = document.documentElement.clientHeight + 'px';
	}

	//	Dialog实例化的方法
	function Dialog( dragId , moveId ){

		var instace = {} ;

		instace.dragElement  = g(dragId);	//	允许执行 拖拽操作 的元素
		instace.moveElement  = g(moveId);	//	拖拽操作时，移动的元素

		instace.mouseOffsetLeft = 0;			//	拖拽操作时，移动元素的起始 X 点
		instace.mouseOffsetTop = 0;			//	拖拽操作时，移动元素的起始 Y 点

		instace.dragElement.addEventListener('mousedown',function(e){

			var e = e || window.event;

			dialogInstace = instace;
			instace.mouseOffsetLeft = e.pageX - instace.moveElement.offsetLeft ;
			instace.mouseOffsetTop  = e.pageY - instace.moveElement.offsetTop ;
			
			onMoveStartId = setInterval(onMoveStart,10);
			return false;
			// instace.moveElement.style.zIndex = zIndex ++;
		})

		return instace;
	}

	//	在页面中侦听 鼠标弹起事件
	document.onmouseup = function(e){
		dialogInstace = false;
		clearInterval(onMoveStartId);
	}
	document.onmousemove = function( e ){
		var e = window.event || e;
		mousePos.x = e.clientX;
		mousePos.y = e.clientY;
		

		e.stopPropagation && e.stopPropagation();
		e.cancelBubble = true;
		e = this.originalEvent;
        e && ( e.preventDefault ? e.preventDefault() : e.returnValue = false );

        document.body.style.MozUserSelect = 'none';
	}	

	function onMoveStart(){


		var instace = dialogInstace;
	    if (instace) {
	    	
	    	var maxX = document.documentElement.clientWidth -  instace.moveElement.offsetWidth;
	    	var maxY = document.documentElement.clientHeight - instace.moveElement.offsetHeight ;

			instace.moveElement.style.left = Math.min( Math.max( ( mousePos.x - instace.mouseOffsetLeft) , 0 ) , maxX) + "px";
			instace.moveElement.style.top  = Math.min( Math.max( ( mousePos.y - instace.mouseOffsetTop ) , 0 ) , maxY) + "px";

	    }

	}


	


	//	重新调整对话框的位置和遮罩，并且展现
	function showDialog(){
		g('dialogMove').style.display = 'block';
		g('mask').style.display = 'block';
		autoCenter( g('dialogMove') );
		fillToBody( g('mask') );
	}

	//	关闭对话框
	function hideDialog(){
		g('dialogMove').style.display = 'none';
		g('mask').style.display = 'none';
	}

	//	侦听浏览器窗口大小变化
	window.onresize = showDialog;

	Dialog('dialogDrag','dialogMove');
	showDialog();

</script>
</body>
</html>