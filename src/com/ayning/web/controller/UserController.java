package com.ayning.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ayning.service.UserService;
import com.ayning.vo.User;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Check to see whether the email already exists
	 * 
	 * @param email
	 * @return An AjaxResponse object, the msg property of the object will be
	 *         set to String "true" if the email already exist, otherwise a
	 *         String "false" will be returned
	 */
	@RequestMapping("emailExist")
	@ResponseBody
	public AjaxResponse emailExist(
			@RequestParam(required = true, value = "email") String email) {

		User user = new User();
		user.setEmail(email);
		AjaxResponse res = new AjaxResponse();
		boolean result = this.userService.existEmail(user);
		res.setResult(true);
		res.setMsg(result);
		return res;
	}

	public AjaxResponse nicknameExist(
			@RequestParam(required = true, value = "nickname") String nickName) {
		User user = new User();
		user.setNickName(nickName);
		AjaxResponse res = new AjaxResponse();
		boolean result = this.userService.existNickName(user);
		res.setResult(true);
		res.setMsg(result);
		return res;
	}
}
