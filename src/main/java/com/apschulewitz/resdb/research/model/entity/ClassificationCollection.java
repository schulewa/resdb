package com.apschulewitz.resdb.research.model.entity;

import com.apschulewitz.resdb.common.model.VersionableDataEntity;
import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "resdb_classification_collection")
public class ClassificationCollection implements VersionableDataEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "owningCollection", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ClassificationEntry> entries;

    @Column
    @NotNull
    private VersionStatus status;

    @Column
    @NotBlank
    private String createdBy;

    @Column
    private String updatedBy;

    @Column(name = "version_no")
    private Long versionNumber;

    @Column(name = "last_updated")
    private ZonedDateTime lastUpdated;

    private transient DataOperation operation;

    public ClassificationCollection() {
        entries = new ArrayList<>();
    }
}
