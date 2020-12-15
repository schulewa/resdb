package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.refdata.model.dao.TechnologyTypeGroupDao;
import com.apschulewitz.resdb.refdata.model.mapper.TechnologyTypeGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
@Ignore
public class TechnologyTypeGroupServiceTest {

  private TechnologyTypeGroupService technologyTypeGroupService;

  @Autowired
  private TechnologyTypeGroupDao technologyTypeGroupDao;

  @Autowired
  private TechnologyTypeGroupMapper technologyTypeGroupMapper;

  @Before
  public void beforeEachTest() {
    technologyTypeGroupService = new TechnologyTypeGroupService(technologyTypeGroupDao, technologyTypeGroupMapper);
  }

  @Test
  public void given_new_entity_when_add_is_executed_then_return_saved_data() {
    // given

    // when

    // then

  }
}
