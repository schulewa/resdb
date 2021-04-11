package com.apschulewitz.resdb.common;

import com.apschulewitz.resdb.ResearchDatabaseApplication;
import com.apschulewitz.resdb.config.model.dao.SystemParameterDao;
import com.apschulewitz.resdb.config.model.entity.SystemParameter;
import com.apschulewitz.resdb.config.model.entity.SystemParameterTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ResearchDatabaseApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SystemConfigurationTest {

  private SystemConfiguration systemConfiguration;

  @Autowired
  private SystemParameterDao systemParameterDao;

  @Test
  public void given_boolean_parameter_when_getBooleanParameter_is_executed_then_return_parameter() {
    // given
    SystemParameter boolParam1 = new SystemParameter();
    boolParam1.setName(SystemParameterTypes.SEND_USER_VERIFICATION_EMAIL_WHEN_REGISTERING.name());
    boolParam1.setBooleanValue(false);
    systemParameterDao.save(boolParam1);
    systemConfiguration = new SystemConfiguration(systemParameterDao);

    // when
    Boolean value = systemConfiguration.getBooleanParameter(SystemParameterTypes.SEND_USER_VERIFICATION_EMAIL_WHEN_REGISTERING);

    // then
    assertNotNull(value);
    assertFalse(value);
  }

  @Test
  public void given_integer_parameter_when_getIntegerParameter_is_executed_then_return_parameter() {
    // given
    SystemParameter intParam1 = new SystemParameter();
    intParam1.setName(SystemParameterTypes.MAXIMUM_ALLOWED_LOGIN_ATTEMPTS.name());
    intParam1.setIntegerValue(3);
    systemParameterDao.save(intParam1);
    systemConfiguration = new SystemConfiguration(systemParameterDao);

    // when
    Integer value = systemConfiguration.getIntegerParameter(SystemParameterTypes.MAXIMUM_ALLOWED_LOGIN_ATTEMPTS);

    // then
    assertNotNull(value);
    assertEquals(3, value.intValue());
  }



}
