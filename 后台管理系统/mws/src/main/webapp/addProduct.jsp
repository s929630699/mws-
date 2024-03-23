<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加产品</title>
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
				<p>添加产品</p>
			</div>
		</div>
<form action="${pageContext.request.contextPath }/product/insert.action" method="Post">
<div class="layuimini-form">
    <div class="layui-form-item">
        <label class="layui-form-label required">产品名称：</label>
        <div class="layui-input-block">
            <input type="text" name="name" lay-verify="required" lay-reqtext="产品名称不能为空" placeholder="请输入产品名称" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">价格：</label>
        <div class="layui-input-block">
            <input type="text" name="price" lay-verify="required" lay-reqtext="价格不能为空" placeholder="请输入价格" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">产品类别：</label>
        <div class="layui-input-block">
            <select name="categoryId" class="layui-input">
            	<option value="1">面包</option>
            	<option value="2">蛋糕</option>
            	<option value="3">甜点</option>
            	<option value="4">饮料</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">库存：</label>
        <div class="layui-input-block">
            <input type="text" name="pnum" lay-verify="required" lay-reqtext="库存不能为空" placeholder="请输入库存" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">添加图片：</label>
        <div class="layui-input-block">
            <input type="text" name="imgurl" lay-verify="required"  placeholder="请输入图片" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">简介：</label>
        <div class="layui-input-block">
            <input type="text" name="description" lay-verify="required" placeholder="请输入简介" value="" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">保质期：</label>
        <div class="layui-input-block">
            <input type="text" name="shelfLife" lay-reqtext="保质期不能为空" placeholder="请输入保质期" value="" class="layui-input">
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