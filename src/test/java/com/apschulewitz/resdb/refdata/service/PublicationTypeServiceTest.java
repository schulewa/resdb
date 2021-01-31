package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.service.DataServiceTestRunner;
import com.apschulewitz.resdb.refdata.model.comparator.PersonTypeDataComparator;
import com.apschulewitz.resdb.refdata.model.comparator.PublicationTypeDataComparator;
import com.apschulewitz.resdb.refdata.model.dao.PersonTypeDao;
import com.apschulewitz.resdb.refdata.model.dao.PublicationTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.PersonTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.PublicationTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.PersonType;
import com.apschulewitz.resdb.refdata.model.entity.PublicationType;
import com.apschulewitz.resdb.refdata.model.helper.PersonTypeTestHelper;
import com.apschulewitz.resdb.refdata.model.helper.PublicationTypeTestHelper;
import com.apschulewitz.resdb.refdata.model.mapper.PersonTypeMapper;
import com.apschulewitz.resdb.refdata.model.mapper.PublicationTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@WithMockUser("testuser")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Slf4j
public class PublicationTypeServiceTest {

  private DataServiceTestRunner<PublicationTypeDto,
    PublicationType,
    Long,
    PublicationTypeDataComparator,
    PublicationTypeTestHelper,
    PublicationTypeMapper,
    PublicationTypeDao,
    PublicationTypeService> testRunner;

  @Autowired
  private PublicationTypeTestHelper testHelper;

  @Autowired
  private PublicationTypeDataComparator testDataComparator;

  @Autowired
  private PublicationTypeService service;

  @Before
  public void beforeEachTest() {
    testRunner = new DataServiceTestRunner<>(testHelper, service, testDataComparator);
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
