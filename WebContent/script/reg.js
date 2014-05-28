/**
 * 
 */

function isEmpty(str) {
	
	if (str == "") {
		return true;
	} else {
		return false;
	}
	
}

$(document).ready(function() {

	// if focus, clear the prompt information
	$("#register-div input").focus(function(){
		$(this).next().hide();
	});
	
	// check email
	$("#email").blur(
		function() {
			var email = $(this).val();

			if (email == "") {
				$(this).next().empty().append("邮箱不能为空").show();
				return;
			}

			var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			if (!regex.test(email)) {
				$(this).next().append("邮箱格式不正确").show();
				return;
			}
		});

	$("#password").blur(
			function() {
				var password = $(this).val();

				if (password == "") {
					$(this).next().empty().append("密码不能为空").show();
					return;
				}

			});


	$("#psw-confirm").blur(
			function() {
				var str = $(this).val();
				var pwd = $("#password").val();
				
				if (str == "") {
					$(this).next().empty().append("密码不能为空").show();
					return;
				}
				
				if (str != pwd) {
					$(this).next().empty().append("两次输入的密码不匹配").show();
					return;
				}

			});

	$("#nickname").blur(
			function() {
				var email = $(this).val();

				if (email == "") {
					$(this).next().empty().append("用户名不能为空").show();
					return;
				}

			});





});