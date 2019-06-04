package com.apschulewitz.resdb.refdata.model.dao;

import com.apschulewitz.resdb.refdata.model.entity.MeasureConverter;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by adrianschulewitz on 01/10/2016.
 */
@Repository
@Transactional
public interface MeasureConverterDao extends CrudRepository<MeasureConverter, Long> {

    @EntityGraph(value = "MeasureConverter.all", type = EntityGraph.EntityGraphType.LOAD)
    MeasureConverter findByNameEquals(String name);

}
