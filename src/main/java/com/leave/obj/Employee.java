package com.leave.obj;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int empId;
	private String empName;
	private long empPhone;
	private String empDept;
	private String empMail;
	private Date empDoj;
	private int leaveBalance;
	private int empMngId;
	
	@OneToMany(mappedBy="employee",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Leave> leaves = new ArrayList<Leave>();

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public long getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(long empPhone) {
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

	public Date getEmpDoj() {
		return empDoj;
	}

	public void setEmpDoj(Date empDoj) {
		this.empDoj = empDoj;
	}

	public int getLeaveBalance() {
		return leaveBalance;
	}

	public void setLeaveBalance(int leaveBalance) {
		this.leaveBalance = leaveBalance;
	}

	public int getEmpMngId() {
		return empMngId;
	}

	public void setEmpMngId(int empMngId) {
		this.empMngId = empMngId;
	}

	public List<Leave> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Leave> leaves) {
		this.leaves = leaves;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empDept == null) ? 0 : empDept.hashCode());
		result = prime * result + ((empDoj == null) ? 0 : empDoj.hashCode());
		result = prime * result + empId;
		result = prime * result + ((empMail == null) ? 0 : empMail.hashCode());
		result = prime * result + empMngId;
		result = prime * result + ((empName == null) ? 0 : empName.hashCode());
		result = prime * result + (int) (empPhone ^ (empPhone >>> 32));
		result = prime * result + leaveBalance;
		result = prime * result + ((leaves == null) ? 0 : leaves.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (empDept == null) {
			if (other.empDept != null)
				return false;
		} else if (!empDept.equals(other.empDept))
			return false;
		if (empDoj == null) {
			if (other.empDoj != null)
				return false;
		} else if (!empDoj.equals(other.empDoj))
			return false;
		if (empId != other.empId)
			return false;
		if (empMail == null) {
			if (other.empMail != null)
				return false;
		} else if (!empMail.equals(other.empMail))
			return false;
		if (empMngId != other.empMngId)
			return false;
		if (empName == null) {
			if (other.empName != null)
				return false;
		} else if (!empName.equals(other.empName))
			return false;
		if (empPhone != other.empPhone)
			return false;
		if (leaveBalance != other.leaveBalance)
			return false;
		if (leaves == null) {
			if (other.leaves != null)
				return false;
		} else if (!leaves.equals(other.leaves))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empPhone=" + empPhone + ", empDept=" + empDept
				+ ", empMail=" + empMail + ", empDoj=" + empDoj + ", leaveBalance=" + leaveBalance + ", empMngId="
				+ empMngId + ", leaves=" + leaves + "]";
	}
	
	
}
