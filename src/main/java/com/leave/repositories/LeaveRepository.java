package com.leave.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leave.obj.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer>{
}
