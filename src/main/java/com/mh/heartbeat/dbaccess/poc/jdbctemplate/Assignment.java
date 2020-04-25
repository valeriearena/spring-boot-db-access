package com.mh.heartbeat.dbaccess.poc.jdbctemplate;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Assignment {

  private Long id;
  private String thirdPartySource;
  private Date lastUpdated;
  private Long userId;
  private Long patientId;

  private User userEntity;
  private Patient patientEntity;


}
