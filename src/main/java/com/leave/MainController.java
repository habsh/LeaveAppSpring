package com.leave;

import org.springframework.web.bind.annotation.RestController;

import com.leave.obj.Leave;
import com.leave.obj.LeaveErrors;
import com.leave.ApplyLeaveService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/add/{firstno}/{secondno}")
    public String addition(@PathVariable String firstno, @PathVariable String secondno) {
        int result = Integer.parseInt(firstno)+Integer.parseInt(secondno);
    	return String.valueOf(result);
    }

    /**
     * applyLeave
     * 
     * Rest method to validate and put through a leave request
     * @param leave - the Leave object to process
     * @return a list of errors, blank if valid and fully processed
     */
    @RequestMapping(value="/applyLeave", method = RequestMethod.POST)
    public LeaveErrors applyLeave(@RequestBody Leave leave) {

    	LeaveErrors errorList = ApplyLeaveService.validateLeave(leave);
    	errorList.addAllErrors(ApplyLeaveService.dbLeave(leave));
		    	
    	return errorList;
    }
}