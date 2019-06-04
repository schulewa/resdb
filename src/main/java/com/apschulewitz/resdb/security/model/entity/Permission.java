package com.apschulewitz.resdb.security.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Permission
 *
 */
@Data
@Builder
@Entity
@Table(name="resdb_permission", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Permission implements GrantedAuthority {

	private static final long serialVersionUID = -5425566642409874667L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable=false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
    private PermissionStatus status;

	@Column(nullable = false)
    private OperationType operationType;

    @Override
    public String getAuthority() {
        return name;
    }

    public enum PermissionStatus {

        Unknown("X"),
        Active("A"),
        Inactive("I"),
        Suspended("S");

        private PermissionStatus(String code) {
            this.code = code;
        }

        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public static Permission.PermissionStatus getStatusFor(String code) {
            if (StringUtils.isEmpty(code))
                return Unknown;

            switch (code) {
                case "A":
                    return Active;
                case "I":
                    return Inactive;
                case "S":
                    return Suspended;
                default:
                    return Unknown;
            }
        }

    }

    public enum OperationType {

        Unknown("X"),
        Create("C"),
        Update("U"),
        Delete("D"),
        Read("R"),
        CreateReadUpdate("CRU"),
        CreateReadUpdateDelete("CRUD");

        private OperationType(String code) {
            this.code = code;
        }

        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public static Permission.OperationType getStatusFor(String code) {
            if (StringUtils.isEmpty(code))
                return Unknown;

            switch (code) {
                case "C":
                    return Create;
                case "U":
                    return Update;
                case "D":
                    return Delete;
                case "CRU":
                    return CreateReadUpdate;
                case "CRUD":
                    return CreateReadUpdateDelete;
                default:
                    return Unknown;
            }
        }

    }

    @Tolerate
    public Permission() {

    }

}
