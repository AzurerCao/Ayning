/**
 * register.jsp
 */

// check email
function checkEmail() {

	var $email = $("#email");
	var email = $email.val();

	if (email == "") {
		$email.next().empty().append("邮箱不能为空").show();
		return false;
	}

	var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (!regex.test(email)) {
		$email.next().empty().append("邮箱格式不正确").show();
		return false;
	}
	
	var exist = false;
	$.ajax({
		url: "emailExist.action",
		async: false,
		data: {
			email: email
		},
		method: "GET",
		dataType: "JSON",
		success: function(data) {
			if (data.result == true && data.msg == true) {
				$email.next().empty().append("该邮箱已被注册, 请使用其他邮箱").show();
				exist = true;
			}
		}
	});
	// end ajax
	
	if (exist == true) {
		return false;
	}
	
	return true;
}

// check password
function checkPsw() {
	
	var $psw = $("#password");
	var password = $psw.val();

	if (password == "") {
		$psw.next().empty().append("密码不能为空").show();
		return false;
	}
	
	return true;
}

// check confirming password
function checkConfirm() {
	var $confirm = $("#psw-confirm");
	var str = $confirm.val(), pwd = $("#password").val();
	
	if (str == "") {
		$confirm.next().empty().append("密码不能为空").show();
		return false;
	}
	
	if (str != pwd) {
		$confirm.next().empty().append("两次输入的密码不匹配").show();
		return false;
	}
	
	return true;
}

//
function checkName() {
	var $nickname = $("#nickname");
	var nick = $nickname.val();

	// can not be empty
	if (nick == "") {
		$nickname.next().empty().append("用户名不能为空").show();
		return false;
	}
	
	// can not be duplicated
	var exist = false;
	$.ajax({
		url: "nameExist.action",
		async: false,
		data: {
			nickname: nick
		},
		method: "GET",
		dataType: "JSON",
		success: function(data) {
			if (data.result == true && data.msg == true) {
				$nickname.next().empty().append("该用户名已被注册, 请使用其他用户名").show();
				exist = true;
			}
		}
	});
	// end ajax
	
	if (exist == true) {
		return false;
	}
	return true;
}

function checkValidCode() {
	var input = $("#validCode").val();
	if (input == "") {
		return false;
	}
	var result = null;
	$.ajax({
		url: "checkValidCode.action",
		data: {
			input: input
		},
		dataType: "JSON",
		method: "GET",
		cache: false,
		async: false,
		success: function(data) {
			console.log(data);
			var $flag = null;
			if (data.result == true) {
				if (data.msg == true) {
					$flag = $("#correctCode").parent("span");
				}else {
					$flag = $("#errorCode").parent("span");
				}
				result = data.msg;
				$flag.show();
			} else {
				console.log("Fails to check validation code");
				alert("Fails to check validation code");
				result = false;
			}
			
		}
	});
	
	return result;
}

$(document).ready(function() {

	// if focus, clear the prompt information
	$("#register-div input").focus(function(){
		$(this).nextAll(".field-alert").hide();
	});
	
	// Fields validation
	$("#email").blur(function(){
		return checkEmail();
	});
	$("#password").blur(function(){
		return checkPsw();
	});
	$("#psw-confirm").blur(function(){
		return checkConfirm();
	});
	$("#nickname").blur(function(){
		return checkName();
	});
	
	// validate code
	$("#validCode").blur(function(){
		return checkValidCode();
	});

	// validate fields before submitting
	$("#reg-submit").click(function() {
		// login AND, Submit is allowed only when all of the fields are valid
		return (checkEmail() && checkPsw() 
				&& checkConfirm() && checkName() && checkValidCode());
	});

	$("#validImg").on("click", function(){
		$("#validImg").attr("src", "/ayning/validCode.action?" + new Date().getTime());
	});

});