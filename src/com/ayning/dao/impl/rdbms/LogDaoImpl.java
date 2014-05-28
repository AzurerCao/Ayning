package com.ayning.dao.impl.rdbms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ayning.dao.LogDao;
import com.ayning.meta.AyningConstant;
import com.ayning.vo.OperationLog;
import com.ayning.vo.User;

public class LogDaoImpl extends BaseDao implements LogDao {

	private static final String INSERT_LOG = "INSERT INTO T_LOG(user_id, operate_time, ip_addr, host_name, operate_des, result, method) "
			+ "VALUES(?, CURRENT TIMESTAMP, ?, ?, ?, ?, ?)";

	private static final String FIND_BY_USER = "SELECT id, user_id, operate_time, ip_addr, host_name, operate_des, "
			+ "result, method FROM t_log WHERE user_id=?";

	private static final String FIND_BY_TIME = "SELECT id, user_id, operate_time, ip_addr, host_name, operate_des, "
			+ "result, method FROM t_log WHERE ";

	@Override
	public int insert(final OperationLog log) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbc().update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement ps = con.prepareStatement(INSERT_LOG,
						new String[] { "id" });
				String result = AyningConstant.STRING_FALSE;
				if (log.isResult()) {
					result = AyningConstant.STRING_TRUE;
				}
				ps.setString(1, log.getUserID());
				ps.setString(2, log.getIpAddr());
				ps.setString(3, log.getHostName());
				ps.setString(4, log.getDescription());
				ps.setString(5, result);
				ps.setString(6, log.getMethod());
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();
	}

	@Override
	public List<OperationLog> queryByUser(User user) {
		return this.getJdbc().query(FIND_BY_USER, mapper, user.getId());
	}

	@Override
	public List<OperationLog> queryByTime(Timestamp start, Timestamp end) {
		if (start == null && end == null) {
			throw new IllegalArgumentException(
					"At least one parameter should not be null");
		}
		StringBuilder sb = new StringBuilder(FIND_BY_TIME);
		List<Timestamp> ts = new ArrayList<Timestamp>();
		if (start != null) {
			ts.add(start);
			sb.append("operate_time >= ?");
		}

		if (end != null) {
			ts.add(end);
			sb.append(" AND operate_time < ?");
		}

		return this.getJdbc().query(sb.toString(), mapper,
				ts.toArray(new Object[ts.size()]));
	}

	/**
	 * RowMapper inner class
	 */
	private RowMapper<OperationLog> mapper = new RowMapper<OperationLog>() {

		@Override
		public OperationLog mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			OperationLog log = new OperationLog();
			log.setId(rs.getInt("id"));
			log.setUserID(rs.getString("user_id"));
			log.setOperateTime(rs.getTimestamp("operate_time"));
			log.setIpAddr(rs.getString("ip_addr"));
			log.setHostName(rs.getString("host_name"));
			log.setDescription(rs.getString("operate_des"));
			if (rs.getString("result").equals(AyningConstant.STRING_TRUE)) {
				log.setResult(true);
			} else {
				log.setResult(false);
			}
			log.setMethod(rs.getString("method"));
			return log;
		}

	};
}
