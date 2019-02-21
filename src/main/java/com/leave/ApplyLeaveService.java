package com.leave;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.leave.obj.Leave;
import com.leave.obj.LeaveErrors;

public class ApplyLeaveService {
	
	 /**
     * dbLeave
     * 
	 * Does database validation of given leave before
	 * adding to database as needed
     * 
     * @param leave - the Leave object to be added to db
     * @return a list of errors, blank if valid
     */
    public static LeaveErrors dbLeave(Leave leave){
    	
    	LeaveErrors errorList = new LeaveErrors();

    	int emplDaysLeft;
    	int empID = 1;	//TODO get emp id
    	//query database for employee # of requested days left
    	emplDaysLeft = 50;	//TODO actual db request
    	//TODO if request fails
    	
    	/* TODO a lot of this rides on Employee object being created
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPADefaults");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		txn.begin();
		Leave test = em.find(Leave.class, empID);
		txn.commit();
		*/

		//make sure employee has enough days
    	if (leave.getNumDays() <= emplDaysLeft){
    		//get database for leaves from user
    		//make sure no overlaps between any of the leaves
    		//hoo boy TODO

    		//update employee # of requested days left
    		//add the new leave to database of leaves
    		//TODO have to coordinate with other teams for what
    		//the Leave object needs to contain in fields
    	}
    	else
    		errorList.addError("Error: Employee does not have enough days left");

    	return errorList;
    }
   
    /**
     * validateLeave
     * 
	 * Validates a leave object with the following business object
	 *  - start date and end date in future
	 *  - start date is before end date
	 *  - days of leave is equal to end date - start date
	 *  - leave type is "Earned Leave"
	 *  - leave reason is not empty
     * 
     * @param leave - the Leave object to be validated
     * @return a list of errors, blank if valid
     */
    public static LeaveErrors validateLeave(Leave leave){
    	
    	LeaveErrors errorList = new LeaveErrors();
    	
    	if (leave != null){
    		System.out.println("Making sure that leave Request was accessed with leave object : \n\t" + leave);
    		
    		Calendar cal = Calendar.getInstance();
    		Date start = leave.getStartDate();
    		Date end = leave.getEndDate();
    		int numDays = leave.getNumDays();
    		String leaveType = leave.getLeaveType();
    		
    		//validation of date and num days
    		if (start != null && end != null){
    			if (start.before(cal.getTime()))
    				errorList.addError("Error: Start date must be after today");
    			if (end.before(cal.getTime())) 
    				errorList.addError("Error: End date must be after today");
    			if (end.before(start))
    				errorList.addError("Error: Start date must be before End date");
    			
    			//only need to validate num days when date is valid
    			if (errorList.errorCount() == 0){
    				
	    			long diffInMillies = Math.abs(end.getTime() - start.getTime());
	    		    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;
	    		    	//adding 1 because leave requests are inclusive in days
	    			if (numDays != diff){
	    				errorList.addError("Error: Number of leave days is incorrect, should be : " + diff);
	    			}
	    			else{	//all validation logic succeeded
	    				leave.setAppliedOn(cal.getTime());
	    				leave.setLeaveStatus("PENDING");
	    			}
    			}
    		}
    		else{
    			errorList.addError("Error: Date field(s) are empty");
    		}
    		
    		//validation of leave type
    		if (leaveType != null && !leaveType.isEmpty()){
    			if (!leaveType.equals("Earned Leave"))
    				errorList.addError("Error: Leave reason is invalid");
    		}
    		else{
    			errorList.addError("Error: Leave type is empty");
    		}
    			
    		//leave reason is allowed to be empty
		}
    	else{
			errorList.addError("Error: Leave object is null");
		}
    	return errorList;
    }
}
