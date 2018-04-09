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
<link rel="shortcut icon" href="http://www.bubbt.com/assets/images/favicon.ico" type="image/x-icon" />
<title>Mitiao-密条 : 头文件</title>
</head>

<body>
<div class="layout">
    <!--===========layout-header================-->
    <div class="layout-header am-hide-sm-only">
      <div class="header-box" data-am-sticky>
        <!--nav start-->
        <div class="nav-contain">
          <div class="nav-inner">
              <script src="http://www.bubbt.com/assets/js/header.js"></script>
          </div>
        </div>
        <!--nav end-->
      </div>
    </div>

    <!--mobile header start-->
    <div class="m-header">
      <div class="am-g am-show-sm-only">
        <script src="http://www.bubbt.com/assets/js/header.js"></script>
      </div>
    <!--mobile header end-->
    </div>
</div>
<!--===========layout-header================-->
<div
	style="padding: 5px 0px 5px 0px; background-color: Moccasin; text-align: center;">
<h1><a
	href="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/"%>">密条：你的秘密纸条</a></h1>
</div>
</body>
</html>