package com.leave;

import org.springframework.web.bind.annotation.RestController;

import com.leave.applyleave.service.ApplyLeaveService;
import com.leave.dtos.LeaveDetailsDTO;
import com.leave.obj.LeaveErrors;
import com.leave.obj.LeaveRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
    		errorList.addAllErrors(applyLeaveService.dbLeave(leave));
		    	
    	return errorList;
    }
    @RequestMapping("/")
	public ResponseEntity homeStatus(){
		//System.out.println("response"+employeeDataService.getLeaveData(id));
		return new ResponseEntity("Greetings from Spring Boot!",
				HttpStatus.OK);
	}
    @RequestMapping("/add/{num1}/{num2}")
	public ResponseEntity addStatus(@PathVariable Integer num1,@PathVariable Integer num2){
		//System.out.println("response"+employeeDataService.getLeaveData(id));
		return new ResponseEntity(num1+num2,
				HttpStatus.OK);
	}
    
}