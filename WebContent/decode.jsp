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
<title>Mitiao-密条 :查看内容</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="keywords" content="密条，保密内容，加密发送">
<meta name="description" content="密条：你的秘密纸条，全方面保护你的交流隐私。">
<meta name="renderer" content="webkit">
<meta name="force-rendering" content="webkit">
<%@include file="/WEB-INF/include/importFile.inc"%>
<style type="text/css">
.div_allinline {
	text-align: center;
	margin: 0 auto;
	padding: 0;
	clear: both;
}

.subdiv_allinline {
	margin: 0;
	padding: 0;
	display: inline-block;
	_display: inline;
	*display: inline;
	zoom: 1;
}
</style>
</head>

<body>
	<%@include file="/header.jsp"%>
	<div id="container" style="padding: 10px 10px 0px 22%;">
		<div class="un-text-center">
			<c:if test="${ empty error }">
				<label for="name">密条${ noteId }的内容为：</label>
				<br />
				<textarea class="form-control"
					style="width: 800px; background-color: #fff;"
					onmouseover="this.select();" name="content" id="content" cols="80"
					rows="10" readonly="readonly">${contentData }</textarea>
				<c:if test="${empty availableTime }">
					<label for="name" style="color: red"><span
						class="glyphicon glyphicon-warning-sign">
							此密条为一次性查看，关闭或刷新页面将会消失，请复制并保管以上内容。</span></label>
				</c:if>
			</c:if>
			<c:if test="${ not empty availableTime }">
				<label for="name" style="color: red"><span
					class="glyphicon glyphicon-warning-sign"> 你的密条将在${ availableTime }被销毁，请尽快阅读并保存。</span></label>
			</c:if>
		</div>
	</div>
	<c:if test="${ not empty availableTime }">
		<div class="div_allinline">
			<div class="subdiv_allinline">
				<form action="/mitiao/${ noteId }" method="post"
					class="bs-example bs-example-form" role="form" id="formid">
					<input type="hidden" name="_method" value="DELETE">
					<button type="submit" class="btn btn-danger"
						onclick="return check()" title="销毁后，内容无法查询，但操作时间可以查询！"
						data-toggle="tooltip" data-placement="bottom">销毁密条</button>
				</form>
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div class="subdiv_allinline">
				<form action="/mitiao/${ noteId }" method="post"
					class="bs-example bs-example-form" role="form" id="formid1">
					<input type="hidden" name="_method" value="DELETE"> <input
						type="hidden" name="destroy" value="1"> <input
						class="btn btn-danger" type="submit" title="销毁后，此记录将彻底删除，再也查询不到！"
						data-toggle="tooltip" data-placement="bottom"
						onclick="return check()" value="彻底销毁" />
				</form>
			</div>
		</div>
	</c:if>

	<c:if test="${not empty error }">
		<div class="text-center">
			<label for="name">${error}</label>
		</div>
	</c:if>
	<br />
	<br />
	<%@include file="/footer.jsp"%>
</body>
<script type="text/javascript">
	$(function() {
		$("[data-toggle='tooltip']").tooltip();
	});
	function check() {
		return confirm("销毁后将无法恢复，是否销毁？");
	}
</script>
</html>
