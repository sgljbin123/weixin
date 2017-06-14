<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
<title>移车服务|移车通知</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="bootstrap/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/weui.min.css">
</head>

<body>
	<div class="container">
		<label class="weui-lable"></label>
	</div>
<div class="page msg_warn js_show">
    <div class="weui-msg">
        <div class="weui-msg__icon-area"><i class="weui-icon-warn weui-icon_msg"></i></div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">${msg}</h2>
        </div>
        <div class="weui-msg__opr-area">
            <p class="weui-btn-area">
                <a href="javascript:history.back();" class="weui-btn weui-btn_primary">返回</a>
            </p>
        </div>
       
    </div>
</div>
</body>
</html>
