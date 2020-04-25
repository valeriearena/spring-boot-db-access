package com.mh.heartbeat.dbaccess.poc.springdatajdbc;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SpringDataJDBCPatientRepository extends CrudRepository<Patient, Long> {

  @Transactional
  @Query("SELECT patientId,patientName,lastName,firstName,middleName,title,suffixName,dateOfBirth,gender,creationDate,wardId,active,patientNumber,hospitalPatientNumber,bedNumber,targetedDischargeTime,latestDischargeTime,alerts,lastUpdated,dischargeDestinationId,acuity,chiefComplaint,lastKnownWell,wardActivationDate,isConfidential FROM hb_patient WHERE patientName = :patientName")
  Patient findByPatientName(@Param("patientName") String patientName);
}
