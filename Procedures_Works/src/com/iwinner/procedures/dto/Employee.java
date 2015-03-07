package com.iwinner.procedures.dto;

import java.util.Date;

public class Employee {

	private Integer empId;
	private String empName;
	private Integer empAge;
	private Date empProcessingDate;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Integer getEmpAge() {
		return empAge;
	}

	public void setEmpAge(Integer empAge) {
		this.empAge = empAge;
	}

	public Date getEmpProcessingDate() {
		return empProcessingDate;
	}

	public void setEmpProcessingDate(Date empProcessingDate) {
		this.empProcessingDate = empProcessingDate;
	}

}
