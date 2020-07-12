/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.entity.DataEntityId;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.entity.YesNoChoice;
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
@Table(name = "resdb_language", uniqueConstraints = @UniqueConstraint(columnNames = {"iso_6392_code_alpha_2b", "name"}))
public class Language implements DataEntityId {
	private static final long serialVersionUID = 5803383078653905879L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String nativeName;

    @Column(name = "iso_6391_code_1", nullable = false, length = 5)
    private String iso6391Code1;

    @Column(name = "iso_6392_code_alpha_2t", nullable = true, length = 3)
    private String iso6392CodeAlpha2t;

	@Column(name = "iso_6392_code_alpha_2b", nullable = false, length = 3)
    private String iso6392CodeAlpha2b;                    // ISO 639-2

    @Column(name = "iso_6392_code_alpha_3", nullable = true, length = 3)
    private String iso6392CodeAlpha3;

	@Column(nullable = false, length = 1)
    private YesNoChoice deciphered;

    @Column(nullable = false, length = 1)
    private YesNoChoice living;

    @Column(nullable = false, length = 1)
    private YesNoChoice constructed;

    @Column(nullable = false, length = 1)
    private YesNoChoice macroLanguage;

    @ManyToOne
	@JoinColumn(name = "language_group_id", referencedColumnName = "id")
    private LanguageGroup languageGroup;

    @Column(length = 500)
    private String notes;

    @Column
    private VersionStatus status;

    @Column
    private String createdBy;

    @Column
    private String updatedBy;

    @Version
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Tolerate
    public Language() {

    }
}
