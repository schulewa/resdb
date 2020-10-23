/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.apschulewitz.resdb.common.model.converter;

/**
 * @author Adrian.Schulewitz
 * @version $Id: EntityToDtoConverter.java, v 0.1 2020-10-18 15:16 adrianschulewitz.hds Exp $$
 */
public interface EntityToDtoConverter<E, D> {

  D toDto(E entity);

}
