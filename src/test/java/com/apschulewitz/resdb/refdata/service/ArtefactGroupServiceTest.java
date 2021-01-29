package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.service.DataServiceTestRunner;
import com.apschulewitz.resdb.refdata.model.comparator.ArtefactGroupDataComparator;
import com.apschulewitz.resdb.refdata.model.dao.ArtefactGroupDao;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactGroup;
import com.apschulewitz.resdb.refdata.model.helper.ArtefactGroupTestHelper;
import com.apschulewitz.resdb.refdata.model.mapper.ArtefactGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@WithMockUser("testuser")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Slf4j
public class ArtefactGroupServiceTest {

  private DataServiceTestRunner<ArtefactGroupDto,
    ArtefactGroup,
    Long,
    ArtefactGroupDataComparator,
    ArtefactGroupTestHelper,
    ArtefactGroupMapper,
    ArtefactGroupDao,
    ArtefactGroupService> testRunner;

  @Autowired
  private ArtefactGroupTestHelper testHelper;

  @Autowired
  private ArtefactGroupDataComparator testDataComparator;

  @Autowired
  private ArtefactGroupService service;

  @Autowired
  private ArtefactGroupDao dao;

  @Before
  public void beforeEachTest() {
    testRunner = new DataServiceTestRunner<>(testHelper, service, testDataComparator);
    dao.deleteAll();
  }

  @Test
  public void given_valid_dto_when_add_is_executed_then_return_saved_dto() {
    testRunner.given_valid_dto_when_add_is_executed_then_return_saved_dto();
  }

  @Test
  public void given_existing_id_when_deleteById_is_executed_then_return_dto_marked_as_cancelled() {
    testRunner.given_existing_id_when_deleteById_is_executed_then_return_dto_marked_as_cancelled();
  }

  @Test
  public void given_entities_when_findAll_is_executed_then_return_list() {
    testRunner.given_entities_when_findAll_is_executed_then_return_list();
  }

  @Test
  public void given_valid_id_when_findById_is_executed_then_return_data() {
    testRunner.given_valid_id_when_findById_is_executed_then_return_data();
  }

  @Test
  public void given_valid_dto_with_minimal_data_when_update_is_executed_then_return_saved_dto() {
    testRunner.given_valid_dto_with_minimal_data_when_update_is_executed_then_return_saved_dto();
  }

  @Test
  public void given_valid_amended_dto_with_minimal_data_when_update_is_executed_then_return_saved_dto() {
    testRunner.given_valid_amended_dto_with_minimal_data_when_update_is_executed_then_return_saved_dto();
  }
}
