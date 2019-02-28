package com.leave.services;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.leave.dtos.EmployeeDetailsDTO;
import com.leave.dtos.LeaveDetailsDTO;
import com.leave.exceptions.IncorrectLeaveBalanceException;
import com.leave.exceptions.LeaveDetailsNotFoundException;
import com.leave.exceptions.UserNotFoundException;
import com.leave.obj.Employee;
import com.leave.obj.Leave;
import com.leave.repositories.EmployeeRepository;
import com.leave.repositories.LeaveRepository;

@Service
@Qualifier(value="LeaveDetailsService")
public class LeaveDetailsService implements EmployeeDataService {

	private LeaveRepository leaveRepository;
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
		
		if(leave.getStatus().equals(AcceptanceEnum.approved.name())||
				leave.getStatus().equals(AcceptanceEnum.denied.name()))
			return null;
				
		Leave dbLeave = Optional.ofNullable(leaveRepository.findOne(leave.getLeaveId()))
				.orElseThrow(()-> new LeaveDetailsNotFoundException("Not leave details Available")); 

		Employee employee = Optional.ofNullable(employeeRepository.findOne(dbLeave.getEmployee().getEmpId()))
				.orElseThrow(()-> new UserNotFoundException("User not found in given leave details"));
		
		leave.setStatus(AcceptanceEnum.approved.name());
		leave.setLeaveBalance(employee.getLeaveBalance()-leave.getDays());
		
		if(leave.getLeaveBalance()<0)
			throw new IncorrectLeaveBalanceException("Not enough days");
			
		dbLeave.setLeaveStatus(leave.getStatus());
		dbLeave.setManagerComments(leave.getManagerCommnets());
		employee.setLeaveBalance(leave.getLeaveBalance());
		
		leaveRepository.save(dbLeave);
		employeeRepository.save(employee);
		
		return leave;
	}

	@Override
	public EmployeeDetailsDTO postLeaveDenied(LeaveDetailsDTO leave) {
		
		if(leave.getStatus().equals(AcceptanceEnum.approved.name())||
				leave.getStatus().equals(AcceptanceEnum.denied.name()))
			return null;
		
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

}

enum AcceptanceEnum{
	approved, denied, pending
}
