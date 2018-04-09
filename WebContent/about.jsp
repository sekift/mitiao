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
<title>Mitiao-密条 :说明</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="keywords" content="密条，保密内容，加密发送">
<meta name="description" content="密条：你的秘密纸条，全方面保护你的交流隐私。">
<meta name="renderer" content="webkit">
<meta name="force-rendering" content="webkit">
<%@include file="/WEB-INF/include/importFile.inc"%>
</head>

<body>
	<%@include file="/header.jsp"%>
	<div class="form-group" style="padding: 0px 0px 0px 30%;">
		<h4>加密部分：</h4>
		<ol>
			<li>分配随机的16位大小写字母数字组合的秘钥，如果用户有自定义则使用自定义的秘钥；</li>
			<li>将秘钥经过sha1算法转成40位的hashNoteId,若已经存在则返回错误；</li>
			<li>用AES算法对内容进行基于秘钥的加密；</li>
			<li>将hashNoteId和加密后的内容对应，并保存到数据库。</li>
		</ol>
		<h4>解密部分：</h4>
		<ol>
			<li>将密匙经过sha1转成hashNoteId；</li>
			<li>根据hashNoteId获取已加密的内容；</li>
			<li>使用AES算法对内容进行基于秘钥的解密；</li>
			<li>返回数据展示，如果是一次性阅读，则删除此条数据。</li>
		</ol>
		<h4>例如，以下是数据库中的一条记录：</h4>
		<ul>
			<li>hashNoteId：BF4950F7FF9CA52A3CCEC8BB08A6323247E5F2FD</li>
			<li>content：3A7E5A3D21BABA12275FAB7AC5032177</li>
		</ul>
		<!-- <h4>源代码：</h4>
<dl>
<dt><a href="https://github.com/sekift/mitiao" target="_blank">https://github.com/sekift/mitiao</a></dt>
</dl> -->
</div>

<%@include file="/footer.jsp"%>
</body>
</html>