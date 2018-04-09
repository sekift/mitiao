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
<title>Mitiao-密条 :加密内容</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="keywords" content="密条，保密内容，加密发送">
<meta name="description" content="密条：你的秘密纸条，全方面保护你的交流隐私。">
<meta name="renderer" content="webkit">
<meta name="force-rendering" content="webkit">
<%@include file="/WEB-INF/include/importFile.inc"%>
</head>

<body>
<%@include file="/header.jsp" %>
<div class="container" style="padding: 10px 10px 0px 15%;">
<c:if test="${ not empty error }">
<div id="myAlert" class="alert alert-success" style="width:800px;">
   <a href="#" class="close" data-dismiss="alert">&times;</a>
   <strong>${error }</strong>
</div>
</c:if>

<form action="/mitiao/" class="bs-example bs-example-form" role="form" id="loginForm" method="post">
<div class="form-group" style="width: 800px;  ">
	<label for="name">请于下框输入内容，字数不得大于5000：</label>
				<textarea class="form-control" name="content" id="content" cols="80"
					rows="10">${content }</textarea>

				<div class="form-group">
	<label for="name">选择销毁时间（可选）:</label>
	    <select name="dday" class="form-control">
			<option value="">默认阅读后销毁</option>
			<option value="1" <c:if test="${dday==1}">selected</c:if>>1天后销毁</option>
			<option value="3" <c:if test="${dday==3}">selected</c:if>>3天后销毁</option>
			<option value="7" <c:if test="${dday==7}">selected</c:if>>7天后销毁</option>
			<option value="30" <c:if test="${dday==30}">selected</c:if>>30天后销毁</option>
			<option value="365" <c:if test="${dday==365}">selected</c:if>>1年后销毁</option>
			<option value="1825" <c:if test="${dday==1825}">selected</c:if>>5年后销毁</option>
			<option value="365000" <c:if test="${dday==365000}">selected</c:if>>1000年后销毁</option>
		</select>
	</div>
	
	<label for="name">自定义秘钥（可选，6-15位数字字母组合）:</label>
	<input class="form-control" type="text" name="keyword" id="keyword" value=${keyword }>
	
</div>
<span class="input-group-btn">
<input class="btn btn-default" type="submit" onclick="return submitFunc()"  value="提交" />
</span>
</form>
</div>

<c:if test="${ not empty error }">
	<script type="text/javascript">
	$(function(){
		   $(".close").click(function(){
		      $("#myAlert").alert();
		   });
		});  
   </script>
</c:if>
<br />
<br />
<%@include file="/footer.jsp" %>
</body>
<script type="text/javascript">
  function check(){
   var content = $("#content").val();
   if(null == content||"" == content.trim()){
	   alert("请输入内容再提交。");
	   return false;
   }

   var keyword = $("#keyword").val();
   if("" != keyword.trim() && (keyword.trim().length < 6 || keyword.trim().length > 15)){
	   alert("自定义秘钥必须是6-15位的数字字母组合。");
	   return false;
   }
   
   return true;
  }

  function submitFunc(){
	  return check();
  }
</script>
</html>