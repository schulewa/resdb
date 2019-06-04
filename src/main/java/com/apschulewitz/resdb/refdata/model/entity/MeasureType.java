package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "resdb_measure_type")
//@Audited
/**
 * Class <CODE>MeasureType</CODE> indicates the type of Measure.
 * This will be one of:
 * <UL>
 *     <LI>DISTANCE</LI>
 *     <LI>TIME</LI>
 *     <LI>VOLUME</LI>
 * </UL>
 * so is intended to ensure that measure converters only attempt to convert measures for the from/to the same type
 * of measure.
 */
public class MeasureType {

	private static final long serialVersionUID = -3137782301296245542L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String name;

  @Column
  private VersionStatus status;

  @Column
  private String createdBy;

  @Column
  private String updatedBy;

  @Version
  @Column(name = "last_updated")
  private LocalDateTime lastUpdated;

  private transient DataOperation operation;

    @Tolerate
    public MeasureType() {
        super();
    }
}
