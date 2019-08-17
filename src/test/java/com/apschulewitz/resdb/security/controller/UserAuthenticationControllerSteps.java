package com.apschulewitz.resdb.security.controller;

import com.apschulewitz.resdb.AbstractFeatureStepTest;
import com.apschulewitz.resdb.ResearchDatabaseApplication;
import com.apschulewitz.resdb.security.model.dto.UserDto;
import com.apschulewitz.resdb.security.model.dto.UserLogonDto;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(
  classes = ResearchDatabaseApplication.class,
  loader = SpringBootContextLoader.class
)
@TestPropertySource(locations = "classpath:application-bdd-test.yml")
@ActiveProfiles("bdd-test")
public class UserAuthenticationControllerSteps extends AbstractFeatureStepTest {

  @Autowired
  private UserAuthenticationController controller;

  private UserLogonDto userLogonDto;

  @Given("I am not logged in")
  public void i_am_not_logged_in() {
    userLogonDto = new UserLogonDto();
  }

  // Valid login scenario

  @When("I supply a valid user name and password")
  public void i_supply_a_valid_user_and_password() {
    userLogonDto.setUserName("adrian");
    userLogonDto.setPassword("nepal1");
  }

  @Then("I should be authorised")
  public void i_should_be_authorised() {
    ResponseEntity<UserDto> responseEntity = controller.logon(userLogonDto);
    assertEquals("Response status should be OK", HttpStatus.OK, responseEntity.getStatusCode());
  }

  // Invalid login scenarios

  @When("I supply an invalid user name and password")
  public void i_supply_an_invalid_user_name_and_password() {
    userLogonDto.setUserName("no such user");
    userLogonDto.setPassword("and pwd combo");
  }

  @Then("I should not be authorised")
  public void i_should_not_be_authorised() {
    ResponseEntity<UserDto> responseEntity = controller.logon(userLogonDto);
    assertEquals("Response status should not be OK", HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
  }
}
