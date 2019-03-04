package com.leave;

import org.springframework.web.bind.annotation.RestController;

import com.leave.applyleave.service.ApplyLeaveService;
import com.leave.obj.Leave;
import com.leave.obj.LeaveErrors;
import com.leave.obj.LeaveRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public LeaveErrors applyLeave(@RequestBody LeaveRequest leave) {

    	LeaveErrors errorList = applyLeaveService.validateLeave(leave);
    	if (errorList.errorCount() == 0)
    		System.out.println("a");
    		errorList.addAllErrors(applyLeaveService.dbLeave(leave));
		    	
    	return errorList;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}