package com.ayning.dao.impl.rdbms;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ayning.dao.UserDao;
import com.ayning.meta.AyningConstant;
import com.ayning.util.DBUtil;
import com.ayning.vo.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserDaoImpl.class);

	private static final String INSERT_USER = "INSERT INTO t_user(id, password, email, nickname, "
			+ "birthdate, sex, mobile, location, company, job, reputation, intro, register_time, last_signin_time, activated, valid) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT TIMESTAMP, CURRENT TIMESTAMP, ?, ?)";

	private static final String UPDATE_USER = "UPDATE t_user SET (birthdate, mobile, location, company, job,"
			+ "intro, activated) = (?, ?, ?, ?, ?, ?, ?, ) WHERE id=?";

	private static final String FIND_BY_EMAIL = "SELECT id, password, email, nickname, birthdate, sex, mobile, "
			+ "location, company, job, reputation, intro, register_time, last_signin_time, activated"
			+ " FROM t_user WHERE email=?";

	private static final String FIND_BY_NICKNAME = "SELECT id, password, email, nickname, birthdate, sex, mobile, "
			+ "location, company, job, reputation, intro, register_time, last_signin_time, activated"
			+ " FROM t_user WHERE nickname=?";

	private static final String DELETE_USER = "UPDATE t_user SET valid = '1' WHERE id=?";

	private static final String UPDATE_REPUTATION = "UPDATE t_user SET reputation = reputation + ? WHERE id = ?";

	private static final String UPDATE_PASSWORD = "UPDATE t_user SET password = ? WHERE id = ?";

	private static final String UPDATE_SIGNIN_TIME = "UPDATE t_user SET last_signin_time = CURRENT TIMESTAMP WHERE id = ?";

	private static final String COUNT_BY_EMAIL = "SELECT COUNT(*) FROM t_user WHERE email = ?";

	private static final String COUNT_BY_NAME = "SELECT COUNT(*) FROM t_user WHERE nickname = ?";

	@Override
	public String insert(User user) {
		String pk = DBUtil.generateUUID();
		String activated = user.isActivated() ? AyningConstant.STRING_TRUE
				: AyningConstant.STRING_FALSE;

		LOGGER.info(user.toString());

		this.getJdbc().update(
				INSERT_USER,
				new Object[] { pk, user.getPassword(), user.getEmail(),
						user.getNickName(), user.getBirthDate(),
						String.valueOf(user.getSex()), user.getMobile(),
						user.getLocation(), user.getCompany(), user.getJob(),
						user.getReputation(), user.getIntroduction(),
						activated, AyningConstant.STRING_TRUE });
		return pk;
	}

	@Override
	public boolean update(User user) {
		int num = this.getJdbc().update(
				UPDATE_USER,
				new Object[] { user.getBirthDate(), user.getMobile(),
						user.getLocation(), user.getCompany(), user.getJob(),
						user.isActivated() });
		if (num == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public User findByEmail(String email) {
		return this.getJdbc().queryForObject(FIND_BY_EMAIL, mapper,
				new Object[] { email });
	}

	@Override
	public void delete(User user) {
		this.getJdbc().update(DELETE_USER, new Object[] { user.getId() });
	}

	@Override
	public void updateReputation(int reputation, User user) {
		if (reputation == 0) {
			return;
		}
		this.getJdbc().update(UPDATE_REPUTATION,
				new Object[] { reputation, user.getId() });
	}

	@Override
	public void updatePassword(String password, User user) {
		this.getJdbc().update(UPDATE_PASSWORD,
				new Object[] { password, user.getId() });
	}

	@Override
	public void updateSigninTime(User user) {
		this.getJdbc()
				.update(UPDATE_SIGNIN_TIME, new Object[] { user.getId() });
	}

	private RowMapper<User> mapper = new RowMapper<User>() {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			user.setNickName(rs.getString("nickname"));
			user.setBirthDate(rs.getString("birthdate"));
			user.setSex(rs.getString("sex").charAt(0));
			user.setMobile(rs.getString("mobile"));
			user.setLocation(rs.getString("location"));
			user.setCompany(rs.getString("company"));
			user.setJob(rs.getString("job"));
			user.setReputation(rs.getInt("reputation"));
			user.setIntroduction(rs.getString("intro"));
			user.setRegisterTime(rs.getTimestamp("register_time"));
			user.setLastSignTime(rs.getTimestamp("last_signin_time"));
			if (rs.getString("activated").equals(AyningConstant.STRING_TRUE)) {
				user.setActivated(true);
			} else {
				user.setActivated(false);
			}
			return user;
		}
	};

	@Override
	public User findByNickName(String nickName) {
		return this.getJdbc().queryForObject(FIND_BY_NICKNAME, mapper,
				new Object[] { nickName });
	}

	@Override
	public int countByEmail(String email) {
		return this
				.getJdbc()
				.queryForObject(COUNT_BY_EMAIL, Integer.class,
						new Object[] { email }).intValue();
	}

	@Override
	public int countByName(String nickName) {
		return this
				.getJdbc()
				.queryForObject(COUNT_BY_NAME, Integer.class,
						new Object[] { nickName }).intValue();
	}
}
