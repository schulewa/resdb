package com.apschulewitz.resdb.security.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserGroupPermission
 *
 */
@Data
@Builder
@Entity
@Table(name="resdb_user_group_permission")
public class UserGroupPermission {

	/**
	 *
	 */
	private static final long serialVersionUID = -4178123573085660956L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @OneToOne()
    @JoinColumn(name = "group_id", nullable = false)
    private UserGroup group;

    @OneToOne()
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;
    
//mappedBy reference an unknown target entity property: com.apschulewitz.resdb.security.model.entity.UserGroupPermission.user
//in com.apschulewitz.resdb.security.model.entity.UserGroup.groupPermissions
    @Tolerate
    public UserGroupPermission() {

    }
}
