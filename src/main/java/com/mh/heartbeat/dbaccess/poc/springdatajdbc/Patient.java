package com.mh.heartbeat.dbaccess.poc.springdatajdbc;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@NoArgsConstructor
@Table("hb_patient")
public class Patient {

  @Id
  private Long patientId;
  private String patientName;
  private String lastName;
  private String firstName;
  private String middleName;
  private String title;
  private String suffixName;
  private Date dateOfBirth;
  private Integer gender;
  private Date creationDate;
  private Integer wardId;
  private Boolean active;
  private String patientNumber;
  private String hospitalPatientNumber;
  private String bedNumber;
  private Date targetedDischargeTime;
  private Date latestDischargeTime;
  private String alerts;
  private Date lastUpdated;
  private Integer dischargeDestinationId;
  private Boolean acuity;
  private String chiefComplaint;
  private Date lastKnownWell;
  private Date wardActivationDate;
  private Boolean isConfidential;

}
