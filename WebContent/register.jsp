<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="pages/general.jsp"></jsp:include>
<script type="text/javascript" src="script/reg.js"></script>
<link rel="stylesheet" type="text/css" href="style/reg.css" />

<title>Learning</title>
</head>
<body>
	<jsp:include page="pages/head.jsp"></jsp:include>
	<form action="<%=request.getContextPath() %>/reg.action" class="vertical-form">
		<div id="register-div">
			<div class="form-row">
				<label>Email</label> <input type="text" id="email" name="email"><span class="field-alert"></span>
			</div>

			<div class="form-row">
				<label>密码</label> <input type="password" id="password" name="password"> <span class="field-alert"></span>
			</div>

			<div class="form-row">
				<label>确认密码</label> <input type="password" id="psw-confirm" name="psw_confirm"><span class="field-alert"></span>
			</div>

			<div class="form-row">
				<label>用户名</label> <input type="text" id="nickname" name="nickName"><span class="field-alert"></span>
			</div>

			<div class="form-row">
				<label>性别</label>
				<select id="sex" name="sex">
					<option value="m">男</option>
					<option value="f">女</option>
					<option value="u">保密</option>
				</select>
			</div>

			<div class="form-row">
				<label>所在地</label>
				<select id="location" name="location">
					<option value="上海">上海</option>
					<option value="深圳">深圳</option>
					<option value="北京">北京</option>
					<option value="广州">广州</option>
					<option value="南京">南京</option>
					<option value="杭州">杭州</option>
					<option value="成都">成都</option>
					<option value="苏州">苏州</option>
					<option value="香港">香港</option>
					<option value="天津">天津</option>
					<option value="西安">西安</option>
					<option value="重庆">重庆</option>
					<option value="哈尔冰">哈尔滨</option>
					<option value="武汉">武汉</option>
					<option value="长沙">长沙</option>
					<option value="国外">国外</option>
					<option value="其他">**其他**</option>
				</select>
			</div>

			<div class="form-row">
				<label>验证码</label>
				<input type="text" id="validCode" name="validCode">
				<img alt="please refresh" src="<%=request.getContextPath() %>/validCode.action" id="validImg">
				<span class="field-alert"><img alt="Correct" src="image/correct.png" id="correctCode"> </span>
				<span class="field-alert"><img alt="Error" src="image/error.png" id="errorCode"> </span>
			</div>
			
			<div class="form-row">
				<button id="reg-submit">确认注册</button>
			</div>
			
		</div>
	</form>
</body>
</html>