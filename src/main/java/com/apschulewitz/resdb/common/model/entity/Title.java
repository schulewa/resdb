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
@Table(name = "resdb_title", uniqueConstraints = @UniqueConstraint(columnNames = {"title"}))
@Audited
public class Title {
	private static final long serialVersionUID = -1411917831665423698L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = true, length = 250)
    private String description;

    @Tolerate
    public Title() {

    }
}
