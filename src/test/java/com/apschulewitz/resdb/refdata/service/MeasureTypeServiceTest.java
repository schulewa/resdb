package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.service.DataServiceTestRunner;
import com.apschulewitz.resdb.refdata.model.comparator.MeasureTypeDataComparator;
import com.apschulewitz.resdb.refdata.model.dao.MeasureTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.MeasureTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.MeasureType;
import com.apschulewitz.resdb.refdata.model.helper.MeasureTypeTestHelper;
import com.apschulewitz.resdb.refdata.model.mapper.MeasureTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
@WithMockUser("testuser")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Slf4j
public class MeasureTypeServiceTest {

  private DataServiceTestRunner<MeasureTypeDto,
    MeasureType,
    Long,
    MeasureTypeDataComparator,
    MeasureTypeTestHelper,
    MeasureTypeMapper,
    MeasureTypeDao,
    MeasureTypeService> testRunner;

  @Autowired
  private MeasureTypeTestHelper testHelper;

  @Autowired
  private MeasureTypeDataComparator testDataComparator;

  @Autowired
  private MeasureTypeService service;

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
