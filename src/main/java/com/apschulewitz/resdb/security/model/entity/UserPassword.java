package com.apschulewitz.resdb.security.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "resdb_user_password")
public class UserPassword {

	private static final long serialVersionUID = 2757360464728176686L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @Column
    private String password;

    @Column
    private LocalDate validFrom;

    @Column(name = "valid_to")
    private LocalDate validUntil;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName= "id")
    private UserAccount user;

    @Tolerate
    public UserPassword() {

    }
}
