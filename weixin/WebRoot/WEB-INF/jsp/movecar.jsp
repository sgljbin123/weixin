<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>移车服务|移车通知</title>
<base href="<%=basePath %>" /> 
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="bootstrap/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/weui.min.css">
<script type="text/javascript">
$(function(){
	$("#usernavdiv").hide();
	$("#sendmovecarmsgform").submit(function(e){
		plantNumber = $("#plantNumber").val().trim();
		if(plantNumber.length!=5){
			$("#plantNumber").focus();
		}else{
			$.post(
				"sendmovecarmsg",
				$("#sendmovecarmsgform").serialize(),
				function(data,success){
					if(success=="success"){
						$("#movecardiv").hide();
						$("#usernavdiv").show();
						
					}
				}
			);
		}
		e.preventDefault();
	});
});
</script>
</head>

<body>
<div class="container" id="usernavdiv">
	<div class="page msg_success js_show">
    <div class="weui-msg">
        <div class="weui-msg__icon-area"><i class="weui-icon-success weui-icon_msg"></i></div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">通知成功</h2>
        </div>
    </div>
</div>
</div>

	<div class="container" id="movecardiv">
		<form class="weui-cells weui-cells_form"
			id="sendmovecarmsgform">
			<div class="weui-cell weui-cell-select" id="plantdiv">
				<div class="weui-cell__hd">
					<label class="weui-label">车牌号</label>
				</div>
				<div class="weui-cell__bd">
					<select class="weui-select" name="plateProvince">
						<option value='安徽'>安徽</option>
						<option value='澳门'>澳门</option>
						<option value='北京'>北京</option>
						<option value='福建'>福建</option>
						<option value='甘肃'>甘肃</option>
						<option value='广东'>广东</option>
						<option value='广西'>广西</option>
						<option value='贵州'>贵州</option>
						<option value='海南'>海南</option>
						<option value='河北'>河北</option>
						<option value='河南'>河南</option>
						<option value='黑龙江'>黑龙江</option>
						<option value='湖北'>湖北</option>
						<option value='湖南'>湖南</option>
						<option value='吉林'>吉林</option>
						<option value='江苏'>江苏</option>
						<option value='江西'>江西</option>
						<option value='辽宁'>辽宁</option>
						<option value='内蒙古'>内蒙古</option>
						<option value='宁夏'>宁夏</option>
						<option value='青海'>青海</option>
						<option value='山东'>山东</option>
						<option value='山西'>山西</option>
						<option value='陕西'>陕西</option>
						<option value='上海'>上海</option>
						<option value='四川'>四川</option>
						<option value='台湾'>台湾</option>
						<option value='天津'>天津</option>
						<option value='西藏'>西藏</option>
						<option value='香港'>香港</option>
						<option value='新疆'>新疆</option>
						<option value='云南'>云南</option>
						<option value='浙江'>浙江</option>
						<option value='重庆'>重庆</option>
					</select>
				</div>
				<div class="weui-cell__bd">
					<select class="weui-select" name="plateChar">
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
						<option value="D">D</option>
						<option value="E">E</option>
						<option value="F">F</option>
						<option value="G">G</option>
						<option value="H">H</option>
						<option value="I">I</option>
						<option value="J">J</option>
						<option value="K">K</option>
						<option value="L">L</option>
						<option value="M">M</option>
						<option value="N">N</option>
						<option value="O">O</option>
						<option value="P">P</option>
						<option value="Q">Q</option>
						<option value="R">R</option>
						<option value="S">S</option>
						<option value="T">T</option>
						<option value="U">U</option>
						<option value="V">V</option>
						<option value="W">W</option>
						<option value="X">X</option>
						<option value="Y">Y</option>
						<option value="Z">Z</option>

					</select>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="tel" name="plateNumber"
						maxlength="5" pattern="[A-Za-z0-9]{5}" placeholder="5位车牌号"
						id=plantNumber />
				</div>
			</div>
			<div>
				<input class="weui-btn weui-btn_primary" type="submit" value="提交" id="submitbtn"/>
			</div>
			</form>
	</div>
</body>
</html>
