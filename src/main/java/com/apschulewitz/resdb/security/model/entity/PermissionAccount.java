/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.security.model.entity;


/**
 *
 * @author adrian
 */

//@Entity
//@Table(name = "resdb_user_account", uniqueConstraints = @UniqueConstraint(columnNames = {"logon_name"}))
public class PermissionAccount {

	private static final long serialVersionUID = -6474631338029541228L;

//    private transient AuthenticationResult authenticationResult;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "logon_name", nullable = false, length = 20)
//    private String logonName;
//
//    @Column(name = "first_name", nullable = false, length = 30)
//    private String firstName;
//
//    @Column(name = "family_name", nullable = false, length = 50)
//    private String familyName;
//
//    @Column(name = "status", nullable = false, length = 1)
////    @Enumerated(EnumType.STRING)
////    @Convert(converter = AccountStatusPersistenceConverter.class)
//    private AccountStatus status;
//
//    @Column(name = "invalid_logon_count", nullable = true)
//    private Integer invalidAccessCount;
//
//    @Column(name = "pwd_last_updated", nullable = true)
//    private LocalDateTime passwordUpdated;
//
//    @Column(name = "last_login", nullable = true)
//    private LocalDateTime lastLogin;
//
//    private transient String sessionId;
//
//    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
//    private Collection<UserGroupMembership> groupMemberships;
//
////    @Column(name = "language_id", nullable = false)
//    @ManyToOne
//    @JoinColumn(name = "language_id", referencedColumnName = "id", nullable = true)
//    private Language preferredLanguage;
//
//    @ManyToOne
//    @JoinColumn(name = "version_user", referencedColumnName = "logon_name", nullable = false)
//    private PermissionAccount versionUser;
//
//    @Column(name = "version_status", nullable = false, length = 1)
////    @Enumerated(EnumType.STRING)
//    private VersionStatus versionStatus;
//
//
//    public PermissionAccount getVersionUser() {
//        return versionUser;
//    }
//
//    public void setVersionUser(PermissionAccount versionUser) {
//        this.versionUser = versionUser;
//    }
//
//    public VersionStatus getVersionStatus() {
//        return versionStatus;
//    }
//
//    public void setVersionStatus(VersionStatus versionStatus) {
//        this.versionStatus = versionStatus;
//    }
//
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setLogonName(String name)
//    {
//        logonName = name;
//    }
//
//    public String getLogonName()
//    {
//        return logonName;
//    }
//
//    public void setFirstName(String name)
//    {
//        firstName = name;
//    }
//
//    public String getFirstName()
//    {
//        return firstName;
//    }
//
//    public String getFamilyName() {
//        return familyName;
//    }
//
//    public void setFamilyName(String familyName) {
//        this.familyName = familyName;
//    }
//
//    public void setStatus(AccountStatus status)
//    {
//        if (status == null) {
//          this.status = AccountStatus.Unknown;
//        } else {
//          this.status = status;
//        }
//    }
//
//    public AccountStatus getStatus()
//    {
//        return status;
//    }
//
//    @Column(name = "status", length = 1, nullable = false)
//    public String getAccountStatus() {
//        return getStatus().getCode();
//    }
//
//    public void setAccountStatus(String code) {
//        setStatus(AccountStatus.getStatusFor(code));
//    }
//
//    public void setInvalidAccessCount(Integer count) throws InvalidValueException
//    {
//        if (count < 0)
//            throw new InvalidValueException("Invalid access count: " + count);
//
//        invalidAccessCount = count;
//    }
//
//    public Integer getInvalidAccessCount()
//    {
//        return invalidAccessCount;
//    }
//
//	public boolean isAccountLocked() {
//		return AccountStatus.Locked.equals(status);
//	}
//
//    public boolean isAccountSuspended() {
//        return AccountStatus.Suspended.equals(status);
//    }
//
//	public Collection<UserGroupMembership> getGroupMemberships() {
//		return groupMemberships;
//	}
//
//	public void setGroupMemberships(Collection<UserGroupMembership> groupMemberships) {
//		this.groupMemberships = groupMemberships;
//	}
//
//    public LocalDateTime getPasswordUpdated() {
//        return passwordUpdated;
//    }
//
//    public void setPasswordUpdated(LocalDateTime passwordUpdated) {
//        this.passwordUpdated = passwordUpdated;
//    }
//
//    public LocalDateTime getLastLogin() {
//        return lastLogin;
//    }
//
//    public void setLastLogin(LocalDateTime lastLogin) {
//        this.lastLogin = lastLogin;
//    }
//
//    public String getSessionId() {
//        return sessionId;
//    }
//
//    public void setSessionId(String sessionId) {
//        this.sessionId = sessionId;
//    }
//
//    public Language getPreferredLanguage() {
//        return preferredLanguage;
//    }
//
//    public void setPreferredLanguage(Language preferredLanguage) {
//        this.preferredLanguage = preferredLanguage;
//    }
//
//        public AuthenticationResult getAuthenticationResult() {
//        return authenticationResult;
//    }
//
//    public void setAuthenticationResult(AuthenticationResult authenticationResult) {
//        this.authenticationResult = authenticationResult;
//    }
//
////    @Override
////	public boolean equals(Object o) {
////		if (o == null)
////			return false;
////		else if (this == o)
////			return true;
////		else if (!(o instanceof UserAccount))
////			return false;
////
////		UserAccount other = (UserAccount) o;
////
////		return logonName == null ? other.getLogonName() == null : logonName.endsWith(other.getLogonName());
////	}
////
////	@Override
////	public int hashCode() {
////		return logonName == null ? 0 : logonName.hashCode();
////	}
//
//    public PermissionAccount()
//    {
//        setAuthenticationResult(AuthenticationResult.UnauthenticatedUser);
//    }
//
//    public PermissionAccount(String logon, String first, String last)
//    {
//        logonName = logon;
//        firstName = first;
//        familyName = last;
//        status = AccountStatus.Unknown;
//        invalidAccessCount = 0;
//        this.passwordUpdated = LocalDateTime.MIN;
//        lastLogin = LocalDateTime.now();
//        setAuthenticationResult(AuthenticationResult.UnauthenticatedUser);
//    }
//
//	@Override
//	public String toString() {
//		return logonName == null ? "null" : logonName;
//	}
//
//	public PermissionAccount clone() {
//        PermissionAccount copy = new PermissionAccount();
//        copy.setAccountStatus(getAccountStatus());
//        copy.setFamilyName(getFamilyName());
//        copy.setFirstName(getFirstName());
//        copy.setGroupMemberships(getGroupMemberships());
//        copy.setInvalidAccessCount(getInvalidAccessCount());
//        copy.setLastLogin(getLastLogin());
//        copy.setLogonName(getLogonName());
//        copy.setPasswordUpdated(getPasswordUpdated());
//        copy.setSessionId(getSessionId());
//        copy.setStatus(getStatus());
//        return copy;
//    }
//
//    public enum AccountStatus {
//
//        Unknown("X"),
//        Active("A"),
//        Inactive("I"),
//        Locked("L"),
//        PasswordNeedsResetting("R"),
//        Suspended("S");
//
//        private AccountStatus(String code) {
//            this.code = code;
//        }
//
//        private String code;
//
//        public String getCode() {
//            return code;
//        }
//
//        public void setCode(String code) {
//            this.code = code;
//        }
//
//        public static AccountStatus getStatusFor(String code) {
//            if (StringUtils.isEmpty(code))
//                return Unknown;
//
//            switch (code) {
//                case "A":
//                    return Active;
//                case "I":
//                    return Inactive;
//                case "L":
//                    return Locked;
//                case "R":
//                    return PasswordNeedsResetting;
//                case "S":
//                    return Suspended;
//                default:
//                    return Unknown;
//            }
//        }
//
//    }
}
