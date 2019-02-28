package com.leave.services;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.leave.repositories.EmployeeRepository;
import com.leave.repositories.LeaveRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LeaveDetailsServiceTest {
	
	@MockBean
	LeaveRepository leaveRepository;
	@MockBean
	EmployeeRepository employeeRepository;
	
	@Autowired
	LeaveDetailsService leavedetailsService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
