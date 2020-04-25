package com.mh.heartbeat.dbaccess.poc.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * JdbcTemplate executes DB operations with auto-commit = true, which is the default behavior of SQL Server.
 * To use transactions, we do not set the connection's auto-commit mode to false.
 * Instead, we tell Spring to start transactions. In turn, Spring will retrieve a connection, set its auto-commit mode to false,
 * and keep using this same connection until the transaction is completed (either with all changes saved/committed, or all changes rolled back).
 * <p>
 * The @Transactional annotation tells Spring when to start a transaction (by setting the connection's auto-commit mode to false).
 * <p>
 * NOTE: The Spring team's recommendation is that you only annotate concrete classes with the @Transactional annotation, as opposed to annotating interfaces.
 * From http://static.springsource.org/spring/docs/2.0.x/reference/transaction.html\
 * The Spring team's recommendation is that you only annotate concrete classes with the @Transactional annotation, as opposed to annotating interfaces.
 */
@Repository
public class JdbcTemplatePatientRepository {

  public final static RowMapper<Patient> patientMapper = BeanPropertyRowMapper.newInstance(Patient.class);

  public static final String INSERT_PATIENT =
      "INSERT INTO hb_patient " +
          "(patientName,lastName,firstName,middleName,title,suffixName,dateOfBirth,gender,creationDate,wardId,active,patientNumber,hospitalPatientNumber,bedNumber,targetedDischargeTime,latestDischargeTime,alerts,lastUpdated,dischargeDestinationId,acuity,chiefComplaint,lastKnownWell,wardActivationDate,isConfidential) " +
          "VALUES " +
          "(:patientName,:lastName,:firstName,:middleName,:title,:suffixName,:dateOfBirth,:gender,:creationDate,:wardId,:active,:patientNumber,:hospitalPatientNumber,:bedNumber,:targetedDischargeTime,:latestDischargeTime,:alerts,:lastUpdated,:dischargeDestinationId,:acuity,:chiefComplaint,:lastKnownWell,:wardActivationDate,:isConfidential)";

  public final String UPDATE_PATIENT =
      "UPDATE hb_patient SET patientName = :patientName WHERE patientId = :patientId";

  public final String DELETE_PATIENT_BY_ID = "DELETE FROM hb_patient WHERE patientId = :patientId";

  private static final String SELECT_BY_ID =
      "SELECT patientId,patientName,lastName,firstName,middleName,title,suffixName,dateOfBirth,gender,creationDate,wardId,active,patientNumber,hospitalPatientNumber,bedNumber,targetedDischargeTime,latestDischargeTime,alerts,lastUpdated,dischargeDestinationId,acuity,chiefComplaint,lastKnownWell,wardActivationDate,isConfidential " +
          "FROM hb_patient WHERE patientId = :patientId";

  private static final String SELECT_BY_PATIENT_NAME =
      "SELECT patientId,patientName,lastName,firstName,middleName,title,suffixName,dateOfBirth,gender,creationDate,wardId,active,patientNumber,hospitalPatientNumber,bedNumber,targetedDischargeTime,latestDischargeTime,alerts,lastUpdated,dischargeDestinationId,acuity,chiefComplaint,lastKnownWell,wardActivationDate,isConfidential " +
          "FROM hb_patient WHERE patientName = :patientName";

  private static final String SELECT_BY_PATIENT_NUMBER =
      "SELECT patientId,patientName,lastName,firstName,middleName,title,suffixName,dateOfBirth,gender,creationDate,wardId,active,patientNumber,hospitalPatientNumber,bedNumber,targetedDischargeTime,latestDischargeTime,alerts,lastUpdated,dischargeDestinationId,acuity,chiefComplaint,lastKnownWell,wardActivationDate,isConfidential " +
          "FROM hb_patient WHERE patientNumber = :patientNumber";

  private static final String SELECT_BY_PATIENT_NUMBER_AND_HOSPITAL_PATIENT_NUMBER =
      "SELECT patientId,patientName,lastName,firstName,middleName,title,suffixName,dateOfBirth,gender,creationDate,wardId,active,patientNumber,hospitalPatientNumber,bedNumber,targetedDischargeTime,latestDischargeTime,alerts,lastUpdated,dischargeDestinationId,acuity,chiefComplaint,lastKnownWell,wardActivationDate,isConfidential " +
          "FROM hb_patient WHERE patientNumber = :patientNumber";

  @Autowired // autocommit = true
  private NamedParameterJdbcTemplate jdbcTemplate;

  public Patient insertPatient(Patient patient) {

    KeyHolder holder = new GeneratedKeyHolder();
    SqlParameterSource namedParameters = new MapSqlParameterSource()
        .addValue("patientId", patient.getPatientId())
        .addValue("patientName", patient.getPatientName())
        .addValue("lastName", patient.getLastName())
        .addValue("firstName", patient.getFirstName())
        .addValue("middleName", patient.getMiddleName())
        .addValue("title", patient.getTitle())
        .addValue("suffixName", patient.getSuffixName())
        .addValue("dateOfBirth", patient.getDateOfBirth())
        .addValue("gender", patient.getGender())
        .addValue("creationDate", patient.getCreationDate())
        .addValue("wardId", patient.getWardId())
        .addValue("active", patient.getActive())
        .addValue("patientNumber", patient.getPatientNumber())
        .addValue("hospitalPatientNumber", patient.getHospitalPatientNumber())
        .addValue("bedNumber", patient.getBedNumber())
        .addValue("targetedDischargeTime", patient.getTargetedDischargeTime())
        .addValue("latestDischargeTime", patient.getLatestDischargeTime())
        .addValue("alerts", patient.getAlerts())
        .addValue("lastUpdated", patient.getLastUpdated())
        .addValue("dischargeDestinationId", patient.getDischargeDestinationId())
        .addValue("acuity", patient.getAcuity())
        .addValue("chiefComplaint", patient.getChiefComplaint().name())
        .addValue("lastKnownWell", patient.getLastKnownWell())
        .addValue("wardActivationDate", patient.getWardActivationDate())
        .addValue("isConfidential", patient.getIsConfidential());

    jdbcTemplate.update(INSERT_PATIENT, namedParameters, holder);
    patient.setPatientId(holder.getKey().longValue());
    return patient;

  }

  public int updatePatientName(String patientName, Long patientId) {

    SqlParameterSource namedParameters = new MapSqlParameterSource()
        .addValue("patientName", patientName)
        .addValue("patientId", patientId);
    return jdbcTemplate.update(UPDATE_PATIENT, namedParameters);

  }

  public int deletePatient(Long patientId) {

    SqlParameterSource namedParameters = new MapSqlParameterSource()
        .addValue("patientId", patientId);
    return jdbcTemplate.update(DELETE_PATIENT_BY_ID, namedParameters);

  }

  public Patient findByPatientId(Long patientId) {

    SqlParameterSource namedParameters = new MapSqlParameterSource()
        .addValue("patientId", patientId);
    return jdbcTemplate.queryForObject(SELECT_BY_ID, namedParameters, patientMapper);

  }

  public Patient findByPatientName(String patientName) {

    SqlParameterSource namedParameters = new MapSqlParameterSource()
        .addValue("patientName", patientName);
    return jdbcTemplate.queryForObject(SELECT_BY_PATIENT_NAME, namedParameters, patientMapper);

  }

  public Patient findByPatientNumber(String patientNumber) {

    SqlParameterSource namedParameters = new MapSqlParameterSource()
        .addValue("patientNumber", patientNumber);
    return jdbcTemplate.queryForObject(SELECT_BY_PATIENT_NUMBER, namedParameters, patientMapper);

  }

  public Patient findByPatientNumberAndHospitalPatientNumber(String patientNumber, String hospitalPatientNumber) {

    SqlParameterSource namedParameters = new MapSqlParameterSource()
        .addValue("patientNumber", patientNumber)
        .addValue("hospitalPatientNumber", hospitalPatientNumber);

    return jdbcTemplate.queryForObject(SELECT_BY_PATIENT_NUMBER_AND_HOSPITAL_PATIENT_NUMBER, namedParameters, patientMapper);

  }

}


