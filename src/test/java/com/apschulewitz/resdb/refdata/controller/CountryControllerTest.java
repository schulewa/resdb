package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.CountryDao;
import com.apschulewitz.resdb.refdata.model.entity.Country;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    @MockBean
    private CountryDao mockedDao;

    private HttpServletRequest mockedRequest = mock(HttpServletRequest.class);

    @Before
    public void beforeEachTest() {
        mockedDao.deleteAll();
        controller = new CountryController(mockedDao);
    }

    @WithMockUser(value = "adrian")
    @Test
    public void given_none_when_findAll_is_executed_then_return_list() {
        // Given

        Country unsaved1 = Country.builder()
                .code("AF")
                .name("Afghanistan")
                .stateName("The Islamic Republic of Afghanistan")
                .sovereignty("UN Member State")
                .createdBy("system")
                .status(VersionStatus.New)
                .build();

        Country unsaved2 = Country.builder()
                .code("AX")
                .name("Aland Islands")
                .stateName("Aland")
                .sovereignty("Finland")
                .createdBy("system")
                .status(VersionStatus.New)
                .build();

        when(mockedDao.findByStatusIn(VersionStatus.getLiveStatuses())).thenReturn(Arrays.asList(unsaved1, unsaved2));

        // When
        ResponseEntity<List<Country>> responseEntityList = controller.findAll();

        // Then
        assertEquals(HttpStatus.OK, responseEntityList.getStatusCode());
        assertNotNull(responseEntityList.getBody());
        assertEquals(2, responseEntityList.getBody().size());
    }

    @WithMockUser(value = "adrian")
    @Test
    public void given_entity_when_save_is_executed_then_return_saved_entity() {
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

        when(mockedDao.save(unsaved)).thenReturn(saved);

        // When
        ResponseEntity<Country> responseEntity = controller.add(mockedRequest, unsaved);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getId());
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

        when(mockedDao.save(unsaved)).thenReturn(saved);

        when(mockedDao.save(saved)).thenReturn(deleted);

        when(mockedDao.findById(saved.getId())).thenReturn(Optional.of(saved));

        // When
        ResponseEntity<Country> responseEntity = controller.delete(saved.getId());

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(mockedDao, times(1)).save(any(Country.class));

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

        when(mockedDao.save(unsaved)).thenReturn(saved);

        when(mockedDao.findById(saved.getId())).thenReturn(Optional.empty());

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

        when(mockedDao.save(unsaved)).thenReturn(saved);

        when(mockedDao.save(saved)).thenReturn(updated);

        when(mockedDao.save(updated)).thenReturn(updated);

        when(mockedDao.findById(saved.getId())).thenReturn(Optional.of(saved));

        // When
        ResponseEntity<Country> responseEntity = controller.add(mockedRequest, unsaved);

        ResponseEntity<Country> responseUpdatedEntity = controller.update(updated);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getId());

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

        when(mockedDao.save(unsaved)).thenReturn(saved);

        when(mockedDao.save(saved)).thenReturn(updated);

        when(mockedDao.save(updated)).thenReturn(updated);

        when(mockedDao.findById(saved.getId())).thenReturn(Optional.empty());

        // When
        ResponseEntity<Country> responseEntity = controller.add(mockedRequest, unsaved);

        ResponseEntity<Country> responseUpdatedEntity = controller.update(updated);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getId());

        assertNotNull(responseUpdatedEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseUpdatedEntity.getStatusCode());
    }
}
