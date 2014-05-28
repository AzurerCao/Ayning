package com.ayning.service.impl;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ayning.dao.UserDao;
import com.ayning.exception.AyningException;
import com.ayning.exception.InvalidUserException;
import com.ayning.service.UserService;
import com.ayning.util.SecurityUtil;
import com.ayning.vo.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public User activate(User user) {
		boolean activated = this.userDao.update(user);
		LOGGER.info("Activate user[" + user.getEmail() + "] result:"
				+ activated);
		if (activated) {
			user.setActivated(true);
		} else {
			user.setActivated(false);
		}
		return user;
	}

	@Override
	public User register(User user) throws AyningException {
		if (StringUtils.isEmpty(user.getEmail())) {
			throw new AyningException("Invalid email");
		}

		if (StringUtils.isEmpty(user.getPassword())) {
			throw new AyningException("Invalid password");
		}

		if (StringUtils.isEmpty(user.getNickName())) {
			throw new AyningException("Invalid nickname");
		}

		String encryption = null;
		try {
			encryption = SecurityUtil.encryptMD5(user.getPassword());
		} catch (NoSuchAlgorithmException e) {
			throw new AyningException("对密码进行加密错误:" + e.getMessage());
		}

		user.setPassword(encryption);
		String pk = this.userDao.insert(user);
		user.setId(pk);
		return user;
	}

	@Override
	public void auth(User user) throws AyningException {
		User u = this.userDao.findByEmail(user.getEmail());
		// verify user
		if (u == null) {
			throw new InvalidUserException("该用户不存在");
		}

		String encryption = null;
		try {
			encryption = SecurityUtil.encryptMD5(user.getPassword());
		} catch (NoSuchAlgorithmException e) {
			throw new AyningException("无法加密明文密码: " + e.getMessage());
		}

		// verify password
		if (encryption != u.getPassword()) {
			throw new InvalidUserException("密码不正确");
		}
	}

	@Override
	public User getUserInfo(User user) throws AyningException {
		return this.userDao.findByEmail(user.getEmail());
	}

	@Override
	public User signIn(User user) throws AyningException {
		if (StringUtils.isEmpty(user.getEmail())
				|| StringUtils.isEmpty(user.getPassword())) {
			throw new AyningException("用户名和密码不能为空");
		}

		// auth user
		this.auth(user);
		// initialize user information
		user = this.getUserInfo(user);

		// if every thing is OK, return user object
		return user;
	}

	@Override
	public void update(User user) {

	}

	@Override
	public void updatePassword(User user) {

	}

	@Override
	public void updateReputation(int reputation, User user)
			throws AyningException {

	}

	@Override
	public void delete(User user) throws AyningException {

	}

	@Override
	public boolean existEmail(User user) {
		User user2 = this.userDao.findByEmail(user.getEmail());
		if (user2 == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean existNickName(User user) {
		User user2 = this.userDao.findByNickName(user.getNickName());
		if (user2 == null) {
			return false;
		} else {
			return true;
		}
	}

}
