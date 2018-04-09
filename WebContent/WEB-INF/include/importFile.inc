<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; //+":"+request.getServerPort()
%>

<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">

<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<script src="http://cdn.bootcss.com/amazeui/2.7.2/js/amazeui.min.js" charset="utf-8"></script>
<link href="http://cdn.bootcss.com/amazeui/2.7.2/css/amazeui.min.css" rel="stylesheet">

<link rel="stylesheet" href="http://www.bubbt.com/assets/css/common.min.css" />
<link rel="stylesheet" href="http://www.bubbt.com/assets/css/index.min.css" />
<script src="http://www.bubbt.com/assets/js/common.js" charset="utf-8"></script>	