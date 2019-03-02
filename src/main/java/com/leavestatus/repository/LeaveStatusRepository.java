package com.leavestatus.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.leave.obj.Leave;
import com.leavestatus.model.LeaveStatus;

@Repository
public interface LeaveStatusRepository extends CrudRepository<LeaveStatus, Integer>{
	  @Query(value = "select * from leave_history where t.emp_id = ?1"
			  , nativeQuery = true)
	  List<LeaveStatus> findLeaveByAttribute(int attribute);
}

