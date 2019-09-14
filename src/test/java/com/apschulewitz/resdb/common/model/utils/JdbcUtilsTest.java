package com.apschulewitz.resdb.common.model.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JdbcUtilsTest {

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private JdbcUtils jdbcUtils;

  @Test
  public void testSpringContext() {
    assertNotNull(applicationContext);
    assertNotNull(jdbcUtils);
  }

}
