package com.apschulewitz.resdb.common.model;

import java.util.List;

public interface TestDataHelper<E extends VersionableDataEntity, D extends VersionableDataDto> {

  /**
   * <CODE>constructUnsavedMinimalEntity</CODE> constructs an unsaved instance of the entity.
   *
   * <ol>The following properties are not set:
   *    <li>createdBy</li>
   *    <li>id</li>
   *    <li>lastUpdated</li>
   *    <li>status</li>
   *    <li>updatedBy</li>
   *    <li>versionNumber</li>
   * </ol>
   * @return E an instance of the entity
   */
   E constructUnsavedMinimalEntity();

  /**
   * <CODE>constructNewEntityWithAllValues</CODE> constructs an unsaved instance of the entity.
   *
   * @return E an instance of the entity
   */
  E constructNewEntityWithAllValues();

  /**
   * <CODE>constructUnsavedMinimalDto</CODE> constructs an unsaved instance of the dto.
   *
   * <ol>The following properties are not set:
   *    <li>createdBy</li>
   *    <li>id</li>
   *    <li>lastUpdated</li>
   *    <li>status</li>
   *    <li>updatedBy</li>
   *    <li>versionNumber</li>
   * </ol>
   * @return D an instance of the entity
   */
  D constructUnsavedMinimalDto();

  /**
   * <CODE>constructListOfUnsavedMinimalDto</CODE> constructs a list of unsaved instances of the dto.
   *
   * <ol>The following properties are not set:
   *    <li>createdBy</li>
   *    <li>id</li>
   *    <li>lastUpdated</li>
   *    <li>status</li>
   *    <li>updatedBy</li>
   *    <li>versionNumber</li>
   * </ol>
   * @return List<D></D> an instance of the entity
   */
  List<D> constructListOfUnsavedMinimalDto();

  /**
   * <P><CODE>constructModifiedMinimalDto</CODE> constructs a modified instance of the dto
   * with all modifications necessary for testing. The original dto passed into this method
   * is modified and returned.
   *
   * A default implementation of this method is provided so that not all data helpers
   * need to provide an implementation.</P>
   *
   * <ol>The following properties are not set:
   *    <li>createdBy</li>
   *    <li>id</li>
   *    <li>lastUpdated</li>
   *    <li>status</li>
   *    <li>updatedBy</li>
   *    <li>versionNumber</li>
   * </ol>
   * @param saved the dto to be modified
   * @return D a modified version of the entity instance
   */
  default D constructModifiedMinimalDto(D saved) {
    return saved;
  }

  /**
   * <CODE>constructUnsavedMinimalDto</CODE> constructs an unsaved instance of the dto.
   *
   * @return D an instance of the entity
   */
  D constructNewDtoWithAllValues();

}
