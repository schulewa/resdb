package com.apschulewitz.resdb;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class ResearchDatabaseApplicationTests {

  // TODO fix Spring ApplicationContext not loading
  // Caused by: org.springframework.beans.factory.support.BeanDefinitionOverrideException: Invalid bean definition with name 'propertySourcesPlaceholderConfigurer

	@Test
	public void contextLoads() {
	}

}

