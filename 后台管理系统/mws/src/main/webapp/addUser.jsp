<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加会员</title>
<link rel="stylesheet" href="lib/layui-v2.5.5/css/layui.css" media="all">
<link rel="stylesheet" href="css/public.css" media="all">
</head>
<style>
.text{
	width:300px;
	height:70px;
	position:relative;
	left:50%;
	margin-left:-150px;
	background-image:-webkit-linear-gradient(left,blue,#66ffff 10%,#cc00ff 20%,#CC00CC 30%,#CCCCFF 40%,#00FFFF 50%,#CCCCFF 60%,#CC00CC 70%,#CC00FF 80%,#66FFFF 90%,blue 100%);
	-webkit-text-fill-color:transparent;
	-webkit-background-clip:text;
	-webkit-background-size:200% 100%;
	-webkit-animation:masked-animation 4s linear infinite;
	font-size:35px;
}
@keyframes masked-animation {
	0% {
		background-position:0 0;
	}
	100% {
		background-position:-100% 0;
	}
}
</style>
<body>
<div>
			<div class="text">
				<p>添加会员</p>
			</div>
		</div>
<form action="${pageContext.request.contextPath }/user/insert.action" method="Post">

<div class="layuimini-form">
    <div class="layui-form-item">
        <label class="layui-form-label required">用户名：</label>
        <div class="layui-input-block">
            <input type="text" name="username" lay-verify="required" lay-reqtext="用户名不能为空" placeholder="请输入用户名" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">密码：</label>
        <div class="layui-input-block">
            <input type="text" name="password" lay-verify="required" lay-reqtext="密码不能为空" placeholder="请输入密码" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label ">性别：</label>
        <div class="layui-input-block" style="top:11px">
            <input type="radio" name="gender" value="男">男
			<input type="radio" name="gender" value="女">女
		</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">联系电话：</label>
        <div class="layui-input-block">
            <input type="text" name="telephone" lay-verify="required" lay-reqtext="联系电话不能为空" placeholder="请输入联系电话" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">介绍：</label>
        <div class="layui-input-block">
            <input type="text" name="introduce" lay-verify="required"  placeholder="请输入介绍" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">身份：</label>
        <div class="layui-input-block" style="top:11px">
            <input type="radio" name="role" value="管理员">管理员
			<input type="radio" name="role" value="普通会员">普通会员
        </div>
    </div>
    

    <div class="layui-form-item">
        <div class="layui-input-block">
            <a href="javascript:history.back(-1)"><button type="button" class="layui-btn layui-btn-primary">上一步</button></a>
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
        </div>
    </div>
</div>
</form>  
<script src="lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
</body>
</html>