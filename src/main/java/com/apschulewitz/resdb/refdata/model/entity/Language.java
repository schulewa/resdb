/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.entity.DataEntityId;
import com.apschulewitz.resdb.common.model.entity.YesNoChoice;
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
@Table(name = "resdb_language", uniqueConstraints = @UniqueConstraint(columnNames = {"iso_6392_code_alpha_3b", "name"}))
//@Audited
public class Language implements DataEntityId {
	private static final long serialVersionUID = 5803383078653905879L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iso_6391_code", nullable = false, length = 5)
    private String iso6391Code;

	@Column(name = "iso_6392_code_alpha_3b", nullable = false, length = 3)
    private String iso6392CodeAlpha3B;                    // ISO 639-2

    @Column(name = "iso_6392_code_alpha_3t", nullable = true, length = 3)
    private String iso6392CodeAlpha3t;

    @Column(name = "iso_6392_code_alpha_2", nullable = true, length = 2)
    private String iso6392CodeAlpha2;

	@Column(nullable = false, length = 30)
    private String name;

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

    @Tolerate
    public Language() {

    }
}
