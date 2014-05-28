<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="script/jquery-1.8.3.js"></script>
<script type="text/javascript" src="script/reg.js"></script>
<link rel="stylesheet" type="text/css" href="style/reg.css" />

<title>Learning</title>
</head>
<body>
	<jsp:include page="index.jsp"></jsp:include>
	<form action="" class="vertical-form">
		<div id="register-div">
			<div class="form-row">
				<label>Email</label> <input type="text" id="email"><span class="field-alert"></span>
			</div>

			<div class="form-row">
				<label>密码</label> <input type="password" id="password"> <span class="field-alert"></span>
			</div>

			<div class="form-row">
				<label>确认密码</label> <input type="password" id="psw-confirm"><span class="field-alert"></span>
			</div>

			<div class="form-row">
				<label>用户名</label> <input type="text" id="nickname"><span class="field-alert"></span>
			</div>

			<div class="form-row">
				<label>性别</label>
				<select>
					<option>男</option>
					<option>女</option>
					<option>保密</option>
				</select>
			</div>

			<div class="form-row">
				<label>所在地</label>
				<select>
					<option value="">上海</option>
					<option value="">深圳</option>
					<option value="">北京</option>
					<option value="">广州</option>
					<option value="">南京</option>
					<option value="">杭州</option>
					<option value="">成都</option>
					<option value="">苏州</option>
					<option value="">香港</option>
					<option value="">天津</option>
					<option value="">西安</option>
					<option value="">重庆</option>
					<option value="">哈尔滨</option>
					<option value="">武汉</option>
					<option value="">长沙</option>
					<option value="">国外</option>
					<option value="">**其他**</option>
				</select>
			</div>

			<div class="form-row">
				<button id="reg-submit">确认注册</button>
			</div>
			
		</div>
	</form>
</body>
</html>