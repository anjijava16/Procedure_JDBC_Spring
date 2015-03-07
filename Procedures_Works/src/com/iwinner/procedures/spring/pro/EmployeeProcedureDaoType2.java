package com.iwinner.procedures.spring.pro;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;

public class EmployeeProcedureDaoType2 {
	private static String procedureName = "insertDBUSER";
	private static String SELECT_ID_PRO="GETEMPLOYEEINFO";
	/** The jdbc template. */
	private JdbcTemplate jdbcTemplate;

	/**
	 * Instantiates a new scanner dao.
	 * 
	 * @param jdbcTemplate
	 *            the jdbc template
	 */
	public EmployeeProcedureDaoType2(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insertProcedure(Integer userID, String userName, String updateBy) {
		try {
			SqlParameter sqlParam[] = new SqlParameter[4];
			sqlParam[0] = new SqlParameter("p_userid", Types.NUMERIC);
			sqlParam[1] = new SqlParameter("p_username", Types.VARCHAR);
			sqlParam[2] = new SqlParameter("p_createdby", Types.VARCHAR);
			sqlParam[3] = new SqlParameter("p_date", Types.DATE);
			ExtractDataRows rows = new ExtractDataRows(
					this.jdbcTemplate.getDataSource(), procedureName, sqlParam);
			HashMap<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("p_userid", userID);
			inParamMap.put("p_username", userName);
			inParamMap.put("p_createdby", updateBy);// new java.sql.Date(new Date().getTime())
			inParamMap.put("p_date", new java.sql.Date(new Date().getTime()));
			Map results = rows.executeStoredProcedure(inParamMap);
			System.out.println(results.size());
		} catch (Exception e) {
           e.printStackTrace();
		}
	}

	public void selectID(Integer userID) {
		try {
			SqlParameter sqlParam[] = new SqlParameter[3];
			sqlParam[0] = new SqlParameter("EMP_ID", Types.NUMERIC);
			sqlParam[1] = new SqlOutParameter("EMP_NAME", Types.VARCHAR);
			sqlParam[2] = new SqlOutParameter("EMP_AGE", Types.NUMERIC);
			ExtractDataRows rows = new ExtractDataRows(this.jdbcTemplate.getDataSource(), "GETEMPLOYEEINFO", sqlParam);
			HashMap<String, Object> inputParams = new HashMap<String, Object>();
			inputParams.put("EMP_ID", userID);
			Map results = rows.executeStoredProcedure(inputParams);
			System.out.println("#####Result Here #################");
			System.out.println(results.get("EMP_NAME")+"  "+results.get("EMP_AGE"));
			System.out.println("###Result End Here ###############");
			System.out.println(results.size());
		} catch (Exception e) {
           e.printStackTrace();
		}

	}
}
