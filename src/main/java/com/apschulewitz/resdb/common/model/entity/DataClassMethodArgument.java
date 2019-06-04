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
@Table(name = "resdb_data_class_method_argmnt",
        uniqueConstraints = @UniqueConstraint(columnNames = {"data_class_key", "method_id", "argument_sequence_no", "argument_data_type"}))
@Audited
public class DataClassMethodArgument {
	private static final long serialVersionUID = 7011125803606263346L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "data_class_key")
    private DataClassAssociation dataClassAssociation;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "method_id")
	private DataClassMethod dataClassMethod;

	@Column(name = "argument_sequence_no", nullable = false)
    private Integer sequenceNumber;

	@Column(name = "argument_data_type", nullable = false)
    private String dataType;

    @Tolerate
    public DataClassMethodArgument() {

    }
}
