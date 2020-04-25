package com.mh.heartbeat.dbaccess.poc.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Custom methods of JPAPatientRepository execute DB operations with auto-commit = true, which is the default behavior of SQL Server.
 * To use transactions, we do not set the connection's auto-commit mode to false.
 * Instead, we tell Spring to start transactions. In turn, Spring will retrieve a connection, set its auto-commit mode to false,
 * and keep using this same connection until the transaction is completed (either with all changes saved/committed, or all changes rolled back).
 * <p>
 * The @Transactional annotation tells Spring when to start a transaction (by setting the connection's auto-commit mode to false).
 * <p>
 * All the methods of CrudRepository are transactional by default and autocommit mode is turned off.
 * All the methods of CrudRepository are annotated with @Transactional in implementation class by default at runtime.
 * <p>
 * NOTE: The Spring team's recommendation is that you only annotate concrete classes with the @Transactional annotation, as opposed to annotating interfaces.
 * From http://static.springsource.org/spring/docs/2.0.x/reference/transaction.html\
 * The Spring team's recommendation is that you only annotate concrete classes with the @Transactional annotation, as opposed to annotating interfaces.
 */
@Repository
public interface SpringDataJPAPatientRepository extends JpaRepository<Patient, Long> {

  //The query can be derived because we followed the naming convention of the domain object’s properties.
  Patient findByPatientName(String patientName);

  @Query(value = "SELECT p FROM Patient p WHERE p.patientNumber = :patientNumber")
  Patient findByVisitNumber(@Param("patientNumber") String patientNumber);

  @Query(value = "SELECT p FROM Patient p WHERE p.patientNumber = :patientNumber AND p.hospitalPatientNumber = :hospitalPatientNumber")
  Patient findByVisitNumberAndMRN(@Param("patientNumber")String patientNumber, @Param("hospitalPatientNumber")String hospitalPatientNumber);

  @Query(value = "SELECT COUNT(*) FROM hb_patient WHERE wardId = :wardId", nativeQuery = true)
  Integer findCount(@Param("wardId") Integer wardId);

  @Transactional // All user defined repository methods are read only by default.
  @Modifying(flushAutomatically=true, clearAutomatically=true)
  @Query("UPDATE Patient p SET p.patientName = :patientName WHERE p.patientId = :patientId")
  void updatePatientName(@Param("patientName") String patientName, @Param("patientId") Long patientId);

  @Query(value = "SELECT * FROM errorTable", nativeQuery = true)
  void throwException();

}