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
<title>Mitiao-密条 : 尾文件</title>
</head>

<body>
	<div
		style="padding: 5px 0px 10px 0px; background-color: Aqua; text-align: center;">
		<h2>
			密条：你的秘密纸条，全方面保护你的交流隐私。<a
				href="<%=request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + request.getContextPath()
					+ "/"%>about.jsp">技术说明</a>
		</h2>
	</div>
		<!--===========layout-footer================-->
  <div class="layout-footer">
    <div class="footer">
      <div style="background-color:#383d61" class="footer--bg"></div>
      <div class="footer--inner">
        <div class="container">
          <div class="footer_main">
            <div class="am-g">
                <script src="http://www.bubbt.com/assets/js/footer.js"></script>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--===========layout-footer================-->
</body>
</html>