/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.common.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 *
 * @author adrian
 */

@Data
@Builder
@Entity
@Table(name = "resdb_data_class", uniqueConstraints = @UniqueConstraint(columnNames = {"data_class_key"} ))
@Audited

public class DataClassAssociation {
	private static final long serialVersionUID = 1653383219977149017L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="data_class_key", nullable = true, length = 30)
    private String keyName;

	@Column(name = "fully_qualified_class_name", nullable = false, length = 250)
    private String className;

    @Tolerate
    public DataClassAssociation() {

    }
}
