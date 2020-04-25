package com.mh.heartbeat.dbaccess.poc.jdbctemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateUserRepository {

  public final static RowMapper<User> userMapper = BeanPropertyRowMapper.newInstance(User.class);

  private static final String SELECT_BY_ID =
      "SELECT userName,userId,imId,password,unlockCode,sipUsername,sipExtensionPW,roleName,sysRoleName,isInternal,externalPhoneNumber,accessCardNumber,accessCardNumberMasked " +
          ",name,heartbeatPhoneNumber,email,lastLoginTime,customOnlineStatusMessage,hospitalId,pagerNumber,deleted,isLastDeviceTurnkey,customOnlineStatus " +
          ",newUser,mustResetUnlockCode,lastUpdateDateTime,lastSyncUpdateDateTime,isMuted,hasAcceptedTermsAndConditions,ldapUniqueId,lastLdapSync,preventLdapSync " +
          ",pendingDeletion,firstName,lastName,initials,title,department,company,liteSyncUpdateDateTime,fmsId,loginFailureCount,userCreationDateTime,hasAlternateCallerIDEnabled " +
          "FROM hb_user WHERE userId = :userId";

  @Autowired // autocommit = true
  private NamedParameterJdbcTemplate jdbcTemplate;

  public User findByUserId(Long userId) {

    SqlParameterSource namedParameters =
        new MapSqlParameterSource()
            .addValue("userId", userId);

    return jdbcTemplate.queryForObject(SELECT_BY_ID, namedParameters, userMapper);
  }

}