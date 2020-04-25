package com.mh.heartbeat.dbaccess.poc.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJPAUserRepository extends JpaRepository<User, Long> {

}
