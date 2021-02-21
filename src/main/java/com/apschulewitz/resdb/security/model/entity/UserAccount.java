package com.apschulewitz.resdb.security.model.entity;


import com.apschulewitz.resdb.common.model.entity.DataEntityId;
import com.apschulewitz.resdb.refdata.model.entity.Language;
import com.apschulewitz.resdb.security.model.AuthenticationResult;
import com.apschulewitz.resdb.security.model.converter.AccountStatusPersistenceConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author adrian
 */
@Data
@ToString(exclude = {"groupMemberships", "passwords"})
@Builder
@Entity
@Table(name = "resdb_user_account", uniqueConstraints = @UniqueConstraint(columnNames = {"logon_name"}))
@JsonIgnoreProperties(value = { "groupMemberships", "passwords" })
public class UserAccount implements DataEntityId {

	private static final long serialVersionUID = -6474631338029541228L;

    private transient AuthenticationResult authenticationResult;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //
    // logonName can be left blank IF their email address is set
    //

    @Column(name = "logon_name", length = 20)
    private String logonName;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "email_verified")
    private ZonedDateTime emailVerified;

    @Column(name = "recovery_email")
    private String recoveryEmail;

    @Column(name = "recovery_email_verified")
    private ZonedDateTime recoveryEmailVerified;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "family_name", nullable = false, length = 50)
    private String familyName;

    @Column(name = "status", nullable = false, length = 1)
    @Convert(converter = AccountStatusPersistenceConverter.class)
    private AccountStatus status;

    @Column(name = "invalid_logon_count", nullable = true)
    private Integer invalidAccessCount;

    @Column(name = "pwd_last_updated", nullable = true)
    private LocalDateTime passwordUpdated;

    @Column(name = "last_logon", nullable = true)
    private LocalDateTime lastLogon;

    private transient String sessionId;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference // resolve 'Unable to parse JSON errors'
    private Collection<UserGroupMembership> groupMemberships;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference // resolve 'Unable to parse JSON errors'
    private Collection<UserPassword> passwords;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language preferredLanguage;

    @Column(name = "is_template", nullable = false)
    private boolean templateUser; // if true then the user cannot log in, is used purely for basing new accounts on

    public Optional<UserPassword> getCurrentPassword() {
        LocalDate today = LocalDate.now();
        if (getPasswords() != null) {
//            return getPasswords().stream()
//                    .peek((p) -> log.info("p="+p))
//                    .filter(p -> (p.getValidFrom().isBefore(today) || p.getValidFrom().isEqual(today)) &&
//                                    (p.getValidUntil() != null && p.getValidUntil().isAfter(today))).findFirst();
            for (UserPassword password : getPasswords()) {
                if ((password.getValidFrom().isBefore(today) || password.getValidFrom().isEqual(today)) &&
                  (password.getValidUntil() == null || (password.getValidUntil() != null && password.getValidUntil().isAfter(today)))) {
                    return Optional.of(password);
                }
            }
        }
        return Optional.empty();
    }

    public boolean hasBeenValidated() {
        return this.getAuthenticationResult() != null && AuthenticationResult.AuthenticatedUser.equals(getAuthenticationResult());
    }

    @Tolerate
    public UserAccount() {

    }
}
