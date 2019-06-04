/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.security.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 *
 * @author adrian
 */
@Data
@Builder
@Entity
@Table(name = "resdb_user_group_membership")
public class UserGroupMembership {
	private static final long serialVersionUID = 8145144919867791233L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName= "id")
    private UserAccount user;

	@ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private UserGroup group;

	@Column
    private LocalDateTime validFrom;

	@Column
    private LocalDateTime validTo;

    @Tolerate
    public UserGroupMembership() {

    }
}
