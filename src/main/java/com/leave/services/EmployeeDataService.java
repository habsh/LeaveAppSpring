package com.leave.services;

import com.leave.dtos.EmployeeDetailsDTO;
import com.leave.dtos.LeaveDetailsDTO;

public interface EmployeeDataService {
	
	EmployeeDetailsDTO getEmployeeData(Integer id);
	EmployeeDetailsDTO postLeaveAccepted(LeaveDetailsDTO leave);
	EmployeeDetailsDTO postLeaveDenied(LeaveDetailsDTO leave);
}
