package com.apschulewitz.resdb;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(
  classes = ResearchDatabaseApplication.class,
  loader = SpringBootContextLoader.class
)
@TestPropertySource(locations = "classpath:application-bdd-test.yml")
@ActiveProfiles("bdd-test")
public class AbstractFeatureStepTest {
}
