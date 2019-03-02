package com.leavestatus.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leavestatus.model.LeaveStatus;
import com.leavestatus.service.LeaveStatusService;
@RestController
public class LeaveStatusController {
	@Autowired
	private LeaveStatusService leaveStatusService;
	
	/*
	 * list all leaves status 
	 */
	@RequestMapping("leaveStatus")
	public List<LeaveStatus> getAllLeaveStatuses() {
		return leaveStatusService.getAllLeaveStatus() ;	
		 
	}
}



