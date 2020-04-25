package com.mh.heartbeat.dbaccess.poc.springdatajpa;

import com.mh.heartbeat.dbaccess.poc.enums.AlertEnum;
import com.mh.heartbeat.dbaccess.poc.enums.ChiefComplaintEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hb_patient")
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long patientId;

  @Enumerated(EnumType.STRING)
  private ChiefComplaintEnum chiefComplaint;

  @Convert(converter = AlertEnumConverter.class)
  private AlertEnum alerts;

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
  private Date lastUpdated;
  private Integer dischargeDestinationId;
  private Boolean acuity;
  private Date lastKnownWell;
  private Date wardActivationDate;
  private Boolean isConfidential;

}
