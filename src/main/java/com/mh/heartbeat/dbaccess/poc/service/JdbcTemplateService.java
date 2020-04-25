package com.mh.heartbeat.dbaccess.poc.service;

import com.mh.heartbeat.dbaccess.poc.enums.ChiefComplaintEnum;
import com.mh.heartbeat.dbaccess.poc.jdbctemplate.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class JdbcTemplateService {

  @Autowired
  private JdbcTemplatePatientRepository jdbcTemplatePatientRepository;

  @Autowired
  private JdbcTemplateUserRepository jdbcTemplateUserRepository;

  @Autowired
  private JdbcTemplateAssignmentRepository jdbcTemplateAssignmentRepository;

  public void createNewPatient() {

    Patient patient = new Patient();
    patient.setPatientName("JdbcTemplateTest");
    patient.setLastName("JdbcTemplateTest");
    patient.setFirstName("JdbcTemplateTest");
    patient.setMiddleName("JdbcTemplateTest");
    patient.setTitle("JdbcTemplateTest");
    patient.setSuffixName("JdbcTemplateTest");
    patient.setGender(0);
    patient.setCreationDate(new Date());
    patient.setWardId(5);
    patient.setActive(true);
    patient.setPatientNumber("V5678");
    patient.setHospitalPatientNumber("M5678");
    patient.setTargetedDischargeTime(new Date());
    patient.setAlerts("");
    patient.setLastUpdated(new Date());
    patient.setDischargeDestinationId(-1);
    patient.setAcuity(false);
    patient.setChiefComplaint(ChiefComplaintEnum.BACK_PAIN);
    patient.setLastKnownWell(new Date());
    patient.setIsConfidential(true);

    patient = jdbcTemplatePatientRepository.insertPatient(patient);
    log.info("Created Spring JDBC patient {}", patient.getPatientId());
  }

  public void findByPatientName() {

    Patient patient = jdbcTemplatePatientRepository.findByPatientName("JdbcTemplateTest");
    log.info("Found JdbcTemplate patient {}", patient.getPatientName());

  }

  public void updatePatient() {

    Patient patient = jdbcTemplatePatientRepository.findByPatientName("JdbcTemplateTest");
    int status = jdbcTemplatePatientRepository.updatePatientName("New JdbcTemplateTest", patient.getPatientId());
    log.info("Updated JdbcTemplate patient {}", status);

  }

  public void deletePatient() {

    Patient patient = jdbcTemplatePatientRepository.findByPatientName("New JdbcTemplateTest");
    int status = jdbcTemplatePatientRepository.deletePatient(patient.getPatientId());
    log.info("Deleted JdbcTemplate patient {}", status);

  }


  public void findPatientById() {

    Patient patient = jdbcTemplatePatientRepository.findByPatientId(Long.valueOf(1));
    log.info("Found JdbcTemplate patient {}", patient.getPatientName());

  }

  public void findPatientByVisitNumber() {

    Patient patient = jdbcTemplatePatientRepository.findByPatientNumber("V8681164925716200501");
    log.info("Found JdbcTemplate patient {}", patient.getPatientName());

  }

  public void findPatientByVisitNumberAndMRN() {

    Patient patient = jdbcTemplatePatientRepository.findByPatientNumberAndHospitalPatientNumber("V8681164925716200501", "M6969358090164071000");
    log.info("Found JdbcTemplate patient {}", patient.getPatientName());

  }

  public void findUserById() {

    User user = jdbcTemplateUserRepository.findByUserId(Long.valueOf(5));
    log.info("Found JdbcTemplate patient {}", user.getUserName());

  }

  public void findAssignmentById() {

    Assignment assignment = jdbcTemplateAssignmentRepository.findById(Long.valueOf(5));
    log.info("Found JdbcTemplate assignment {}", assignment.getUserId());

    Assignment assignmentFullyLoaded = jdbcTemplateAssignmentRepository.findByIdFullyLoaded(Long.valueOf(5));
    log.info("Found JdbcTemplate assignment {}", assignmentFullyLoaded.getUserId());

  }
}
