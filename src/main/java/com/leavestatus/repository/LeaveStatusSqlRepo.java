package com.leavestatus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.leavestatus.model.LeaveStatus;

public class LeaveStatusSqlRepo {
	 @PersistenceContext
	    EntityManager entityManager;
	    @SuppressWarnings("unchecked")
	    public List<LeaveStatus> getAllLeaveStatus(StringBuffer whereQuery) {

		Query query = entityManager.createNativeQuery("select * from leave_details where " + whereQuery,
			LeaveStatus.class);

		return query.getResultList();
	    }
}
