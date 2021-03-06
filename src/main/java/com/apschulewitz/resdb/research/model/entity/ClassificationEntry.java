package com.apschulewitz.resdb.research.model.entity;

import com.apschulewitz.resdb.common.InvalidValueException;
import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "resdb_classification_entry")
public class ClassificationEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    @NotBlank
    private String name;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    @JsonManagedReference
    private ClassificationCollection owningCollection;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_entry_id")
    @JsonBackReference
    private ClassificationEntry parentEntry;

    @OneToMany(mappedBy = "parentEntry", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ClassificationEntry> entries = new ArrayList<>();

    @Column
    @NotNull
    private VersionStatus status;

    @Column
    @NotBlank
    private String createdBy;

    @Column
    private String updatedBy;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    private transient DataOperation operation;

    public ClassificationEntry() {
//        entries = new ArrayList<>();
    }

    public ClassificationEntry setParentEntry(ClassificationEntry parentEntry) {
        if (parentEntry == null) {
            throw new InvalidValueException("Null value passed as parentEntry");
        }

        this.parentEntry = parentEntry;
        parentEntry.addChildEntry(this);
        return this;
    }

    public ClassificationEntry addChildEntry(ClassificationEntry childEntry) {
        if (childEntry == null) {
            throw new InvalidValueException("Null value passed as childEntry");
        }
        this.entries.add(childEntry);
        return this;
    }
}
