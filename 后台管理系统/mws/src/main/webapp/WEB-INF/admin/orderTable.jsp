<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>麦维思烘培坊管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="<%=basePath%>lib/layui-v2.5.5/css/layui.css" media="all">
<link rel="stylesheet" href="<%=basePath%>css/public.css" media="all">
<link href="<%=basePath%>css/app.css" rel="stylesheet" type="text/css" />

<style>
.layui-table-cell {
	padding-left:15px;
	padding-right:0px;
	text-align:center;
}

.laytable-cell-1-0-1 {
	width: 9vw;
}

.laytable-cell-1-0-2 {
	width: 5vw;
}

.laytable-cell-1-0-3 {
	width: 18vw;
}

.laytable-cell-1-0-4 {
	width: 5vw;
}

.laytable-cell-1-0-5 {
	width: 8vw;
}

.laytable-cell-1-0-6 {
	width: 5vw;
}

.laytable-cell-1-0-7 {
	width: 12vw;
}

.laytable-cell-1-0-8 {
	width: 5vw;
}

.laytable-cell-1-0-9 {
	width: 5vw;
}

.laytable-cell-1-0-10 {
	width: 16vw;
}

.laytable-cell-1-0-11 {
	width: 12vw;
}
select{
	margin:0;
padding:0;
list-style:none;
	float:left;
	width:160px;
height:47px;
line-height:32px;
padding:0 10px;
border:solid 1px #ccc;
float:left;
}
select:hover{
background:#666;
color:#fff;
}
.input{
	width:300px;
	margin-left:170px;
	display: block;
	padding-left: 10px;
	height:47px;
	border-width: 1px;
    border-style: solid;
    background-color: #fff;
    border-radius: 2px;
    outline: 0;
    -webkit-appearance: none;
    -webkit-transition: all .3s;
    box-sizing: border-box;
    overflow: visible;
    font-family: inherit;
    font-size: inherit;
    font-style: inherit;
    font-weight: inherit;
}
.selectContent{
	width:500px;
	float:left;
	margin-top:-10px;
	height:60px
}
.selectBtn{
	height:47px;
	margin-top:-10px
}
input:focus{
	border-color:#66afe9;
	outline:0;
	-webkit-box-shadow:inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
	box-shadow:inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
}
</style>
</head>
<body>
	<div class="layuimini-container">
		<div class="layuimini-main">

			<fieldset class="table-search-fieldset">
				<legend style="width: 100px;">搜索信息</legend>
				<div style="margin: 10px 10px 10px 10px">
					<form action="${pageContext.request.contextPath }/order/list2.action" method="get">
						<div class="selectContent">
								<select name="select" >
									<option value="0">-请选择搜索条件-</option>
									<option value="1">订单编号</option>
									<option value="2">用户编号</option>
								</select>
								<input type="text" name="value"
										autocomplete="off" class="input">
						</div>
						<div class="" style="float:left">
							<button type="submit" class="layui-btn layui-btn-primary selectBtn"
									lay-submit lay-filter="data-search-btn" >
									<i class="layui-icon"></i> 搜 索
								</button>
						</div>
					</form>
				</div>
			</fieldset>


			<table class="layui-hide" id="currentTableId"
				lay-filter="currentTableFilter"></table>


			<div class="layui-form layui-border-box layui-table-view"
				lay-filter="LAY-table-1" lay-id="currentTableId" style="">
				<div class="layui-table-tool">
					<div class="layui-table-tool-temp">
						<div class="layui-btn-container">
							<a>
								<button
									class="layui-btn layui-btn-normal layui-btn-sm data-add-btn">请前往小程序添加订单</button>
							</a>
						</div>
					</div>

				</div>
				<div class="layui-table-box">
					<div class="layui-table-header">
						<table cellspacing="0" cellpadding="0" border="0"
							class="layui-table" lay-skin="line">
							<thead>
								<tr>
									<th data-field="id" data-key="1-0-1" class=" layui-unselect"><div
											class="layui-table-cell laytable-cell-1-0-1">
											<span>编号</span><span class="layui-table-sort layui-inline"><i
												class="layui-edge layui-table-sort-asc" title="升序"></i><i
												class="layui-edge layui-table-sort-desc" title="降序"></i></span>
										</div></th>
									<th data-field="name" data-key="1-0-2" class=" layui-unselect"><div
											class="layui-table-cell laytable-cell-1-0-2">
											<span>金额</span><span class="layui-table-sort layui-inline"><i
												class="layui-edge layui-table-sort-asc" title="升序"></i><i
												class="layui-edge layui-table-sort-desc" title="降序"></i></span>
										</div></th>
									<th data-field="price" data-key="1-0-3"
										class=" layui-unselect"><div
											class="layui-table-cell laytable-cell-1-0-3">
											<span>收货地址</span><span class="layui-table-sort layui-inline"><i
												class="layui-edge layui-table-sort-asc" title="升序"></i><i
												class="layui-edge layui-table-sort-desc" title="降序"></i></span>
										</div></th>
									<th data-field="category" data-key="1-0-4"
										class=" layui-unselect"><div
											class="layui-table-cell laytable-cell-1-0-4">
											<span>收货人</span><span class="layui-table-sort layui-inline"><i
												class="layui-edge layui-table-sort-asc" title="升序"></i><i
												class="layui-edge layui-table-sort-desc" title="降序"></i></span>
										</div></th>
									<th data-field="pnum" data-key="1-0-5"
										class=" layui-unselect"><div
											class="layui-table-cell laytable-cell-1-0-5">
											<span>收货电话</span><span class="layui-table-sort layui-inline"><i
												class="layui-edge layui-table-sort-asc" title="升序"></i><i
												class="layui-edge layui-table-sort-desc" title="降序"></i></span>
										</div></th>
									<th data-field="imgurl" data-key="1-0-6" class=" layui-unselect"><div
											class="layui-table-cell laytable-cell-1-0-6">
											<span>支付状态</span><span class="layui-table-sort layui-inline"><i
												class="layui-edge layui-table-sort-asc" title="升序"></i><i
												class="layui-edge layui-table-sort-desc" title="降序"></i></span>
										</div></th>
									<th data-field="description" data-key="1-0-7"
										class=" layui-unselect"><div
											class="layui-table-cell laytable-cell-1-0-7">
											<span>订单时间</span><span class="layui-table-sort layui-inline"><i
												class="layui-edge layui-table-sort-asc" title="升序"></i><i
												class="layui-edge layui-table-sort-desc" title="降序"></i></span>
										</div></th>
									<th data-field="manufactureDate" data-key="1-0-8"
										class=" layui-unselect"><div
											class="layui-table-cell laytable-cell-1-0-8">
											<span>用户id</span><span class="layui-table-sort layui-inline"><i
												class="layui-edge layui-table-sort-asc" title="升序"></i><i
												class="layui-edge layui-table-sort-desc" title="降序"></i></span>
										</div></th>
									<th data-field="shelfLife" data-key="1-0-9"
										class=" layui-unselect"><div
											class="layui-table-cell laytable-cell-1-0-9">
											<span>订单状态</span><span class="layui-table-sort layui-inline"><i
												class="layui-edge layui-table-sort-asc" title="升序"></i><i
												class="layui-edge layui-table-sort-desc" title="降序"></i></span>
										</div></th>
									<th data-field="shelfLife" data-key="1-0-11"
										class=" layui-unselect"><div
											class="layui-table-cell laytable-cell-1-0-11">
											<span>订单备注</span><span class="layui-table-sort layui-inline"><i
												class="layui-edge layui-table-sort-asc" title="升序"></i><i
												class="layui-edge layui-table-sort-desc" title="降序"></i></span>
										</div></th>
									<th data-field="5" data-key="1-0-10"
										class=" layui-table-col-special"><div
											class="layui-table-cell laytable-cell-1-0-10" align="center">
											<span>操作</span>
										</div></th>
								</tr>
							</thead>
						</table>
					</div>
					<div class="layui-table-body layui-table-main">
						<table cellspacing="0" cellpadding="0" border="0"
							class="layui-table" lay-skin="line">
							<tbody>
								<c:forEach items="${orders}" var="o">
									<tr data-index="0" class="">
										<td data-field="id" data-key="1-0-1" class=""><div
												class="layui-table-cell laytable-cell-1-0-1">${o.id}</div></td>
										<td data-field="name" data-key="1-0-2" class=""><div
												class="layui-table-cell laytable-cell-1-0-2">${o.money}</div></td>
										<td data-field="price" data-key="1-0-3" class=""><div
												class="layui-table-cell laytable-cell-1-0-3">${o.receiverAddress}</div></td>
										<td data-field="category" data-key="1-0-4" class=""><div
												class="layui-table-cell laytable-cell-1-0-4">${o.receiverName}</div></td>
										<td data-field="pnum" data-key="1-0-5" class=""><div
												class="layui-table-cell laytable-cell-1-0-5">${o.receiverPhone}</div></td>
										<td data-field="imgurl" data-key="1-0-6" class=""><div
												class="layui-table-cell laytable-cell-1-0-6">${o.paystate}</div></td>
										<td data-field="description" data-key="1-0-7" class=""><div
												class="layui-table-cell laytable-cell-1-0-7">${o.ordertime}</div></td>
										<td data-field="manufactureDate" data-key="1-0-8" class=""><div
												class="layui-table-cell laytable-cell-1-0-8">${o.user_id}</div></td>
										<td data-field="shelfLife" data-key="1-0-9" class=""><div
												class="layui-table-cell laytable-cell-1-0-9">${o.orderState}</div></td>
										<td data-field="shelfLife" data-key="1-0-11" class=""><div
												class="layui-table-cell laytable-cell-1-0-11">${o.remarks}</div></td>
										<td data-field="6" data-key="1-0-10" align="center"
											data-off="true" data-minwidth="150"
											class="layui-table-col-special"><div
												class="layui-table-cell laytable-cell-1-0-10">
												
												<a
													class="layui-btn layui-btn-normal layui-btn-xs data-count-edit"
													lay-event="edit" 
													href="${pageContext.request.contextPath }/order/update.action?id=${o.id}">查看详情并编辑</a>
												<a
													class="layui-btn layui-btn-xs layui-btn-danger data-count-delete"
													lay-event="delete"
													href="${pageContext.request.contextPath }/order/delete.action?id=${o.id}">删除</a>
											</div></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

				<form action="${pageContext.request.contextPath }/order/jumpPage.action">
					<div style="text-align: center;" id="shequfen">
						当前第${paging.currentPage }页/共${paging.totalPage }页

						<div class="layui-box layui-laypage layui-laypage-default"
							style="padding-right: 10px">
							<span class="layui-laypage-skip">到第<input type="text"
								class="layui-input" name="pageNumber">页
							</span>
						</div>
						<button
							class="layui-btn layui-btn-normal layui-btn-sm data-add-btn">确定</button>

						<c:choose>
							<c:when test="${paging.currentPage==1&&paging.totalPage==0}">

							</c:when>
							<c:when test="${paging.currentPage==1&&paging.totalPage==1}">

							</c:when>
							<c:when test="${paging.currentPage==paging.totalPage}">
								<a class="layui-btn layui-btn-xs"
									style="width: 50px; height: 25px; font-size: 15px;"
									href="${pageContext.request.contextPath }/order/homePage.action">首页</a>
								<a class="layui-btn layui-btn-xs"
									style="width: 70px; height: 25px; font-size: 15px;"
									href="${pageContext.request.contextPath }/order/upPage.action">上一页</a>
							</c:when>
							<c:when test="${paging.currentPage==1 }">
								<a class="layui-btn layui-btn-xs"
									style="width: 70px; height: 25px; font-size: 15px;"
									href="${pageContext.request.contextPath }/order/downPage.action">下一页</a>
								<a class="layui-btn layui-btn-xs"
									style="width: 50px; height: 25px; font-size: 15px;"
									href="${pageContext.request.contextPath }/order/lastPage.action">末页</a>
							</c:when>
							<c:when test="${paging.currentPage==paging.totalPage }">
								<a class="layui-btn layui-btn-xs"
									style="width: 50px; height: 25px; font-size: 15px;"
									href="${pageContext.request.contextPath }/order/homePage.action">首页</a>
								<a class="layui-btn layui-btn-xs"
									style="width: 70px; height: 25px; font-size: 15px;"
									href="${pageContext.request.contextPath }/order/upPage.action">上一页</a>
							</c:when>
							<c:otherwise>
								<a class="layui-btn layui-btn-xs"
									style="width: 50px; height: 25px; font-size: 15px;"
									href="${pageContext.request.contextPath }/order/homePage.action">首页</a>
								<a class="layui-btn layui-btn-xs"
									style="width: 70px; height: 25px; font-size: 15px;"
									href="${pageContext.request.contextPath }/order/upPage.action">上一页</a>
								<a class="layui-btn layui-btn-xs"
									style="width: 70px; height: 25px; font-size: 15px;"
									href="${pageContext.request.contextPath }/order/downPage.action">下一页</a>
								<a class="layui-btn layui-btn-xs"
									style="width: 50px; height: 25px; font-size: 15px;"
									href="${pageContext.request.contextPath }/order/lastPage.action">末页</a>
							</c:otherwise>
						</c:choose>
					</div>

				</form>
				

			</div>
			
			
			





		</div>
	</div>
	<script src="<%=basePath%>lib/layui-v2.5.5/layui.js" charset="utf-8"></script>



</body>
</html>