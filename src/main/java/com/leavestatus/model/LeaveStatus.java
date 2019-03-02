package com.leavestatus.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LeaveStatus {
	private int leaveId;
	private int numOfDays;
	private Date startDate;
	private Date endDate;
	private String leaveType;
	private String leaveStatus;
	private String reason;
	private Date appliedOn;
	private String managerComment;

	public LeaveStatus(int leaveId, int numOfDays, Date startDate, Date endDate, String leaveType, String leaveStatus,
			String reason, Date appliedOn, String managerComment) {
		super();
		this.leaveId = leaveId;
		this.numOfDays = numOfDays;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveType = leaveType;
		this.leaveStatus = leaveStatus;
		this.reason = reason;
		this.appliedOn = appliedOn;
		this.managerComment = managerComment;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public int getNumOfDays() {
		return numOfDays;
	}

	public void setNumOfDays(int numOfDays) {
		this.numOfDays = numOfDays;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getAppliedOn() {
		return appliedOn;
	}

	public void setAppliedOn(Date appliedOn) {
		this.appliedOn = appliedOn;
	}

	public String getManagerComment() {
		return managerComment;
	}

	public void setManagerComment(String managerComment) {
		this.managerComment = managerComment;
	}
}
