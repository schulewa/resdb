/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.security.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;

/**
 *
 * @author adrian
 */
@Data
@Builder
@Entity
@Table(name = "resdb_user_group", uniqueConstraints = @UniqueConstraint(columnNames = {"group_name"}))
public class UserGroup {

	private static final long serialVersionUID = 5312097513514344853L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false, name = "group_name", length = 20)
    private String name;

    @Column(nullable = false, name = "display_name", length = 30)
    private String displayName;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Collection<UserGroupPermission> groupPermissions;

    @Tolerate
    public UserGroup() {

    }
}
