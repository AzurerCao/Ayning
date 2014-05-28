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
			throw new AyningException("��������м��ܴ���:" + e.getMessage());
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
			throw new InvalidUserException("���û�������");
		}

		String encryption = null;
		try {
			encryption = SecurityUtil.encryptMD5(user.getPassword());
		} catch (NoSuchAlgorithmException e) {
			throw new AyningException("�޷�������������: " + e.getMessage());
		}

		// verify password
		if (encryption != u.getPassword()) {
			throw new InvalidUserException("���벻��ȷ");
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
			throw new AyningException("�û��������벻��Ϊ��");
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
