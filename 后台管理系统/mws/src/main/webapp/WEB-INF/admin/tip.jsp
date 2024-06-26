<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>麦维思烘培坊后台管理系统</title>
<style type="text/css">
*{ margin:0px; padding:0px;}
.error-container{ background:#fff; border:3px solid rgba(120, 206, 238, 0.9);  text-align:center; width:450px; margin:100px auto; font-family:Microsoft Yahei; padding-bottom:30px; border-top-left-radius:5px; border-top-right-radius:5px;  }
.error-container h1{ font-size:16px; padding:12px 0; background:rgba(90, 186, 250, 0.85); color:#fff;} 
.errorcon{ padding:35px 0; text-align:center; color:rgba(121, 106, 238, 0.9); font-size:18px;}
.errorcon i{ display:block; margin:12px auto; font-size:30px; }
.errorcon span{color:rgba(121, 106, 238, 0.9);}
h4{ font-size:14px; color:#666;}
a{color:rgba(121, 106, 238, 0.9);}
</style>
</head>

<body class="no-skin">
<div class="error-container"> 
    <h1> 麦维思烘培坊后台管理系统-操作提示 </h1>   
    <div class="errorcon">      
        <span>${infomation }</span>  
   </div>
    <h4 class="smaller">页面自动 <a id="href" href="${url}">跳转</a> 等待时间： <b id="wait">1</b></h4> 
  
</div>




<script type="text/javascript">
(function(){
var wait = document.getElementById('wait'),href = document.getElementById('href').href;
var interval = setInterval(function(){
	var time = --wait.innerHTML;
	if(time <= 0) {
		location.href = href;
		clearInterval(interval);
	};
}, 1000);
})();
</script>
</body>
</html>