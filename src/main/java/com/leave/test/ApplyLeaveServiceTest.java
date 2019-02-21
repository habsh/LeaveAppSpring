package com.leave.test;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.leave.MainController;
import com.leave.obj.Leave;
import com.leave.obj.LeaveErrors;

public class ApplyLeaveServiceTest {

	private MainController controller; 
	
	@Before
	public void instantiateController(){
		controller = new MainController();
	}
	
	@Test
	public void testLeaveNull() {
		LeaveErrors response = controller.applyLeave(null);
		assertEquals("Null Leave Check",1,response.getErrors().size());
	}

	@Test
	public void testLeaveEmpty() {
		
		//date fields, and leave type empty
		System.out.println("in testLeaveEmpty");
		LeaveErrors response = controller.applyLeave(new Leave());
		assertEquals("Leave Validation",2,response.errorCount());
		response.getErrors().forEach(System.out::println);
	}
	
	@Test
	public void testLeaveInvalidDates() {
		
		System.out.println("in testInvalidDates");
		
		//date fields, and leave type empty
		Calendar cal = Calendar.getInstance();
		cal.set(2019,2,15);
		Date date1 = cal.getTime();
		
		cal.set(2019,0,15);
		Date date2 = cal.getTime();
		
		cal.set(2019,2,20);
		Date date3 = cal.getTime();
		
		cal.set(2019,1,15);
		Date date4 = cal.getTime();
		
		Leave testing = new Leave();
		testing.setLeaveType("Earned Leave");
		testing.setStartDate(date2);
		testing.setEndDate(date4);
		
		LeaveErrors response = controller.applyLeave(testing);
		assertEquals("Dates must be after today",2,response.errorCount());
		response.getErrors().forEach(System.out::println);
		
		testing.setStartDate(date1);
		response = controller.applyLeave(testing);
		assertEquals("Start must be before end date",2,response.errorCount());
		response.getErrors().forEach(System.out::println);
		
		testing.setEndDate(date3);
		response = controller.applyLeave(testing);
		assertEquals("Incorrect num of days",1,response.errorCount());
		response.getErrors().forEach(System.out::println);
		
		testing.setNumDays(5);
		response = controller.applyLeave(testing);
		assertEquals("Successful validation",0,response.errorCount());
		response.getErrors().forEach(System.out::println);
	}
	
}
