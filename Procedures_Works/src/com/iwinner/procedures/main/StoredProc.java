package com.iwinner.procedures.main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Date;
import java.util.Scanner;

import com.iwinner.procedures.utils.DbUtils;

public class StoredProc {

	private static Connection conn = null;
	private static CallableStatement cStmt = null;
	private static int ORACLE_CURSOR_TYPE = -10;

	public static Object proceduresProcessing() {

		try {
			conn = DbUtils.getConnection();
			// Pro_Select_DbUser_Info
			cStmt = conn.prepareCall("{ call insertDBUSER(?,?,?,?) }");
			Scanner sc = new Scanner(System.in);

			System.out.println("Enter UserID Here: ");
			Integer userID = sc.nextInt();

			System.out.println("Enter UserName Here: ");
			String userName = sc.next();

			System.out.println("Enter UpdatedName ");
			String updatedBy = sc.next();

			cStmt.setInt(1, userID);
			cStmt.setString(2, userName);
			cStmt.setString(3, updatedBy);
			cStmt.setDate(4, new java.sql.Date(new Date().getTime()));
			int updateValue = cStmt.executeUpdate();
			System.out.println("Updated Value " + updateValue);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// http://blog.harpoontech.com/search?updated-min=2013-01-01T00:00:00-08:00&updated-max=2014-01-01T00:00:00-08:00&max-results=2
	/*
	 * CREATE OR REPLACE PROCEDURE SELECT_USERS ( st_cursor OUT SYS_REFCURSOR )
	 * is BEGIN OPEN st_cursor FOR SELECT USER_ID, USERNAME,
	 * CREATED_BY,CREATED_DATE FROM DBUSER; end SELECT_USERS;
	 */public static Object selectProcedure() {

		try {
			conn = DbUtils.getConnection();
			// Pro_Select_DbUser_Info
			cStmt = conn.prepareCall("call SELECT_USERS(?)");
			/*
			 * cStmt.registerOutParameter(1, Types.INTEGER);
			 * cStmt.registerOutParameter(2, Types.VARCHAR);
			 * cStmt.registerOutParameter(3, Types.VARCHAR);
			 * cStmt.registerOutParameter(4, Types.DATE);
			 *//*
				 * boolean bs=cStmt.execute(); System.out.println(bs); ResultSet
				 * rs = (ResultSet) cStmt.getObject(1);
				 */
			cStmt.registerOutParameter(1, ORACLE_CURSOR_TYPE);
			cStmt.execute();
			ResultSet rs = (ResultSet) cStmt.getObject(1);
			while (rs.next()) {
				System.out.println(rs.getInt("USER_ID") + " "
						+ rs.getString("USERNAME") + "  "
						+ rs.getString("CREATED_BY") + "  "
						+ rs.getString("CREATED_DATE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	 public static Object selectEmployeeInfo(Integer empID){
		 
		 try {
			conn=DbUtils.getConnection();
			cStmt=conn.prepareCall("{call GETEMPLOYEEINFO(?,?,?)}");
			cStmt.registerOutParameter(1, Types.VARCHAR);
			cStmt.registerOutParameter(2, Types.INTEGER);
			cStmt.setInt(3, empID);
			int x=cStmt.executeUpdate();
			System.out.println("Test"+x);
			String userName=cStmt.getString(1);
			Integer userAge=cStmt.getInt(2);
			System.out.println(userName+"    "+userAge);
		} catch (Exception e) {
               e.printStackTrace();
		}
		 return null;
	 }
	public static void main(String[] args) {
		// proceduresProcessing();
		// selectProcedure();
		System.out.println("Full");
		selectEmployeeInfo(10);
	}
}