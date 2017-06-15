<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>车主/用户注册</title>
<base href="<%=basePath %>" /> 
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="bootstrap/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/weui.min.css">
<script type="text/javascript">
	$(function() {
		$('#dialogs').on('click', '.weui-dialog__btn', function() {
			$(this).parents('.js_dialog').fadeOut(200);
		});
		$("#usernavdiv,#js_show").hide();
		switchuserType();
		$("#registerform").submit(function(e){
			username = $("#username").val().trim();
			if(username.length==0){
				$("#username").focus();
				return false;
			}
		
			plantNumber = $("#plantNumber").val().trim();
			if(plantNumber.length!=5 && userType==0){
				$("#plantNumber").focus();
				return false;
			}
			telephone = $("#telinput").val().trim();
			if(telephone.length==0){
				$("#telinput").focus();
				return false;
			}
			verificationCode = $("#verificationCode").val().trim();
			if(verificationCode.length==0){
				$("#verificationCode").focus();
				return false;
			}
			$("#submitbtn").hide();
			$("#js_show").show();
			$.post(
			 "registerUser",
			 $(this).serialize(),
			 function(data){
				 if(data=="success"){
					 $("#userregisterdiv,#js_show").hide();
					 $("#usernavdiv").show();
				 }
			 }
			);
			e.preventDefault();
		});
	});
	function switchuserType(){
		if($("#switchCP:checked").val()==null){
			userType=1;
			$("#plantdiv,#notifytimediv").hide();
		}else {
			userType=0;
			$("#plantdiv,#notifytimediv").show();
			
		}
		$("#userType").val(userType);
	}
	function getCodebutton() {
		var telnumber = $("#telinput").val().trim();
		if (null == telnumber || telnumber.length != 11 || isNaN(telnumber)) {
			$("#dialogmegdiv").text("请填写正确的手机号");
			$("#androidDialog2").fadeIn(200);
		} else {
			$("#getcodebutton").attr("disabled", "disabled");
			var openid = $("#openid").val();
			var time = 60;
			var t = setInterval(printTime, 1000);
			function printTime() {
				time = time - 1;

				$("#getcodebutton").text("重新发送(" + time + ")");

				if (time == 0) {
					clearInterval(t);
					$("#getcodebutton").text("获取验证码");
					$("#getcodebutton").removeAttr("disabled");
				}

			}
			$.post("sendVerificationCode", {
				telnumber : telnumber,
				openid : openid
			}, function(data, status) {
				if (status == "success") {
					

				}
			}

			);
		}
	};
	
	
</script>
</head>

<body>
	<div class="container" id="usernavdiv">
	<div class="page msg_success js_show">
    <div class="weui-msg">
        <div class="weui-msg__icon-area"><i class="weui-icon-success weui-icon_msg"></i></div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">注册成功</h2>
             <p class="weui-footer__links">
               		<a href="moveCar" class="weui-footer__link">移车通知</a>
                    <a href="javascript:void(0);" class="weui-footer__link">用户中心</a>
                </p>
        </div>
    </div>
</div>
	</div>
	<div class="container" id="userregisterdiv">
		<div class="weui-cells__title">注册</div>
		<form class="weui-cells weui-cells_form" action="registerUser" id="registerform">
			<input type="text" style="display:none" id="openid" value="${openid}" name="openid"/>
			<div class="weui-cell weui-cell_switch">
                <div class="weui-cell__bd">车主</div>
                <div class="weui-cell__ft">
                    <label for="switchCP" class="weui-switch-cp">
                        <input id="switchCP" class="weui-switch-cp__input" type="checkbox" checked="checked"  onclick="switchuserType()">
        				<input type="text" style="display:none" id="userType" name="userType"/>
                        <span class="weui-switch-cp__box"></span>
                    </label>
                    
                </div>
            </div>
		
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
						maxlength="5" placeholder="5位车牌号"
						id=plantNumber />
				</div>
			</div>

			<div class="weui-cell weui-cell_vcode">
				<div class="weui-cell__hd">
					<label class="weui-label">称谓</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入称谓"
						id="username" name="username" maxlength="10" />
				</div>
				<div class="weui-cell__bd">
					<select class="weui-select" name="sex">
						<option value="Male" selected="selected">先生</option>
						<option value="Female">女士</option>
					</select>
				</div>
			</div>


			<div class="weui-cell weui-cell-select weui-cell_select-before" id="notifytimediv">
				<div class="weui-cell__hd">
					<label class="weui-label">可通知时间</label>
				</div>
				<div class="weui-cell__hd">
					<select class="weui-select" name="notifyStart" id="notify_start">
						<option value="0">00</option>
						<option value="1">01</option>
						<option value="2">02</option>
						<option value="3">03</option>
						<option value="4">04</option>
						<option value="5">05</option>
						<option value="6">06</option>
						<option value="7">07</option>
						<option value="8" selected="selected">08</option>
						<option value="9">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
					</select>
				</div>
				<div class="weui-cell__hd">
					<select class="weui-select" name="notifyStop" id="notify_stop">
						<option value="0">00</option>
						<option value="1">01</option>
						<option value="2">02</option>
						<option value="3">03</option>
						<option value="4">04</option>
						<option value="5">05</option>
						<option value="6">06</option>
						<option value="7">07</option>
						<option value="8">08</option>
						<option value="9">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21" selected="selected">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
					</select>
				</div>
			</div>
			<div class="weui-cell weui-cell_vcode">
				<div class="weui-cell__hd">
					<label class="weui-label">手机号</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" pattern="[1]\d{10}"
						placeholder="请输入手机号码" id="telinput" maxlength="11" name="telephone"/>
				</div>
				<div class="weui-cell__ft">
					<button class="weui-vcode-btn" onclick="getCodebutton()"
						id="getcodebutton" type="button">获取验证码</button>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">验证码</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="tel" placeholder="验证码"
						id="verificationCode" name="verificationCode" maxlength="6"/>
				</div>
			</div>
			<div>
				<button class="weui-btn weui-btn_primary" type="submit" id="submitbtn">提交</button>
			</div>
		</form>
		<div id="dialogs">
			<div class="js_dialog" id="androidDialog2" style="display: none;">
				<div class="weui-mask"></div>
				<div class="weui-dialog weui-skin_android">
					<div class="weui-dialog__bd" id="dialogmegdiv"></div>
					<div class="weui-dialog__ft">
						<a href="javascript:;"
							class="weui-dialog__btn weui-dialog__btn_primary">确认</a>
					</div>
				</div>
			</div>
		</div>
		
	<div class="page loadmore js_show" id="js_show">
        <div class="weui-loadmore">
            <i class="weui-loading"></i>
            <span class="weui-loadmore__tips" id="js_show_msg">正在提交</span>
        </div>
	</div>
	</div>

</body>
</html>
