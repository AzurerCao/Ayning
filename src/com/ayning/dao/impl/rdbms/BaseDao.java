package com.ayning.dao.impl.rdbms;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao {

	private JdbcTemplate jdbc;

	public JdbcTemplate getJdbc() {
		return jdbc;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}

}
