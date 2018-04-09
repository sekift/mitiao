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
<title>Mitiao-密条 :信息页面</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="keywords" content="密条，保密内容，加密发送">
<meta name="description" content="密条：你的秘密纸条，全方面保护你的交流隐私。">
<meta name="renderer" content="webkit">
<meta name="force-rendering" content="webkit">
<%@include file="/WEB-INF/include/importFile.inc"%>
<style>
#link {
	width: 40%;
	height: 40px;
	font-family: 微软雅黑, 宋体;
	font-size: 1.4em;
	background-color: #fff;
}
</style>
</head>

<body>
	<%@include file="/header.jsp"%>
	<div class="container" style="padding: 10px 10px 0px 22%;">
		<label for="name">你的链接如下，请复制保管！</label>
		<div>
			<!-- <p class="text-info"><%=basePath%>${noteId}</p>  -->
			<input class="form-control" type="text" style="width: 600px;"
				id="link" onmouseover="this.select();" name="link"
				readonly="readonly" value="<%=basePath%>${noteId}" />
		</div>
		<div class="btn-group btn-group-sm" style="padding: 10px 0px 5px 0px;">
			<a id="mailto" class="btn btn-danger"
				href="mailto:?body=<%=basePath%>${noteId}">邮件发送</a>
		</div>
	</div>
	<br />
	<br />
	<%@include file="/footer.jsp"%>
</body>
</html>


