package com.ayning.web.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.patchca.color.RandomColorFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.Captcha;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.word.RandomWordFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ayning.exception.AyningException;
import com.ayning.service.UserService;
import com.ayning.vo.User;

@Controller
public class UserController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserController.class);

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

		LOGGER.debug("Checking email: " + email);
		User user = new User();
		user.setEmail(email);
		AjaxResponse res = new AjaxResponse();
		boolean result = this.userService.existEmail(user);
		res.setResult(true);
		res.setMsg(result);
		return res;
	}

	/**
	 * Check to see whether the nickname already exists
	 * 
	 * @param email
	 * @return An AjaxResponse object, the msg property of the object will be
	 *         set to String "true" if the nickname already exist, otherwise a
	 *         String "false" will be returned
	 */
	@RequestMapping("nameExist")
	@ResponseBody
	public AjaxResponse nicknameExist(
			@RequestParam(required = true, value = "nickname") String nickName) {
		LOGGER.debug("Checking nickName: " + nickName);
		User user = new User();
		user.setNickName(nickName);
		AjaxResponse res = new AjaxResponse();
		boolean result = this.userService.existNickName(user);
		res.setResult(true);
		res.setMsg(result);
		return res;
	}

	@RequestMapping("reg")
	public ModelAndView register(
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "psw_confirm", required = true) String psw_confirm,
			@RequestParam(value = "nickName", required = true) String nickName,
			@RequestParam(value = "sex", required = true) String sex,
			@RequestParam(value = "location", required = true) String location) {

		ModelAndView mav = new ModelAndView();

		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)
				|| StringUtils.isEmpty(psw_confirm)
				|| StringUtils.isEmpty(nickName) || StringUtils.isEmpty(sex)
				|| StringUtils.isEmpty(location)) {
			mav.setViewName("error");
			mav.addObject("msg", "注册信息不能为空");
			return mav;
		}

		if (!password.equals(psw_confirm)) {
			mav.setViewName("error");
			mav.addObject("msg", "两次输入的密码不匹配");
			return mav;
		}

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setNickName(nickName);
		user.setSex(sex.toCharArray()[0]);
		user.setLocation(location);

		try {
			this.userService.register(user);
			mav.setViewName("regJump");
		} catch (AyningException e) {
			mav.setViewName("error");
			mav.addObject("msg", "注册失败: " + e.getMessage());
		}

		return mav;
	}

	@RequestMapping("validCode")
	public void validateCode(HttpServletRequest request,
			HttpServletResponse response) {
		LOGGER.debug("refresh");
		ConfigurableCaptchaService captchService = new ConfigurableCaptchaService();

		// 颜色创建工厂,使用一定范围内的随机色
		RandomColorFactory colorFactory = new RandomColorFactory();
		captchService.setColorFactory(colorFactory);

		// 字体生成器
		RandomFontFactory fontFactory = new RandomFontFactory();
		fontFactory.setMaxSize(32);
		fontFactory.setMinSize(28);
		captchService.setFontFactory(fontFactory);

		RandomWordFactory wordFactory = new RandomWordFactory();
		wordFactory.setCharacters("abcdefghkmnpqstwxyz23456789");
		wordFactory.setMaxLength(7);
		wordFactory.setMinLength(5);
		captchService.setWordFactory(wordFactory);

		// 文字渲染器设置
		BestFitTextRenderer textRenderer = new BestFitTextRenderer();
		textRenderer.setBottomMargin(3);
		textRenderer.setTopMargin(3);
		captchService.setTextRenderer(textRenderer);

		// 验证码图片的大小
		captchService.setWidth(100);
		captchService.setHeight(36);

		Captcha captcha = captchService.getCaptcha();
		HttpSession session = request.getSession(true);
		response.setContentType("image/png");
		response.setHeader("cache", "no-cache");

		session.setAttribute("validCode", captcha.getChallenge());
		BufferedImage image = captcha.getImage();

		try {
			OutputStream os = response.getOutputStream();
			ImageIO.write(image, "png", os);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("checkValidCode")
	@ResponseBody
	public AjaxResponse checkValidCode(String input, HttpServletRequest request) {
		AjaxResponse response = new AjaxResponse();
		String validCode = (String) request.getSession().getAttribute(
				"validCode");
		if (input == null || "".equals(input)) {
			response.setMsg(false);
		} else {
			if (validCode.equalsIgnoreCase(input)) {
				response.setMsg(true);
			} else {
				response.setMsg(false);
			}
		}
		response.setResult(true);
		return response;
	}
}
