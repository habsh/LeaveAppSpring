package com.hex.leaverequest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Proxy;



@Entity
@Table(name = "employee",
	uniqueConstraints=@UniqueConstraint(columnNames={"emp_id", "emp_mail"}))
//@NamedQuery(query="from Employee e", name="getEmployee")
@Proxy(lazy=false)
public class Employee implements Serializable {
	public Employee() {
		super();
	}
	
	public Employee(Integer empId, String empName, Integer empPhone, String empDept, String empMail, Date empDOJ,
			int leaveBalance, Integer empManagerId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empPhone = empPhone;
		this.empDept = empDept;
		this.empMail = empMail;
		this.empDOJ = empDOJ;
		this.leaveBalance = leaveBalance;
		this.empManagerId = empManagerId;
	}

	@Id
	//@GeneratedValue(generator="increment")
	@Column(name = "emp_id")
	Integer empId;

	@Column(name = "emp_name" )	
	String empName;
	
	@Column(name = "emp_phone" )
	Integer empPhone;
	
	@Column(name = "emp_dept" )	
	String empDept;

	@Column(name = "emp_mail" )		
	String empMail;
	
	@Column(name = "emp_doj" )	
	Date empDOJ;	
	
	@Column(name = "leave_balance" )	
	Integer leaveBalance;
	
	@Column(name = "emp_mng_id" )	
	Integer empManagerId;

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

	public Integer getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(Integer empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmpDept() {
		return empDept;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	public String getEmpMail() {
		return empMail;
	}

	public void setEmpMail(String empMail) {
		this.empMail = empMail;
	}

	public Date getEmpDOJ() {
		return empDOJ;
	}

	public void setEmpDOJ(Date empDOJ) {
		this.empDOJ = empDOJ;
	}

	public int getLeaveBalance() {
		return leaveBalance;
	}

	public void setLeaveBalance(int leaveBalance) {
		this.leaveBalance = leaveBalance;
	}

	public Integer getEmpManagerId() {
		return empManagerId;
	}

	public void setEmpManagerId(Integer empManagerId) {
		this.empManagerId = empManagerId;
	}
}
