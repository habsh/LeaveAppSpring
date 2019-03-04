package com.leave.services;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.stereotype.Service;

import com.leave.dtos.EmployeeDetailsDTO;
import com.leave.dtos.LeaveDetailsDTO;
import com.leave.exceptions.LeaveDetailsNotFoundException;
import com.leave.exceptions.UserNotFoundException;
import com.leave.obj.Employee;
import com.leave.obj.Leave;
import com.leave.obj.LeaveOne;
import com.leave.repositories.EmployeeRepository;
import com.leave.repositories.LeaveRepository;
import com.leave.repositories.LeaveRepositoryOne;

@Service
@Qualifier(value="LeaveDetailsService")
public class LeaveDetailsService implements EmployeeDataService {
	
	private LeaveRepository leaveRepository;
	private LeaveRepositoryOne leaveRepositoryOne;
	private EmployeeRepository employeeRepository;
	
	public LeaveDetailsService(LeaveRepository leaveRepository, EmployeeRepository employeeRepository) {
		this.leaveRepository = leaveRepository;
		this.employeeRepository = employeeRepository;
	}

	@Override
	public EmployeeDetailsDTO getEmployeeData(Integer id) {
		Leave leave = Optional.ofNullable(leaveRepository.findOne(id))
				.orElseThrow(()-> new LeaveDetailsNotFoundException("Not leave details Available")); 

		Employee employee = Optional.ofNullable(employeeRepository.findOne
					(
						Optional.ofNullable(leave.getEmployee())
						.orElseThrow(()-> new UserNotFoundException("Not employee available")).getEmpId() 	
						)
				)
				.orElseThrow(()-> new UserNotFoundException("Not employee available")); 

		LeaveDetailsDTO leaveDetailsDTO = new LeaveDetailsDTO();
		
		leaveDetailsDTO.setLeaveId(leave.getLeaveID());
		leaveDetailsDTO.setEmployeeId(employee.getEmpId());
		leaveDetailsDTO.setEmployeeName(Optional.ofNullable(employee.getEmpName()).orElse(" "));
		leaveDetailsDTO.setEndDate(Optional.ofNullable(leave.getEndDate()).orElse(Date.from(Instant.now())).toString());
		leaveDetailsDTO.setLeaveBalance(Optional.ofNullable(employee.getLeaveBalance()).orElse(0));
		leaveDetailsDTO.setReason(Optional.ofNullable(leave.getReasons()).orElse(" "));
		leaveDetailsDTO.setDays(Optional.ofNullable(leave.getNumDays()).orElse(0));
		leaveDetailsDTO.setLeaveType(Optional.ofNullable(leave.getLeaveType()).orElse(" "));
		
		if(Optional.ofNullable(leave.getEndDate()).isPresent())
			leaveDetailsDTO.setEndDate(new SimpleDateFormat("MM/dd/yyyy").format(leave.getEndDate()).toString());
		else
			leaveDetailsDTO.setEndDate(" ");
			

		if(Optional.ofNullable(leave.getStartDate()).isPresent())
			leaveDetailsDTO.setStartDate(new SimpleDateFormat("MM/dd/yyyy").format(leave.getStartDate()).toString());
		else
			leaveDetailsDTO.setStartDate(" ");
		
		leaveDetailsDTO.setStatus(Optional.ofNullable(leave.getLeaveStatus()).orElse(" "));
		leaveDetailsDTO.setManagerCommnets(Optional.ofNullable(leave.getManagerComments()).orElse(" "));
		
		return leaveDetailsDTO;
	}

	@Override
	public EmployeeDetailsDTO postLeaveAccepted(LeaveDetailsDTO leave) {
		Leave dbLeave = Optional.ofNullable(leaveRepository.findOne(leave.getLeaveId()))
				.orElseThrow(()-> new LeaveDetailsNotFoundException("Not leave details Available")); 
		
		leave.setStatus(AcceptanceEnum.accepted.name());
		dbLeave.setLeaveStatus(leave.getStatus());
		
		leaveRepository.save(dbLeave);
		
		return leave;
	}

	@Override
	public EmployeeDetailsDTO postLeaveDenied(LeaveDetailsDTO leave) {
		Leave dbLeave = Optional.ofNullable(leaveRepository.findOne(leave.getLeaveId()))
				.orElseThrow(()-> new LeaveDetailsNotFoundException("Not leave details Available")); 
		
		leave.setStatus(AcceptanceEnum.denied.name());
		dbLeave.setLeaveStatus(leave.getStatus());
		
		leaveRepository.save(dbLeave);
		
		return leave;
	}
	
	@Override
	public Leave postAddLeave(Leave leave) {
		
		leave.setLeaveStatus(AcceptanceEnum.pending.name());
		
		leaveRepository.save(leave);
		
		return leave;
	}
	
	@Override
	public Employee postUpdateEmployee(Integer id, Integer newTime) {
		Employee employee = Optional.ofNullable(employeeRepository.findOne
				(
					Optional.ofNullable(id)
					.orElseThrow(()-> new UserNotFoundException("Not employee available"))
					)
			)
			.orElseThrow(()-> new UserNotFoundException("Not employee available")); 
		employee.setLeaveBalance(newTime);
		employeeRepository.save(employee);
		return employee;
	}
	
	@Override
	public List<Leave> getLeaveData(Integer id) {
		
		List<Leave> leaves = Optional.ofNullable(leaveRepository.findLeaveByAttribute(id))
				.orElseThrow(()-> new LeaveDetailsNotFoundException("Not leave details Available")); 
	
		return leaves;
	}
	@Override
	public List<LeaveOne> getLeaveDataOne(Integer id) {
		//List<LeaveOne> leaves = leaveRepositoryOne.findLeaveByAttribute(id); 
		//return leaves;
		
		Configuration config = new Configuration();
        SessionFactory factory = new Configuration().configure(
				"/com/leave/hibernate.cfg.xml")
				.buildSessionFactory();
		 org.hibernate.Session sess = factory.openSession();
		try{
		     List <LeaveOne>products ;
		    org.hibernate.Transaction tx = sess.beginTransaction();
		    //products = sess.createSQLQuery("SELECT * FROM leave_history where emp_id="+id).list();
		    products = sess.createSQLQuery("SELECT * FROM leave_history where emp_id="+id).addEntity(LeaveOne.class).list();
		    if(products.size() > 0)
		    {
		        return products;
		    	//return (LeaveOne)products.get(0);
		    	//for (int i = 0; i < products.size(); i++) {
		    		
		    	//	Object[] stringValues = (Object[]) products.get(i);
		    		
		    	//}
		    }
		    	return null;  
		    }
	    catch(Exception e)
	    {
	        throw e;
	    }
		 
	}
	
	public EmployeeDetailsDTO getEmployeeDataById(Integer id) {
		Employee employee = Optional.ofNullable(employeeRepository.findOne(id))
				.orElseThrow(()-> new UserNotFoundException("Employee not found")); 

		EmployeeDetailsDTO toReturn = new EmployeeDetailsDTO();
		toReturn.setEmployeeId(employee.getEmpId());
		toReturn.setEmployeeName(employee.getEmpName());
		toReturn.setLeaveBalance(employee.getLeaveBalance());
		return toReturn;
	}

}

enum AcceptanceEnum{
	accepted, denied, pending
}
