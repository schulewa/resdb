package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.google.gson.Gson;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Ignore // TODO see Spring Boot issue:   https://github.com/spring-cloud/spring-cloud-config/issues/1142
public class AddressTypeControllerIT {

  @Autowired
  private MockMvc mockMvc;

  private Gson gson = new Gson();

  @Test
  public void given_valid_address_type_when_save_is_executed_then_save_data() throws Exception {
    AddressType home = AddressType.builder().name("Home").build();
    String jsonData = gson.toJson(home);
    mockMvc.perform(post(RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL)
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .content(jsonData))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(1)))
      .andExpect(jsonPath("$[0].name", is(home.getName())));
  }
}
