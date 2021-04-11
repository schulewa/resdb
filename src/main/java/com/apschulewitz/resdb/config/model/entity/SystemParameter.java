package com.apschulewitz.resdb.config.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Builder
@Entity
@Table(name = "resdb_system_parameters", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class SystemParameter {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(nullable = false)
	private String name;

    @Column
    private String description;

    @Column
    private String stringValue;

    @Column
    private Integer integerValue;

    @Column
    private Boolean booleanValue;

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStringValue(String stringValue) {
    checkThatParameterIsOnlyOneDataType();
    this.stringValue = stringValue;
  }

  public void setIntegerValue(Integer integerValue) {
    checkThatParameterIsOnlyOneDataType();
    this.integerValue = integerValue;
  }

  public void setBooleanValue(Boolean booleanValue) {
    checkThatParameterIsOnlyOneDataType();
    this.booleanValue = booleanValue;
  }

  private void checkThatParameterIsOnlyOneDataType() {
    if (booleanValue != null || integerValue != null || stringValue != null)
      throw new IllegalArgumentException("A parameter may only be set to boolean, integer or string value");
    return;
  }

  @Tolerate
    public SystemParameter() {

    }
}
