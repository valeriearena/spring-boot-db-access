package com.mh.heartbeat.dbaccess.poc.service;

import com.mh.heartbeat.dbaccess.poc.enums.AlertEnum;
import com.mh.heartbeat.dbaccess.poc.enums.ChiefComplaintEnum;
import com.mh.heartbeat.dbaccess.poc.springdatajpa.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SpringDataJPAService {

  @Autowired
  private SpringDataJPAPatientRepository springDataJPAPatientRepository;

  @Autowired
  private SpringDataJPAUserRepository springDataJPAUserRepository;

  @Autowired
  private SpringDataJPAAssignmentRepository springDataJPAAssignmentRepository;

  public void createNewPatient() {

    Patient patient = new Patient();
    patient.setPatientName("JPATest");
    patient.setLastName("JPATest");
    patient.setFirstName("JPATest");
    patient.setMiddleName("JPATest");
    patient.setTitle("JPATest");
    patient.setSuffixName("JPATest");
    patient.setGender(1);
    patient.setCreationDate(new Date());
    patient.setWardId(5);
    patient.setActive(true);
    patient.setPatientNumber("V9876");
    patient.setHospitalPatientNumber("M9876");
    patient.setTargetedDischargeTime(new Date());
    patient.setAlerts(AlertEnum.RED_ALERT);
    patient.setLastUpdated(new Date());
    patient.setDischargeDestinationId(-1);
    patient.setAcuity(true);
    patient.setChiefComplaint(ChiefComplaintEnum.SEVERE_HEADACHE);
    patient.setLastKnownWell(new Date());
    patient.setIsConfidential(false);

    patient = springDataJPAPatientRepository.save(patient);
    log.info("Created JPA patient {}", patient.getPatientId());
  }

  public void findPatientByName() {

    Patient patient = springDataJPAPatientRepository.findByPatientName("JPATest");
    log.info("Found JPA patient {}", patient.getPatientName());

  }

  public void updatePatientByDirtyCheckingNoTransaction() {

    Patient patient = springDataJPAPatientRepository.findByVisitNumber("V9876");
    patient.setPatientName("Update By Dirty Checking No Transaction");
    log.info("1st update - no transaction");

  }
  public void findPatientAfterDirtyCheckNoTransaction() {

    Patient patient = springDataJPAPatientRepository.findByVisitNumber("V9876");

    springDataJPAPatientRepository.flush();

    log.info("1st update - no transaction");

  }

  @Transactional
  public void updatePatientByDirtyCheckingInTransaction() {

    Patient patient = springDataJPAPatientRepository.findByVisitNumber("V9876");
    patient.setPatientName("Update By Dirty Checking Inside Transaction");
    log.info("2nd update - inside a transaction");

  }
  public void findPatientAfterDirtyCheckInTransaction() {

    Patient patient = springDataJPAPatientRepository.findByVisitNumber("V9876");
    log.info("2nd update - inside a transaction");

  }

  public void updatePatientByModifyingQuery() {

    Patient patient = springDataJPAPatientRepository.findByVisitNumber("V9876");
    springDataJPAPatientRepository.updatePatientName("Update By Query", patient.getPatientId());
    log.info("3rd update - JQL update inside a transaction");

  }
  public void findPatientAfterModifyingQuery() {

    Patient patient = springDataJPAPatientRepository.findByVisitNumber("V9876");
    log.info("3rd update - JQL update inside a transaction");

  }

  @Transactional
  public void deletePatient() {

    Patient patient = springDataJPAPatientRepository.findByVisitNumber("V9876");
    springDataJPAPatientRepository.delete(patient);
    log.info("Deleted JPA patient {}", patient.getPatientName());

  }

  @Transactional
  public void throwException(){

    try{
      springDataJPAPatientRepository.throwException();
    }
    catch (DataAccessException e){
      log.error(e.getMessage(), e);
    }
  }

  public void findPatientById() {

    Optional<Patient> optionalSpringDataJPAPatient= springDataJPAPatientRepository.findById(Long.valueOf(1));
    Patient patient = optionalSpringDataJPAPatient.get();
    log.info("Found JPA patient {}", patient.getPatientName());

  }

  public void findPatientByVisitNumber() {

    Patient patient = springDataJPAPatientRepository.findByVisitNumber("V8681164925716200501");
    log.info("Found JPA patient {}", patient.getPatientNumber());

  }

  public void findPatientByVisitNumberAndMRN() {

    Patient patient = springDataJPAPatientRepository.findByVisitNumberAndMRN("V8121461256639365911", "M7437734745283371814");
    log.info("Found JPA patient {} {}", patient.getPatientNumber(), patient.getHospitalPatientNumber());

  }

  public void findPatientCount() {

    Integer wardCount = springDataJPAPatientRepository.findCount(5);
    log.info("Found JPA patient count {}", wardCount);

    long patientCount = springDataJPAPatientRepository.count();
    log.info("Found JPA patient count {}", patientCount);

  }

  public void findAll(){

    List<Patient> patientList = springDataJPAPatientRepository.findAll();
    log.info("Found JPA patient list {}", patientList.size());

  }

  public void findUserById() {

    Optional<User> optionalSpringDataJPAUser = springDataJPAUserRepository.findById(Long.valueOf(5));
    User user = optionalSpringDataJPAUser.get();
    log.info("Found JPA user {}", user.getUserName());

  }

  public void findAssignmentByUserId() {

    Optional<Assignment> optionalJPAAssignment = springDataJPAAssignmentRepository.findById(Long.valueOf(5));
    Assignment assignment1 = optionalJPAAssignment.get();
    log.info("Found JPA assignment1 {}", assignment1.getUser().getUserName());

    Assignment assignment2 = springDataJPAAssignmentRepository.findAssignmentById(Long.valueOf(6));
    log.info("Found JPA assignment2 {}", assignment2.getUser().getUserName());

  }
}
