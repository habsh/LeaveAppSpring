package com.leave;

import org.springframework.web.bind.annotation.RestController;

import com.leave.applyleave.service.ApplyLeaveService;
import com.leave.obj.Leave;
import com.leave.obj.LeaveErrors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@CrossOrigin(origins = "*")
public class MainController {

	@Autowired
	ApplyLeaveService applyLeaveService;

    /**
     * applyLeave
     * 
     * Rest method to validate and put through a leave request
     * @param leave - the Leave object to process
     * @return a list of errors, blank if valid and fully processed
     */
    @RequestMapping(value="/applyLeave", method = RequestMethod.POST)
    public LeaveErrors applyLeave(@RequestBody Leave leave) {

    	LeaveErrors errorList = applyLeaveService.validateLeave(leave);
    	if (errorList.errorCount() == 0)
    		errorList.addAllErrors(applyLeaveService.dbLeave(leave));
		    	
    	return errorList;
    }
}