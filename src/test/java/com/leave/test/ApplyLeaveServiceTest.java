package com.leave.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.leave.MainController;
import com.leave.applyleave.service.ApplyLeaveService;
import com.leave.obj.Leave;
import com.leave.obj.LeaveErrors;

public class ApplyLeaveServiceTest {

	private MainController controller; 
	@Autowired
	ApplyLeaveService applyLeaveService;
	
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
		System.out.println("in Leave test - LeaveEmpty");
		LeaveErrors response = controller.applyLeave(new Leave());
		assertEquals("Leave Validation",2,response.errorCount());
		response.getErrors().forEach(System.out::println);
	}
	
	@Test
	public void testLeaveInvalidDates() {
		
		System.out.println("in Leave test - InvalidDates");
		
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
		
		testing.setNumDays(6);
		response = controller.applyLeave(testing);
		assertEquals("Successful validation",0,response.errorCount());
		response.getErrors().forEach(System.out::println);
	}
	
	@Test
	public void testLeaveDateOverlap() {
		
		System.out.println("in Leave test - DateOverlap");
		
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
		
		Leave testing1 = new Leave();
		testing1.setStartDate(date2);
		testing1.setEndDate(date1);
		
		Leave testing2 = new Leave();
		testing2.setStartDate(date4);
		testing2.setEndDate(date3);
		
		assertTrue("Dates overlap",applyLeaveService.checkOverlap(testing1,testing2));
		assertTrue("Dates overlap swap",applyLeaveService.checkOverlap(testing2,testing1));
		
		testing1.setStartDate(date2);
		testing1.setEndDate(date3);
		
		testing2.setStartDate(date4);
		testing2.setEndDate(date1);
		
		assertTrue("Dates inside",applyLeaveService.checkOverlap(testing1,testing2));
		assertTrue("Dates inside swap",applyLeaveService.checkOverlap(testing2,testing1));
		
		testing1.setStartDate(date2);
		testing1.setEndDate(date4);
		
		testing2.setStartDate(date1);
		testing2.setEndDate(date3);
		
		assertFalse("Dates do not overlap",applyLeaveService.checkOverlap(testing1,testing2));
		assertFalse("Dates do not overlap swap",applyLeaveService.checkOverlap(testing2,testing1));
		
	}
	
}
