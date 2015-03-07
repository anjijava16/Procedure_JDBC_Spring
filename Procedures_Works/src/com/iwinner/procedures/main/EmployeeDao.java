package com.iwinner.procedures.main;

import java.util.List;

import javax.sql.DataSource;

import com.iwinner.procedures.dto.Employee;

public interface EmployeeDao {
	/**
	 * This is the method to be used to initialize database resources ie.
	 * connection.
	 */
	public void setDataSource(DataSource ds);

	/**
	 * This is the method to be used to create a record in the Student table.
	 */
	public void create(String name, Integer age);

	/**
	 * This is the method to be used to list down a record from the Student
	 * table corresponding to a passed student id.
	 */
	public Employee getEmployee(Integer id);

	/**
	 * This is the method to be used to list down all the records from the
	 * Student table.
	 */
	public List<Employee> listEmployee();
	
	public void inserProc();
}
