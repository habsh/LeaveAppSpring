package com.leave.dtos;

public class EmployeeDetailsDTO {
	private int employeeId;
	String employeeName;
	private int leaveBalance;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getEmployeeId();
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		result = prime * result + getLeaveBalance();
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
		EmployeeDetailsDTO other = (EmployeeDetailsDTO) obj;
		if (getEmployeeId() != other.getEmployeeId())
			return false;
		if (employeeName == null) {
			if (other.employeeName != null)
				return false;
		} else if (!employeeName.equals(other.employeeName))
			return false;
		if (getLeaveBalance() != other.getLeaveBalance())
			return false;
		return true;
	}
	public int getLeaveBalance() {
		return leaveBalance;
	}
	public void setLeaveBalance(int leaveBalance) {
		this.leaveBalance = leaveBalance;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	
}
