package com.iwinner.procedures.spring.pro;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.iwinner.procedures.dto.Employee;
import com.iwinner.procedures.main.EmployeeDao;

public class EmployeeDaoImpl implements EmployeeDao {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(String name, Integer age) {
		// TODO Auto-generated method stub

	}
/*
 *
	//#########CREATE OR REPLACE PROCEDURE GETEMPLOYEEINFO 
	   (EMP_NAME OUT VARCHAR,EMP_AGE OUT NUMBER,  EMP_ID IN NUMBER) AS
	BEGIN
	   SELECT NAME,AGE INTO EMP_NAME, EMP_AGE   FROM Employee e
	   WHERE e.ID = EMP_ID;
	END;
	
	*/
	
	public Employee getEmployee(Integer id) {
		try {
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplateObject).withProcedureName("GETEMPLOYEEINFO");

			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("EMP_ID", 10);
			SqlParameterSource in = new MapSqlParameterSource(inParamMap);

			Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
			
			System.out.println(simpleJdbcCallResult.get("EMP_NAME")+"  "+simpleJdbcCallResult.get("EMP_AGE"));
		} catch (Exception e) {
                 e.printStackTrace();
		}
		return null;
	}

	
	// Insert Procedure
	public void inserProc(){
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter UserID Here: ");
		Integer userID = sc.nextInt();

		System.out.println("Enter UserName Here: ");
		String userName = sc.next();

		System.out.println("Enter UpdatedName ");
		String updatedBy = sc.next();

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplateObject).withProcedureName("insertDBUSER");
		Map<Object, Object> inParamMap = new HashMap<Object, Object>();
		inParamMap.put("p_userid", userID);
		inParamMap.put("p_username", userName);
		inParamMap.put("p_createdby", updatedBy);//new java.sql.Date(new Date().getTime())
		inParamMap.put("p_date", new java.sql.Date(new Date().getTime()));
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		System.out.println(simpleJdbcCallResult.size());
		
	}
	public List<Employee> listEmployee() {
		return null;
	}

}
