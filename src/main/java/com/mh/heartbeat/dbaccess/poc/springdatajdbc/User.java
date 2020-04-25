package com.mh.heartbeat.dbaccess.poc.springdatajdbc;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@NoArgsConstructor
@Table("hb_user")
public class User {

  @Id
  private Long userId;
  private String userName;
  private String imId;
  private String password;
  private String unlockCode;
  private String sipUsername;
  private String sipExtensionPW;
  private String roleName;
  private String sysRoleName;
  private Boolean isInternal;
  private String externalPhoneNumber;
  private String accessCardNumber;
  private String accessCardNumberMasked;
  private String name;
  private String heartbeatPhoneNumber;
  private String email;
  private Date lastLoginTime;
  private String customOnlineStatusMessage;
  private Integer hospitalId;
  private String pagerNumber;
  private Boolean deleted;
  private Boolean isLastDeviceTurnkey;
  private Integer customOnlineStatus;
  private Boolean newUser;
  private Boolean mustResetUnlockCode;
  private Date lastUpdateDateTime;
  private Date lastSyncUpdateDateTime;
  private Boolean isMuted;
  private Boolean hasAcceptedTermsAndConditions;
  private String ldapUniqueId;
  private Date lastLdapSync;
  private Boolean preventLdapSync;
  private Boolean pendingDeletion;
  private String firstName;
  private String lastName;
  private String initials;
  private String title;
  private String department;
  private String company;
  private Date liteSyncUpdateDateTime;
  private Integer fmsId;
  private Integer loginFailureCount;
  private Date userCreationDateTime;
  private Boolean hasAlternateCallerIDEnabled;


}
