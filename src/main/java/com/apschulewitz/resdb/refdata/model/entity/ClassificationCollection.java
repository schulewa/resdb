package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Tolerate;
import com.apschulewitz.resdb.refdata.model.entity.ClassificationEntry;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "resdb_classification_collection")
public class ClassificationCollection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "owningCollection", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ClassificationEntry> entries = new ArrayList<>();

    @Column
    @NotNull
    private VersionStatus status;

    @Column
    @NotBlank
    private String createdBy;

    @Column
    @NotBlank
    private String updatedBy;

    @Version
    @Column(name = "last_updated")
    @NotNull
    private LocalDateTime lastUpdated;

    private transient DataOperation operation;

    public ClassificationCollection() {
//        entries = new ArrayList<>();
    }
}
