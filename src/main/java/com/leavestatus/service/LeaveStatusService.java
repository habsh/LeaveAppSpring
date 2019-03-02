package com.leavestatus.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leavestatus.model.LeaveStatus;
import com.leavestatus.repository.LeaveStatusRepository;
import com.leavestatus.repository.LeaveStatusSqlRepo;
import com.lms.models.LeaveDetails;

@Service

public class LeaveStatusService {
	
	@Autowired
private LeaveStatusRepository leaveStatusRepository;
	
	private List<LeaveStatus> leaveStatus = new ArrayList<>
	/*
	 * Out put simple Json Format 
	 */
	
	(Arrays.asList(new LeaveStatus(1234,1,new SimpleDateFormat("mm/dd/yyyy").parse("02/28/2019"), 
					new SimpleDateFormat("mm/dd/yyyy").parse("04/28/2019"),"sick", 
					"pending","personal",new SimpleDateFormat("mm/dd/yyyy").parse("02/15/2019"),
					"Approved for 10 days"));
		

	}


