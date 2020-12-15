package com.apschulewitz.resdb.refdata.model.dao;

import com.apschulewitz.resdb.common.model.entity.Altitude;
import com.apschulewitz.resdb.common.model.entity.Gender;
import com.apschulewitz.resdb.common.model.entity.HistoricalDate;
import com.apschulewitz.resdb.common.model.entity.Latitude;
import com.apschulewitz.resdb.common.model.entity.Longitude;
import com.apschulewitz.resdb.common.model.entity.Title;
import com.apschulewitz.resdb.common.model.entity.TitleType;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.entity.Person;
import com.apschulewitz.resdb.refdata.model.entity.Place;
import com.apschulewitz.resdb.research.model.PersonTestHelper;
import com.apschulewitz.resdb.research.model.PlaceTestHelper;
import com.apschulewitz.resdb.research.model.dao.TitleDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class PersonDaoTest {

  private static final String USER_NAME = "testuser";

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private TitleDao titleDao;

    private Title sir;

  private Altitude altitude;
  private Latitude latitude;
  private Longitude longitude;

    @Before
    public void beforeEachTest() {
        // first delete all entries
        personDao.deleteAll();
        placeDao.deleteAll();
        titleDao.deleteAll();

      altitude = new Altitude();
      altitude.setValue("altitude");

      latitude = new Latitude();
      latitude.setValue("latitude");

      longitude = new Longitude();
      longitude.setValue("longitude");

        sir = Title.builder().appliesTo(Gender.Male).createdBy(USER_NAME).title("Sir").titleType(TitleType.Prefix).build();
        titleDao.save(sir);
    }

    @Test
    public void given_valid_person_entity_when_save_is_executed_then_save_entity() {

        // Given

        Place campbellBirthPlace = PlaceTestHelper.constructNewPlace(altitude,latitude, longitude, "Chislehurst", null);
        Place campbellDeathPlace = PlaceTestHelper.constructNewPlace(altitude, latitude, longitude, "Reigate", null);

        HistoricalDate campbellDateOfBirth = HistoricalDate.builder()
                .day(11).month(Month.MARCH.getValue()).year(1885).build();

        HistoricalDate campbellDateOfDeath = HistoricalDate.builder()
                .day(31).month(Month.DECEMBER.getValue()).year(1948).build();

      Person unSavedCampbell = PersonTestHelper.constructNewPerson(
        "Malcolm",
        null,
        "Campbell",
        Gender.Male,
        campbellDateOfBirth,
        campbellBirthPlace,
        campbellDateOfDeath,
        campbellDeathPlace,
        sir,
        null,
        null
      );

        // When

        Person savedCampbell = personDao.save(unSavedCampbell);

        // Then

        assertNotNull("Person saved", savedCampbell);
        assertNotNull("Person id", savedCampbell.getId());
        assertEquals("Person first name", unSavedCampbell.getFirstName(), savedCampbell.getFirstName());
        assertEquals("Person family name", unSavedCampbell.getFamilyName(), savedCampbell.getFamilyName());

        assertEquals("Person createdBy", unSavedCampbell.getCreatedBy(), savedCampbell.getCreatedBy());
        assertEquals("Person status", unSavedCampbell.getStatus(), savedCampbell.getStatus());

        Iterable<Person> liveEntries = personDao.findByStatusIn(VersionStatus.getLiveStatuses());
        List<Person> persons = StreamSupport.stream(liveEntries.spliterator(), false).collect(Collectors.toList());
        assertEquals("Active persons size", 1, persons.size());
    }

    @Test
    public void given_some_live_and_some_entries_when_findByStatusIn_is_executed_then_retrieve_active_entries() {

        // Given

        Place campbellBirthPlace = PlaceTestHelper.constructNewPlace(altitude, latitude, longitude, "Chislehurst", null);
        Place campbellDeathPlace = PlaceTestHelper.constructNewPlace(altitude, latitude, longitude, "Reigate", null);

        Place sennaBirthPlace = PlaceTestHelper.constructNewPlace(altitude, latitude, longitude,"Sao Paulo", null);
        Place sennaDeathPlace = PlaceTestHelper.constructNewPlace(altitude, latitude, longitude,"Bologna", null);

        HistoricalDate campbellDateOfBirth = HistoricalDate.builder()
                .day(11).month(Month.MARCH.getValue()).year(1885).build();

        HistoricalDate campbellDateOfDeath = HistoricalDate.builder()
                .day(31).month(Month.DECEMBER.getValue()).year(1948).build();

        HistoricalDate sennaDateOfBirth = HistoricalDate.builder()
                .day(21).month(Month.MARCH.getValue()).year(1960).build();

        HistoricalDate sennaDateOfDeath = HistoricalDate.builder()
                .day(1).month(Month.MAY.getValue()).year(1994).build();

        Person unSavedCampbell = PersonTestHelper.constructNewPerson(
          "Malcolm",
          null,
          "Campbell",
          Gender.Male,
          campbellDateOfBirth,
          campbellBirthPlace,
          campbellDateOfDeath,
          campbellDeathPlace,
          sir,
          null,
          null
          );

      Person unsavedSenna = PersonTestHelper.constructNewPerson(
        "Ayrton",
        null,
        "Senna da Silva",
        Gender.Male,
        sennaDateOfBirth,
        sennaBirthPlace,
        sennaDateOfDeath,
        sennaDeathPlace,
        null,
        null,
        null
      );

        // When
        Person savedMalcolmCampbell = personDao.save(unSavedCampbell);

        Person savedAyrtonSenna = personDao.save(unsavedSenna);
        savedAyrtonSenna.setStatus(VersionStatus.Cancel);
        personDao.save(savedAyrtonSenna);

        List<Person> livePersons = personDao.findByStatusIn(VersionStatus.getLiveStatuses());

        Iterable<Person> allPersonsIter = personDao.findAll();
        List<Person> allPersons = StreamSupport.stream(allPersonsIter.spliterator(), false).collect(Collectors.toList());

        List<Person> cancelledPersons = personDao.findByStatusIn(Arrays.asList(VersionStatus.Cancel));

        // Then
        assertNotNull("Malcolm Campbell id", savedMalcolmCampbell.getId());
        assertEquals("Live persons size", 1, livePersons.size());
        assertEquals("All persons size", 2, allPersons.size());
        assertEquals("Cancelled persons size", 1, cancelledPersons.size());
        assertEquals("Cancelled person", "Ayrton", cancelledPersons.get(0).getFirstName());
    }

}
