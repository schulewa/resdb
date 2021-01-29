package com.apschulewitz.resdb.research.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.research.model.dto.ClassificationCollectionDto;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Adrian.Schulewitz
 * @version $Id: ClassificationControllerIT.java, v 0.1 2020-10-18 18:16 adrianschulewitz.hds Exp $$
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClassificationControllerIT {

  @Autowired
  private MockMvc mockMvc;

  private Gson gson = new Gson();

  @Test
  public void when_findAll_is_executed_then_return_ok() throws Exception {
    mockMvc.perform(
      get(RestUrlPaths.CLASSIFICATION_CONTROLLER_BASE_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(status().isOk());
  }

  @Test
  public void given_valid_classification_and_no_entries_when_save_is_executed_then_return_ok() throws Exception {
    ClassificationCollectionDto dto = new ClassificationCollectionDto();
    dto.setName("Test Collection");
    String json = gson.toJson(dto);

    mockMvc.perform(
      post(RestUrlPaths.CLASSIFICATION_CONTROLLER_BASE_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(status().isOk());
  }

  @Test
  public void given_valid_classification_and_id_and_status_and_no_entries_when_save_is_executed_then_return_ok() throws Exception {
    ClassificationCollectionDto dto = new ClassificationCollectionDto();
    dto.setId(1L);
    dto.setName("Test Collection");
    dto.setStatus(VersionStatus.New.name());
    String json = gson.toJson(dto);

    mockMvc.perform(
      post(RestUrlPaths.CLASSIFICATION_CONTROLLER_BASE_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(status().isOk());
  }
}
