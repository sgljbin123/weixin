$(function() {
			var date = new Date();
			date.setDate(date.getDate()-1);
			var month = date.getMonth() + 1;
			if (month < 10) {
				month = "0" + month;
			}
			document.getElementById("reportDate").value = date.getFullYear()+ "-" + month + "-" + date.getDate();
		});

		function checkSubmit() {
			var tags = ["input","textarea"];
			for(var j=0;j<tags.length;j++){
				var tagList = document.getElementsByTagName(tags[j]);
				for(var i=0;i<tagList.length;i++){
					if(tagList[i].className!="hidden"){
						if(tagList[i].value.length==0){
							alert("请填写完毕");
							tagList[i].focus();
							return false;
						}
					}
				}
			}
			var isNaNElementName = ["trade","systemTotal","failureTotal","profit"];
			for(var j=0;j<isNaNElementName.length;j++){
				var isNaNElementList = document.getElementsByName(isNaNElementName[j]);
				for(var i=0;i<isNaNElementList.length;i++){
					if (isNaN(isNaNElementList[i].value)) {
						alert("请填写数字");
						isNaNElementList[i].focus();
						return false;
					}
				}
			}
		};
		function clear(str) {  
		    str = str.replace(/,/g, "");//取消字符串中出现的所有逗号  
		    return str;  
		} 
		function updateUser(){
			var password = $("#inputpassword").val();
			var confirmPassword = $("#inputconfirmpassword").val();
			if(password.length==0||confirmPassword.length==0){
				$("#updateuserstatus").text("请输入密码");
				return;
			}
			if(password.length<8||confirmPassword.length<8){
				$("#updateuserstatus").text("密码长度不能小于8");
				return;
			}
			if(password!=confirmPassword){
				$("#updateuserstatus").text("密码不一致");
				return;
			}
			var sendEmail;
			if($("#sendEmail:checked").val()==null){
				sendEmail=1;
			}else {
				sendEmail=0;
			}
			$.post(
			 "updateUser",
			 {password:password,sendEmail:sendEmail,emailAddress:$("#emailaddress").val()},
			 function(data,status){
				 if(status=="success"){
					 $("#updateuserstatus").text(data);
					 setTimeout(function(){
						 $("#updateuserstatus").text("");
					 },1500); 
				 }
			 });
		};
		
		function clearupdateuserstatus(){
			$("#updateuserstatus").text("");
		}
		function toggleRadioCheck(){
			if($("#sendEmail").attr("checked")){
				$("#sendEmail").removeAttr('checked');
			}else {
				$("#sendEmail").attr("checked",'checked');
			}
		}
