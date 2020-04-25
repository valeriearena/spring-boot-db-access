package com.mh.heartbeat.dbaccess.poc.controller;

import com.mh.heartbeat.dbaccess.poc.service.JdbcTemplateService;
import com.mh.heartbeat.dbaccess.poc.service.SpringDataJDBCService;
import com.mh.heartbeat.dbaccess.poc.service.SpringDataJPAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/dbaccesspoc") // Maps http://localhost:8080/ctassignments to this controller
public class POCController {

  private static final String MAPPING_JDBC_TEMPLATE = "jdbctemplate";
  private static final String MAPPING_SPRING_DATA_JDBC = "springdatajdbc";
  private static final String MAPPING_SPRING_DATA_JPA = "springdatajpa";

  private static final String VIEW_HOME = "home";
  private static final String VIEW_ERROR = "error";

  private static final String MESSAGE_ERROR = "errorMessage";

  @Autowired
  private JdbcTemplateService jdbcTemplateService;

  @Autowired
  private SpringDataJDBCService springDataJDBCService;

  @Autowired
  private SpringDataJPAService springDataJPAService;

  @GetMapping
  public String displayHomeScreen() {
    return VIEW_HOME;
  }

  //JdbcTemplate ------------------------------------------------------
  @GetMapping(MAPPING_JDBC_TEMPLATE + "/createNewPatient")
  public String createNewPatient() {
    jdbcTemplateService.createNewPatient();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_JDBC_TEMPLATE + "/findPatientByName")
  public String findByPatientName() {
    jdbcTemplateService.findByPatientName();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_JDBC_TEMPLATE + "/updatePatient")
  public String updatePatient() {
    jdbcTemplateService.updatePatient();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_JDBC_TEMPLATE + "/deletePatient")
  public String deletePatient() {
    jdbcTemplateService.deletePatient();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_JDBC_TEMPLATE + "/findPatientById")
  public String findPatientByIdJdbcTemplate() {
    jdbcTemplateService.findPatientById();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_JDBC_TEMPLATE + "/findPatientByVisitNumber")
  public String findPatientByVisitNumberJdbcTemplate() {
    jdbcTemplateService.findPatientByVisitNumber();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_JDBC_TEMPLATE + "/findPatientByVisitNumberAndMRN")
  public String findPatientByVisitNumberAndMRNJdbcTemplate() {
    jdbcTemplateService.findPatientByVisitNumberAndMRN();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_JDBC_TEMPLATE + "/findUserById")
  public String findUserByIdJdbcTemplate() {
    jdbcTemplateService.findUserById();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_JDBC_TEMPLATE + "/findAssignmentById")
  public String findAssignmentByIdJdbcTemplate() {
    jdbcTemplateService.findAssignmentById();
    return VIEW_HOME;
  }

  //Spring Data JDBC ------------------------------------------------------
  @GetMapping(MAPPING_SPRING_DATA_JDBC + "/createNewPatient")
  public String saveNewPatientSpringDataJdbc() {
    springDataJDBCService.createNewPatient();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JDBC + "/findPatientByName")
  public String findPatientByNameSpringDataJdbc() {
    springDataJDBCService.findPatientByName();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JDBC + "/updatePatient")
  public String updatePatientSpringDataJdbc() {
    springDataJDBCService.updatePatient();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JDBC + "/deletePatient")
  public String deletePatientSpringDataJdbc() {
    springDataJDBCService.deletePatient();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JDBC + "/updateAssignment")
  public String saveNewAssignmentSpringDataJdbc() {
    springDataJDBCService.updateAssignment();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JDBC + "/findPatientById")
  public String findPatientByIdSpringDataJdbc() {
    springDataJDBCService.findPatientById();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JDBC + "/findUserById")
  public String findUserByIdSpringDataJdbc() {
    springDataJDBCService.findUserById();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JDBC + "/findAssignmentById")
  public String findAssignmentByIdSpringDataJdbc() {
    springDataJDBCService.findAssignmentById();
    return VIEW_HOME;
  }


  //Spring Data JPA ------------------------------------------------------
  @GetMapping(MAPPING_SPRING_DATA_JPA + "/createNewPatient")
  public String saveNewPatientSpringDataJPA() {
    springDataJPAService.createNewPatient();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JPA + "/findPatientByName")
  public String findPatientByNameSpringDataJPA() {
    springDataJPAService.findPatientByName();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JPA + "/updatePatient")
  public String updatePatientSpringDataJPA() {
    springDataJPAService.updatePatientByDirtyCheckingNoTransaction();
    springDataJPAService.findPatientAfterDirtyCheckNoTransaction();
    springDataJPAService.updatePatientByDirtyCheckingInTransaction();
    springDataJPAService.findPatientAfterDirtyCheckInTransaction();
    springDataJPAService.updatePatientByModifyingQuery();
    springDataJPAService.findPatientAfterModifyingQuery();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JPA + "/deletePatient")
  public String deletePatientSpringDataJPA() {
    springDataJPAService.deletePatient();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JPA+"/findPatientById")
  public String findPatientById() {
    springDataJPAService.findPatientById();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JPA+"/findPatientByVisitNumber")
  public String findPatientByVisitNumber() {
    springDataJPAService.findPatientByVisitNumber();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JPA+"/findPatientByVisitNumberAndMRN")
  public String findPatientByVisitNumberAndMRN() {
    springDataJPAService.findPatientByVisitNumberAndMRN();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JPA+"/findPatientCount")
  public String findPatientCount() {
    springDataJPAService.findPatientCount();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JPA+"/findAll")
  public String findAll(){
    springDataJPAService.findAll();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JPA+"/findUserById")
  public String findUserById() {
    springDataJPAService.findUserById();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JPA+"/findAssignmentByUserId")
  public String findAssignmentByUserId() {
    springDataJPAService.findAssignmentByUserId();
    return VIEW_HOME;
  }

  @GetMapping(MAPPING_SPRING_DATA_JPA+"/throwException")
  public String throwException(){
    springDataJPAService.throwException();
    return VIEW_HOME;
  }

  @ExceptionHandler(DataAccessException.class)
  public String WebClientResponseException(DataAccessException ex, Model model) {

    String errorMessage = String.format("Error Message = %s", ex.getMessage());
    log.error("Error from database: {}", errorMessage, ex.getStackTrace());

    model.addAttribute(MESSAGE_ERROR, errorMessage);

    return VIEW_ERROR;
  }


}
