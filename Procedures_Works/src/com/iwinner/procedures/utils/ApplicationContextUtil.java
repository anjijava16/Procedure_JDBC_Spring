package com.iwinner.procedures.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iwinner.procedures.spring.pro.EmployeeDaoImpl;
import com.iwinner.procedures.spring.pro.EmployeeProcedureDaoType2;

public class ApplicationContextUtil {
	public static ApplicationContext getContext() {
		ApplicationContext context = new ClassPathXmlApplicationContext("com//iwinner//procedures//utils//proceduresBeans.xml");
		return context;
	}
	public static void main(String[] args) {
		ApplicationContext context=getContext();
		//EmployeeDaoImpl employeeJDBCTemplate =(EmployeeDaoImpl)context.getBean("employeeJDBCTemplate");
	//	employeeJDBCTemplate.inserProc();
		//Employee emp=employeeJDBCTemplate.getEmployee(10);
		System.out.println("Ine");
		//System.out.println(emp);
		EmployeeProcedureDaoType2 employeeProceDao=(EmployeeProcedureDaoType2)context.getBean("employeeProcedureDao");
		//employeeProceDao.insertProcedure(1120, "TestingProc2","AnjiMS");
		employeeProceDao.selectID(14);
		//scannerDao
	}
}
