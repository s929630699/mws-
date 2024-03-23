<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改订单</title>
<style>
body{
	text-align:center;
}
.content{
    width: 1000px;
    margin: auto auto;
}
table{
	border-color:#000000;
    border-spacing:0px;/*设置边框的距离*/
    border-collapse: collapse ;/*合并边框(设置了此属性 边框的距离就没用了 所以用此属性无需再设置边框距离)*/
    margin:0 auto;
    text-align:center;
    width: 100%;
    filter: alpha(opacity=0);
}
table th,td{
	border:1px solid #cad9ea;
	color:#666;
	height:30px;
}
table thead th{
	background-color:#CCE8EB;
	width:100px;
}
table tr:nth-child(odd){
	background:#fff
}
table tr:nth-child(even){
	background:#F5FAFA
}
input{
	outline-style: none ;
	border: 1px solid #ccc;
	border-radius: 3px;
	padding: 7px 0px;
	width: 80%;
	font-size: 24px;
	font-family: "Microsoft soft";
	float:left;
}
input:focus{
	border-color:#66afe9;
	outline:0;
	-webkit-box-shadow:inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
	box-shadow:inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
}
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
label{
	width:150px;
	float:left;
	margin:auto atuo;
	text-align:center;
	padding-top:10px;
}
.message{
	margin-top:50px;
	position:relate;
}
.field{
	position:relative;
	height:70px;
	width:100%;
}
select{
	margin:0;
padding:0;
list-style:none;
	float:left;
	width:150px;
height:47px;
line-height:32px;
padding:0 10px;
border:solid 1px #ccc;
}
select:hover{
background:#666;
color:#fff;
}
button{
	padding: 16px 32px;
	text-align: center;    
	text-decoration: none;    
	display: inline-block;    
	font-size: 16px;    
	margin: 20px 50px 100px;    
	-webkit-transition-duration: 0.4s; /* Safari */    
	transition-duration: 0.4s;    
	cursor: pointer;
}
.back{
	background-color: white;
	color: black;
	border: 2px solid #4CAF50;
}
.back:hover{
	background-color: #4CAF50;    
	color: white;
}
.confirm{
	background-color: white;     
	color: black;     
	border: 2px solid #008CBA;
}
.confirm:hover {    
	background-color: #008CBA;    
	color: white;
}
</style>
</head>

<body>
	<div class="content">
		<div>
			<div class="text">
				<p>订单详情</p>
			</div>
		</div>
		
		<form action="${pageContext.request.contextPath }/order/update.action" method="POST">
		<div class="table">
			<table>
				<thead>
					<tr >
						<th>编号</th>
						<th>产品编号</th>
						<th>产品名称</th>
						<th>购买数量</th>
						<th>价格</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${orderItem}" var="orderItem">
					<tr>
					    <td>${orderItem.id}</td>
						<td>${orderItem.productId}</td>
						<td>${orderItem.productName}</td>
						<td>${orderItem.buyNum}</td>
						<td>${orderItem.buyPrice}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div class="message">
		<div class="field">
			<label class="">订单编号：</label>
        	<div class="layui-input-block">
            <input type="text" name="id" lay-verify="required" value="${order.id}" readonly="true" style="background-color:#BDBDBD">
        	</div>
		</div>
		<div class="field">
			<label class="">会员编号：</label>
        	<div class="">
            <input type="text" name="user_id" lay-verify="required" value="${order.user_id}" readonly="true" style="background-color:#BDBDBD">
        	</div>
		</div>
		<div class="field">
			<label class="">收货人姓名：</label>
        	<div class="">
            <input type="text" name="receiverName" lay-verify="required" value="${order.receiverName}">
        	</div>
		</div>
		<div class="field">
			<label class="">联系电话：</label>
        	<div class="">
            <input type="text" name="receiverPhone" lay-verify="required" value="${order.receiverPhone}">
        	</div>
		</div>
		<div class="field">
			<label class="">收货地址：</label>
        	<div class="">
            <input type="text" name="receiverAddress" lay-verify="required" value="${order.receiverAddress}">
        	</div>
		</div>
		<div class="field">
			<label class="">支付状态：</label>
        	<div class="">
            <select name="paystate" class="" value="${order.paystate}">
            	<option value="1"
            		<c:if test="${order.paystate==1}">selected</c:if>
            		>未支付
            	</option>
            	<option value="2" 
            		<c:if test="${order.paystate==2}">selected</c:if>
            		>已支付
            	</option>
            </select>
        	</div>
		</div>
		<div class="field">
			<label class="">订单状态：</label>
        	<div class="">
            <select name="orderState" class="" value="${order.orderState}">
            	<option value="1" 
            	<c:if test="${order.orderState==1}">selected</c:if>
            		>未发货</option>
            	<option value="2" <c:if test="${order.orderState==2}">selected</c:if>
            		>配送中</option>
            	<option value="3" <c:if test="${order.orderState==3}">selected</c:if>
            		>已到达</option>
            	<option value="4" <c:if test="${order.orderState==4}">selected</c:if>
            		>已取消</option>
            </select>
        	</div>
		</div>
		<div class="field">
			<label class="">下单时间：</label>
        	<div class="">
            <input type="text" name="ordertime" lay-verify="required" value="${order.ordertime}" readonly="true" style="background-color:#BDBDBD">
        	</div>
		</div>
		<div class="field">
			<label class="">总金额：</label>
        	<div class="">
            <input type="text" name="money" lay-verify="required" value="${order.money}" readonly="true" style="background-color:#BDBDBD">
        	</div>
		</div>
		<div class="field">
			<label class="">订单备注：</label>
        	<div class="">
            <input type="text" name="remarks" lay-verify="required" value="${order.remarks}" readonly="true" style="background-color:#BDBDBD">
        	</div>
		</div>
		
        <div class="field" style="margin:10px 10px 100px">
            <a href="javascript:history.back(-1)"><button type="button" class="back">上一步</button></a>
            <button class="confirm" lay-submit lay-filter="saveBtn">确认</button>
        </div>
        </div>
	</form>
	
	</div>
</body>
</html>