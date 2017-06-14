<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jinpeng.js"></script>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/reset.css">
</head>

<body>
	<div class="container">
		<div class="header">
			<ul class="nav nav-tabs nav-justified">
				<li><a class="dropdown-toggle" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> 部门日报 <span
						class="caret"></span>
				</a>

					<ul class="dropdown-menu menufont">
						<li><a href="operation_one_report">运营一部</a></li>
						<li><a href="operation_two_report">运营二部</a></li>
						<li><a href="operation_proxy_report">代运营</a></li>
						<li><a href="customer_report">客服部</a></li>
						<li><a href="operation_IT_report">运维部</a></li>
					</ul></li>
				<li><a href="editUser">个人设置</a></li>
				<li><a>消息</a></li>
				<li><a href="loginout">退出</a></li>
			</ul>
		</div>
	</div>
</body>
</html>
