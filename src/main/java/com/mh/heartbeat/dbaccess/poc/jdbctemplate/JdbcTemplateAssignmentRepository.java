package com.mh.heartbeat.dbaccess.poc.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcTemplateAssignmentRepository {

  public final static RowMapper<Assignment> caregiverMapper = BeanPropertyRowMapper.newInstance(Assignment.class);

  private static final String SELECT_BY_USER_ID =
      "SELECT id,patientId,userId,thirdPartySource,lastUpdated " +
          "FROM hb_patient_caregiver_internal WHERE id = :id";

  private static final String SELECT_BY_USER_ID_FULLY_LOADED =
      "SELECT c.id,c.patientId,c.userId,c.thirdPartySource,c.lastUpdated," +
            "p.patientName,p.lastName,p.firstName,p.middleName,p.title,p.suffixName,p.dateOfBirth,p.gender,p.creationDate,p.wardId,p.active,p.patientNumber,p.hospitalPatientNumber,p.bedNumber,p.targetedDischargeTime,p.latestDischargeTime,p.alerts,p.lastUpdated,p.dischargeDestinationId,p.acuity,p.chiefComplaint,p.lastKnownWell,p.wardActivationDate,p.isConfidential," +
            "u.userName,u.imId,u.password,u.unlockCode,u.sipUsername,u.sipExtensionPW,u.roleName,u.sysRoleName,u.isInternal,u.externalPhoneNumber,u.accessCardNumber,u.accessCardNumberMasked,u.name,u.heartbeatPhoneNumber,u.email,u.lastLoginTime,u.customOnlineStatusMessage,u.hospitalId,u.pagerNumber,u.deleted,u.isLastDeviceTurnkey,u.customOnlineStatus,u.newUser,u.mustResetUnlockCode,u.lastUpdateDateTime,u.lastSyncUpdateDateTime,u.isMuted,u.hasAcceptedTermsAndConditions,u.ldapUniqueId,u.lastLdapSync,u.preventLdapSync,u.pendingDeletion,u.firstName,u.lastName,u.initials,u.title,u.department,u.company,u.liteSyncUpdateDateTime,u.fmsId,u.loginFailureCount,u.userCreationDateTime,u.hasAlternateCallerIDEnabled " +
          "FROM hb_patient_caregiver_internal c " +
          "JOIN hb_user u ON c.userId = u.userId " +
          "JOIN hb_patient p ON c.patientId = p.patientId WHERE id = :id";


  @Autowired
  private JdbcTemplatePatientRepository jdbcTemplatePatientRepository;
  @Autowired
  private JdbcTemplateUserRepository jdbcTemplateUserRepository;
  @Autowired // autocommit = true
  private NamedParameterJdbcTemplate jdbcTemplate;

  public Assignment findById(Long id) {

    SqlParameterSource namedParameters =
        new MapSqlParameterSource()
            .addValue("id", id);
    return jdbcTemplate.queryForObject(SELECT_BY_USER_ID, namedParameters, caregiverMapper);
  }

  public Assignment findByIdFullyLoaded(Long id) {

    SqlParameterSource namedParameters =
        new MapSqlParameterSource()
            .addValue("id", id);
    return jdbcTemplate.queryForObject(SELECT_BY_USER_ID_FULLY_LOADED, namedParameters, new CaregiverFullLoadedMapper());
  }

  private class CaregiverFullLoadedMapper implements RowMapper<Assignment> {

    @Override
    public Assignment mapRow(ResultSet rs, int rowNum) throws SQLException {

      Assignment assignment = (new BeanPropertyRowMapper<>(Assignment.class)).mapRow(rs,rowNum);
      User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs,rowNum);
      Patient patient = (new BeanPropertyRowMapper<>(Patient.class)).mapRow(rs,rowNum);

      assignment.setUserEntity(user);
      assignment.setPatientEntity(patient);
      return assignment;

    }
  }
}
