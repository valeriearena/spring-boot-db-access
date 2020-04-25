package com.mh.heartbeat.dbaccess.poc.springdatajdbc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJDBCAssignmentRepository extends CrudRepository<Assignment, Long> {

}
