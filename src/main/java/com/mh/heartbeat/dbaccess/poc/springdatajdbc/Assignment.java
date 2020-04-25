package com.mh.heartbeat.dbaccess.poc.springdatajdbc;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@NoArgsConstructor
@Table("hb_patient_caregiver_internal")
public class Assignment {

  @Id
  private Long id;
  private String thirdPartySource;
  private Date lastUpdated;

  @Column(value = "userId")
  private User userEntity;

  @Column(value = "patientId")
  private Patient patientEntity;

}
