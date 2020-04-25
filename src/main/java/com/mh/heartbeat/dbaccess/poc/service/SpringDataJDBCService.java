package com.mh.heartbeat.dbaccess.poc.service;

import com.mh.heartbeat.dbaccess.poc.springdatajdbc.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class SpringDataJDBCService {

  @Autowired
  private SpringDataJDBCPatientRepository springDataJDBCPatientRepository;

  @Autowired
  private SpringDataJDBCUserRepository springDataJDBCUserRepository;

  @Autowired
  private SpringDataJDBCAssignmentRepository springDataJDBCAssignmentRepository;

  public void createNewPatient() {

    Patient patient = new Patient();
    patient.setPatientName("SpringDateJDBCTest");
    patient.setLastName("SpringDateJDBCTest");
    patient.setFirstName("SpringDateJDBCTest");
    patient.setMiddleName("SpringDateJDBCTest");
    patient.setTitle("SpringDateJDBCTest");
    patient.setSuffixName("SpringDateJDBCTest");
    patient.setGender(0);
    patient.setCreationDate(new Date());
    patient.setWardId(5);
    patient.setActive(true);
    patient.setPatientNumber("V1234");
    patient.setHospitalPatientNumber("M1234");
    patient.setTargetedDischargeTime(new Date());
    patient.setAlerts("");
    patient.setLastUpdated(new Date());
    patient.setDischargeDestinationId(-1);
    patient.setAcuity(true);
    patient.setLastKnownWell(new Date());
    patient.setIsConfidential(false);

    springDataJDBCPatientRepository.save(patient);
    log.info("Created Spring Data JDBC patient {}", patient.getPatientId());

  }

  public void findPatientByName() {

    Patient patient = springDataJDBCPatientRepository.findByPatientName("SpringDateJDBCTest");
    log.info("Found Spring Data JDBC patient {}", patient.getPatientName());

  }

  public void updatePatient() {

    Patient patient = springDataJDBCPatientRepository.findByPatientName("SpringDateJDBCTest");
    patient.setPatientName("New SpringDateJDBCTest");
    springDataJDBCPatientRepository.save(patient);
    log.info("Updated Spring Data JDBC patient {}", patient.getPatientName());

  }

  public void deletePatient() {

    Patient patient = springDataJDBCPatientRepository.findByPatientName("New SpringDateJDBCTest");
    springDataJDBCPatientRepository.delete(patient);
    log.info("Deleted Spring Data JDBC patient {}", patient.getPatientName());

  }

  public void findPatientById() {

    Optional<Patient> optionalPatient = springDataJDBCPatientRepository.findById(Long.valueOf(1));
    Patient patient = optionalPatient.get();
    log.info("Found Spring Data JDBC patient {}", patient.getPatientName());

  }

  public void findUserById() {

    User user = springDataJDBCUserRepository.findByUserId(Long.valueOf(5));
    log.info("Found Spring Data JDBC user {}", user.getUserName());

  }

  public void findAssignmentById() {

    Optional<Assignment> optionalSpringDataJDBCAssignment = springDataJDBCAssignmentRepository.findById(Long.valueOf(5));
    Assignment assignment = optionalSpringDataJDBCAssignment.get();
    log.info("Found Spring JDBC assignment {}", assignment.getId());

  }

  public void updateAssignment() {

    Optional<Assignment> optionalSpringDataJDBCAssignment = springDataJDBCAssignmentRepository.findById(Long.valueOf(5));
    Assignment assignment = optionalSpringDataJDBCAssignment.get();

    assignment.setThirdPartySource("CareTeam");

    springDataJDBCAssignmentRepository.save(assignment);
    log.info("Updated Spring Data JDBC assignment {}", assignment.getId());


  }

}
