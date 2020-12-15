package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.CountryDao;
import com.apschulewitz.resdb.refdata.model.dto.CountryDto;
import com.apschulewitz.resdb.refdata.model.entity.Country;
import com.apschulewitz.resdb.refdata.service.CountryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CountryControllerTest {

    private LocalDateTime now = LocalDateTime.now();

    private CountryController controller;

    @Autowired
    private CountryDao countryDao;

    @MockBean
    private CountryService mockedService;

    @Before
    public void beforeEachTest() {
      countryDao.deleteAll();
        controller = new CountryController(mockedService);
    }

    @WithMockUser(value = "adrian")
    @Test
    public void when_findAll_is_executed_and_onlyactive_is_false_then_return_list() {
        // Given

        CountryDto unsaved1 = CountryDto.builder()
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.New.name())
                .build();

        CountryDto unsaved2 = CountryDto.builder()
                .code("AX")
                .name("Aland Islands")
                .stateName("Aland")
                .sovereignty("Finland")
                .createdBy("system")
                .status(VersionStatus.Cancel.name())
                .build();

        when(mockedService.findAll(false)).thenReturn(Arrays.asList(unsaved1, unsaved2));

        // When
        ResponseEntity<List<CountryDto>> responseEntityList = controller.findAll(false);

        // Then
        assertEquals(HttpStatus.OK, responseEntityList.getStatusCode());
        assertNotNull(responseEntityList.getBody());
        assertEquals(2, responseEntityList.getBody().size());
    }

    @WithMockUser(value = "adrian")
    @Test
    public void when_add_is_executed_then_return_saved_entity() {
        // Given
        CountryDto unsaved = CountryDto.builder()
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .status(VersionStatus.New.name())
                .build();

        CountryDto saved = CountryDto.builder()
                .id(1L)
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.New.name())
                .build();

        when(mockedService.add(unsaved)).thenReturn(saved);

        // When
        ResponseEntity<CountryDto> responseEntity = controller.add(unsaved);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getId());
    }

    @WithMockUser(value = "adrian")
    @Test
    public void when_delete_is_executed_then_return_mark_entity_as_cancelled() {
        // Given
        Country saved = Country.builder()
                .id(1L)
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.New)
                .build();

        CountryDto deleted = CountryDto.builder()
                .id(1L)
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.Cancel.name())
                .build();

        when(mockedService.deleteById(saved.getId())).thenReturn(deleted);

        // When
        ResponseEntity<CountryDto> responseEntity = controller.delete(saved.getId());

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(mockedService, times(1)).deleteById(anyLong());

        assertEquals(deleted, responseEntity.getBody());
    }

    @Test
    @WithMockUser(value = "adrian")
    public void when_update_is_executed_then_return_updated_entity() {
        // Given
        CountryDto saved = CountryDto.builder()
                .id(1L)
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.New.name())
                .build();

        CountryDto updated = CountryDto.builder()
                .id(1L)
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.Amend.name())
                .updatedBy("testuser")
                .build();

        when(mockedService.update(saved)).thenReturn(updated);

        // When
        ResponseEntity<CountryDto> responseUpdatedEntity = controller.update(updated);

        // Then
        assertNotNull(responseUpdatedEntity);
        assertEquals(HttpStatus.OK, responseUpdatedEntity.getStatusCode());
    }

}
