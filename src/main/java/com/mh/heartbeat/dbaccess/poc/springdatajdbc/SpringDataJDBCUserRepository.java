package com.mh.heartbeat.dbaccess.poc.springdatajdbc;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJDBCUserRepository extends CrudRepository<User, Long> {

  @Query("SELECT userName,userId,imId,password,unlockCode,sipUsername,sipExtensionPW,roleName,sysRoleName,isInternal,externalPhoneNumber,accessCardNumber,accessCardNumberMasked " +
      ",name,heartbeatPhoneNumber,email,lastLoginTime,customOnlineStatusMessage,hospitalId,pagerNumber,deleted,isLastDeviceTurnkey,customOnlineStatus " +
      ",newUser,mustResetUnlockCode,lastUpdateDateTime,lastSyncUpdateDateTime,isMuted,hasAcceptedTermsAndConditions,ldapUniqueId,lastLdapSync,preventLdapSync " +
      ",pendingDeletion,firstName,lastName,initials,title,department,company,liteSyncUpdateDateTime,fmsId,loginFailureCount,userCreationDateTime,hasAlternateCallerIDEnabled " +
      "FROM hb_user WHERE userId = :userId")
  User findByUserId(@Param("userId") Long userId);
}
