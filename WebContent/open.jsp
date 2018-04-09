<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<!--[if IE 6]><html class="ie6 lte9 lte8 lte7" lang="zh-cn"><![endif]-->
<!--[if IE 7]><html class="ie7 lte9 lte8 lte7" lang="zh-cn"><![endif]-->
<!--[if IE 8]><html class="ie8 lte9 lte8" lang="zh-cn"><![endif]-->
<!--[if IE 9]><html class="ie9 lte9" lang="zh-cn"><![endif]-->
<!--[if !(IE 6) | !(IE 7) | !(IE 8) | !(IE 9)  ]><!-->
<html lang="zh-cn">
<!--<![endif]-->
<%@ page isELIgnored="false"%>
<head>
<title>Mitiao-公开内容</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="keywords" content="公开，公开内容，公开发送">
<meta name="description" content="公开：你的公开纸条，一键分享你的交流。">
<meta name="renderer" content="webkit">
<meta name="force-rendering" content="webkit">
<%@include file="/WEB-INF/include/importFile.inc"%>
</head>

<body>
	<%@include file="/header.jsp"%>
	<div class="container" style="padding:2% 0% 0% 2%;">
		<c:if test="${ not empty error }">
			<div id="myAlert" class="alert alert-success" style="width: 80%;">
				<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>${error }</strong>
			</div>
		</c:if>

		<form action="/mitiao/open/add" class="bs-example bs-example-form"
			role="form" id="loginForm" method="post">
			<div class="form-group form" style="width: 100%;">
				<label for="name">标题，不得大于50字：</label> 
				<input class="form-control" type="text" name="title" id="title" value=""> 
				
				<label for="name">内容，不得大于5000字:</label>
				<input class="form-control" type="text" name="content" id="content" value="">

				<div class="form-group">
					<label for="name">选择公开时间（可选）:</label> <select name="ddtime"
						class="form-control">
						<option value="">默认永久公开</option>
						<option value="1" <c:if test="${dtime==1}">selected</c:if>>公开1分钟</option>
						<option value="60" <c:if test="${dtime==60}">selected</c:if>>公开1小时</option>
						<option value="1440" <c:if test="${dtime==1440}">selected</c:if>>公开1天</option>
						<option value="43200"
							<c:if test="${dtime==43200}">selected</c:if>>公开1个月</option>
						<option value="15768000"
							<c:if test="${dtime==15768000}">selected</c:if>>公开1年</option>
						<option value="157680000"
							<c:if test="${dtime==157680000}">selected</c:if>>公开10年</option>
					</select>
				</div>
			</div>
			<span class="input-group-btn"> <input class="btn btn-primary"
				type="submit" onclick="return submitFunc()" value="保存" />
			</span>
		</form>
	<br />
	<form class="bs-example bs-example-form"
		role="form" name="queryform" id="queryform" method="post">
		<div class="form-group form-inline" style="width: 100%;text-align:center;">
			<label for="name">关键字：</label> <input class="form-control"
				type="text" name="keyword" id="keyword" value=${keyword }>
				<input
				class="btn btn-primary" type="submit" name="query" onclick="doQuery(true)"
				value="查询" />
		</div>

		<div class="table-responsive" style="padding-left: 0px;width: 100%;">
			<table class="table table-bordered">
				<tr>
					<td class="warning">标题</td>
					<td class="warning">内容</td>
					<td class="warning">过期时间</td>
				</tr>
				<c:forEach items="${list}" var="item" varStatus="index">
					<tr>
						<td id="resultTitle">${item.hashNoteId }</td>
						<td id="resultContent">${item.content }</td>
						<td id="resultTime">${item.availableTime }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<%@include file="/WEB-INF/include/paging.inc"%>
	</form>

	<c:if test="${ not empty error }">
		<script type="text/javascript">
			$(function() {
				$(".close").click(function() {
					$("#myAlert").alert();
				});
			});
		</script>
	</c:if>
	<br />
	<br />
</div>
<%@include file="/footer.jsp"%>
</body>
<script type="text/javascript">
	function check() {
		var content = $("#content").val();
		if (null == content || "" == content.trim()) {
			alert("请输入内容再提交。");
			return false;
		}

		return true;
	}

	function submitFunc() {
		return check();
	}

	function doQuery(newSql) {
		if(newSql){ 
			//重新初始化页信息
			resetPaging();
		}
		document.queryform.action="<%=basePath%>open/search";
		document.queryform.submit();
		openTipsDialog();
	}
</script>
</html>