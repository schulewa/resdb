package com.apschulewitz.resdb.common.model.validator;

import com.apschulewitz.resdb.common.model.VersionableDataDto;

public interface DataComparator<D extends VersionableDataDto<K>, K> {

  boolean areEqual(D dataEntity1, D dataEntity2);

}
