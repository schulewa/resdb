package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.AddressTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@WebMvcTest(AddressTypeController.class)
public class AddressTypeControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private AddressTypeDao mockedDao;

  @WithMockUser(value = "adrian")
  @Test
  public void givenNone_whenGetAddressTypes_thenReturnJsonArray() throws Exception {

    List<AddressType> data = new ArrayList<>();
    AddressType datum = AddressType.builder().id(1L).name("Test").build();
    data.add(datum);

    given(mockedDao.findAll()).willReturn(data);

    mvc.perform(
      get(RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL)
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(1)))
      .andExpect(jsonPath("$[0].id", is(datum.getId())))
      .andExpect(jsonPath("$[0].name", is(datum.getName())));
  }
}
