package com.pheonix.vending.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.pheonix.vending.exception.VendingDataBaseException;
import com.pheonix.vending.model.Soda;
import com.pheonix.vending.service.dao.SodaDao;

@Service
public class SodaDaoImpl implements SodaDao {

	protected static final Log LOGGER = LogFactory.getLog(SodaDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	protected DataSource dataSource;

	@Override
	public void createSoda() throws VendingDataBaseException, Exception {
		try {
			String sql = "CREATE TABLE soda(id INTEGER, name VARCHAR(150), cost INTEGER,count INTEGER)";
			jdbcTemplate.execute(sql);
		} catch (DataAccessException ex) {
			throw new VendingDataBaseException(500, ex.getMessage(), "Database Access Error");
		} catch (Exception e) {
			throw new Exception("general exception");
		}
	}

	@Override
	public int addSoda(Soda soda) throws VendingDataBaseException, Exception {
		int count = 0;
		try {
			String sql = "INSERT INTO soda(id, name, cost, count) VALUES(?,?,?,?)";
			count = jdbcTemplate.update(sql, soda.getId(), soda.getName(), soda.getCost(), soda.getCount());
		} catch (DataAccessException ex) {
			throw new VendingDataBaseException(500, ex.getMessage(), "Database Access Error");
		} catch (Exception e) {
			throw new Exception("general exception");
		}
		return count;
	}
	
	@Override
	public int buySoda(int buyCount) throws VendingDataBaseException, Exception {
		int count = 0;
		try {
			String sql = "UPDATE soda SET count="+buyCount+" WHERE id=1";
			count = jdbcTemplate.update(sql);
		} catch (DataAccessException ex) {
			throw new VendingDataBaseException(500, ex.getMessage(), "Database Access Error");
		} catch (Exception e) {
			throw new Exception("general exception");
		}
		return count;
	}

	@Override
	public List<Soda> getSodaCount() throws VendingDataBaseException, Exception {
		List<Soda> sodas = new ArrayList<>();
		try {
			sodas = jdbcTemplate.query("SELECT * FROM soda", new RowMapper<Soda>() {
				public Soda mapRow(ResultSet rs, int arg1) throws SQLException {
					Soda soda = new Soda();
					soda.setId(rs.getInt("id"));
					soda.setName(rs.getString("name"));
					soda.setCost(rs.getInt("cost"));
					soda.setCount(rs.getInt("count"));
					return soda;
				}
			});

		} catch (DataAccessException ex) {
			throw new VendingDataBaseException(500, ex.getMessage(), "Database Access Error");
		} catch (Exception e) {
			throw new Exception("general exception");
		}
		return sodas;
	}

}
