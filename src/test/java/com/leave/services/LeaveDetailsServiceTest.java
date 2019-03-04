package com.leave.services;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leave.repositories.EmployeeRepository;
import com.leave.repositories.LeaveRepository;


@RunWith(SpringJUnit4ClassRunner.class)
public class LeaveDetailsServiceTest {
	
	@MockBean
	LeaveRepository leaveRepository;
	@MockBean
	EmployeeRepository employeeRepository;
	
	LeaveDetailsService leavedetailsService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() {
	}

}
