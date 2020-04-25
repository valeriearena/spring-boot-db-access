package com.mh.heartbeat.dbaccess.poc.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataJPAAssignmentRepository extends JpaRepository<Assignment, Long> {

  Optional<Assignment> findById(Long id);

  @Query(value = "SELECT a FROM Assignment a LEFT JOIN FETCH a.user WHERE a.id = :id")
  Assignment findAssignmentById(@Param("id") Long id);


}
