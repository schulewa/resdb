package com.apschulewitz.resdb.research.model;

import com.apschulewitz.resdb.common.model.TestDataHelper;
import com.apschulewitz.resdb.common.model.VersionableDataDto;
import com.apschulewitz.resdb.common.model.VersionableDataEntity;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <P><CODE>AbstractTestHelper</CODE> provides helper methods to construct sample data
 * for use in unit or integration tests.</P>
 *
 * E = a JPA entity
 * D = a data transfer object
 */
public abstract class AbstractTestHelper<E extends VersionableDataEntity, D extends VersionableDataDto>
        implements TestDataHelper<E, D> {

  protected static final String USER_NAME = "testuser";

  protected static final String USER_NAME1 = "updated_by_user";

  protected final AtomicLong ID = new AtomicLong(1);

  protected final AtomicLong VERSION_NUMBER = new AtomicLong(1);

}
