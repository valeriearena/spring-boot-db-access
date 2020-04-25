package com.mh.heartbeat.dbaccess.poc.springdatajpa;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hb_patient_caregiver_internal")
public class Assignment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String thirdPartySource;
  private Date lastUpdated;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "patientId")
  private Patient patient;

}
