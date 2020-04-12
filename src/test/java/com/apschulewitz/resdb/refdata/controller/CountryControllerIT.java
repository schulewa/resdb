package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.ResearchDatabaseApplication;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.CountryDao;
import com.apschulewitz.resdb.refdata.model.dao.ImageDao;
import com.apschulewitz.resdb.refdata.model.dao.ImageTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.Country;
import com.apschulewitz.resdb.refdata.model.entity.Image;
import com.apschulewitz.resdb.refdata.model.entity.ImageType;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ResearchDatabaseApplication.class)
@AutoConfigureMockMvc
public class CountryControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CountryController controller;

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private ImageTypeDao imageTypeDao;

    private Gson gson = new Gson();

    @Before
    public void beforeEachTest() {
        countryDao.deleteAll();
    }

    @WithMockUser(value = "adrian")
    @Test
    public void given_none_when_findAll_is_executed_then_return_list() throws Exception {
        // Given

        Country unsaved = Country.builder()
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.New)
                .build();

        Country saved = countryDao.save(unsaved);

        // When
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders
                .get(RestUrlPaths.COUNTRY_CONTROLLER_BASE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andReturn();

        // Then
        assertNotNull(saved);
        assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE, mvcResult.getResponse().getContentType());
    }

    @WithMockUser(value = "adrian")
    @Test
    public void given_entity_with_mandatory_fields_when_save_is_executed_then_return_saved_entity() throws Exception {
        // Given
        Country unsaved = Country.builder()
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.New)
                .build();

        // When
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders
                        .post(RestUrlPaths.COUNTRY_CONTROLLER_BASE_URL)
                        .content(gson.toJson(unsaved))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andReturn();

        // Then
        assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE, mvcResult.getResponse().getContentType());
    }

    @WithMockUser(value = "adrian")
    @Test
    public void given_entity_with_all_fields_when_save_is_executed_then_return_saved_entity() throws Exception {
        // Given
        ImageType unsavedImageType = ImageType.builder()
                .name("Test image type")
                .createdBy("system")
                .status(VersionStatus.New)
                .build();

        ImageType savedImageType = imageTypeDao.save(unsavedImageType);

        Image unsavedFlagImage = Image.builder()
                .imageType(savedImageType)
                .filePath("/test/path")
                .fileName("test-file-name")
                .build();

        Image savedFlagImage = imageDao.save(unsavedFlagImage);

        Country unsavedCountry = Country.builder()
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .flagImage(savedFlagImage)
                .createdBy("system")
                .status(VersionStatus.New)
                .build();

        String json = gson.toJson(unsavedCountry);

        // TODO - controller.add(*) and countryDao.save(unsavedCountry) both work; the issue is when pushing the data through MockMvc.
        
        // When
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders
                        .post(RestUrlPaths.COUNTRY_CONTROLLER_BASE_URL)
                        .content(json) // gson.toJson(unsavedCountry)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andReturn();

        // Then
        assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE, mvcResult.getResponse().getContentType());
    }

    @WithMockUser(value = "adrian")
    @Test
    public void given_existing_entity_when_delete_is_executed_then_return_mark_entity_as_cancelled() {
        // Given
        Country unsaved = Country.builder()
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.New)
                .build();

        Country saved = Country.builder()
                .id(1L)
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.New)
                .build();

        Country deleted = Country.builder()
                .id(1L)
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.New)
                .build();

        when(countryDao.save(unsaved)).thenReturn(saved);

        when(countryDao.save(saved)).thenReturn(deleted);

        when(countryDao.findById(saved.getId())).thenReturn(Optional.of(saved));

        // When
        ResponseEntity<Country> responseEntity = controller.delete(saved.getId());

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(countryDao, times(1)).save(any(Country.class));

        assertEquals(deleted, responseEntity.getBody());
    }

    @WithMockUser(value = "adrian")
    @Test
    public void given_nonexisting_entity_when_delete_is_executed_then_return_not_found() {
        // Given
        Country unsaved = Country.builder()
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.New)
                .build();

        Country saved = Country.builder()
                .id(1L)
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.Amend)
                .build();

        Country deleted = Country.builder()
                .id(1L)
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.New)
                .build();

        when(countryDao.save(unsaved)).thenReturn(saved);

        when(countryDao.findById(saved.getId())).thenReturn(Optional.empty());

        // When
        ResponseEntity<Country> responseEntity = controller.delete(saved.getId());

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    @WithMockUser(value = "adrian")
    public void given_new_entity_when_update_is_executed_then_return_updated_entity() {
        // Given
        Country unsaved = Country.builder()
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.New)
                .build();

        Country saved = Country.builder()
                .id(1L)
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.New)
                .build();

        Country updated = Country.builder()
                .id(1L)
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.Amend)
                .build();

        when(countryDao.save(unsaved)).thenReturn(saved);

        when(countryDao.save(saved)).thenReturn(updated);

        when(countryDao.save(updated)).thenReturn(updated);

        when(countryDao.findById(saved.getId())).thenReturn(Optional.of(saved));

        // When
//        ResponseEntity<Country> responseEntity = controller.add(mockedRequest, unsaved);

        ResponseEntity<Country> responseUpdatedEntity = controller.update(updated);

        // Then
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertNotNull(responseEntity.getBody().getId());

        assertNotNull(responseUpdatedEntity);
        assertEquals(HttpStatus.OK, responseUpdatedEntity.getStatusCode());
    }

    @Test
    @WithMockUser(value = "adrian")
    public void given_unknown_entity_when_update_is_executed_then_return_notfound() {
        // Given
        Country unsaved = Country.builder()
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.New)
                .build();

        Country saved = Country.builder()
                .id(1L)
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.New)
                .build();

        Country updated = Country.builder()
                .id(1L)
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.Amend)
                .build();

        when(countryDao.save(unsaved)).thenReturn(saved);

        when(countryDao.save(saved)).thenReturn(updated);

        when(countryDao.save(updated)).thenReturn(updated);

        when(countryDao.findById(saved.getId())).thenReturn(Optional.empty());

        // When
//        ResponseEntity<Country> responseEntity = controller.add(mockedRequest, unsaved);

        ResponseEntity<Country> responseUpdatedEntity = controller.update(updated);

        // Then
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertNotNull(responseEntity.getBody().getId());

        assertNotNull(responseUpdatedEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseUpdatedEntity.getStatusCode());
    }
}
