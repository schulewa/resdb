package com.apschulewitz.resdb.security.controller;

import com.apschulewitz.resdb.common.ApplicationResponse;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.security.UserAuthenticationService;
import com.apschulewitz.resdb.security.model.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(UserAuthenticationController.class)
public class UserAuthenticationControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private UserAuthenticationService mockedUserAuthenticationService;


  @Test
  public void givenUserNameAndPassword_whenAuthenticateUser_thenReturnToken() throws Exception {

    UserDto userDto = UserDto.builder().logonName("adrian").build();

    ApplicationResponse<UserDto> applicationResponse = new ApplicationResponse<>();

    given(mockedUserAuthenticationService.authenticateUser("adrian", "nepal1")).willReturn(applicationResponse);

    mvc.perform(
        get(RestUrlPaths.LOGIN_PAGE_URL)
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("", is(applicationResponse))
    );
  }

}
