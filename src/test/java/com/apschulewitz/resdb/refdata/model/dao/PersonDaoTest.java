package com.apschulewitz.resdb.refdata.model.dao;

import com.apschulewitz.resdb.common.model.entity.Gender;
import com.apschulewitz.resdb.common.model.entity.HistoricalDate;
import com.apschulewitz.resdb.common.model.entity.Title;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.entity.Person;
import com.apschulewitz.resdb.refdata.model.entity.Place;
import com.apschulewitz.resdb.research.model.dao.TitleDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
@Ignore
public class PersonDaoTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private TitleDao titleDao;

    private Title sir;

    @Before
    public void beforeEachTest() {
        // first delete all entries
        personDao.deleteAll();
        placeDao.deleteAll();
        titleDao.deleteAll();

        sir = Title.builder().title("Sir").build();
        titleDao.save(sir);
    }

    @Test
    public void given_valid_person_entity_when_save_is_executed_then_save_entity() {

        // Given

        Place campbellBirthPlace = constructNewPlace("Chislehurst", true);
        Place campbellDeathPlace = constructNewPlace("Reigate", true);

        HistoricalDate campbellBirth = HistoricalDate.builder()
                .day(11).month(Month.MARCH.getValue()).year(1885).build();

        HistoricalDate campbellDeath = HistoricalDate.builder()
                .day(31).month(Month.DECEMBER.getValue()).year(1948).build();

        Person unSavedCampbell = constructNewPerson("Malcolm",
                null,
                "Campbell",
                Gender.Male,
                campbellBirthPlace,
                campbellBirth,
                campbellDeathPlace,
                campbellDeath);


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

        Place campbellBirthPlace = constructNewPlace("Chislehurst", true);
        Place campbellDeathPlace = constructNewPlace("Reigate", true);

        Place sennaBirthPlace = constructNewPlace("Sao Paulo", true);
        Place sennaDeathPlace = constructNewPlace("Bologna", true);

        HistoricalDate campbellBirth = HistoricalDate.builder()
                .day(11).month(Month.MARCH.getValue()).year(1885).build();

        HistoricalDate campbellDeath = HistoricalDate.builder()
                .day(31).month(Month.DECEMBER.getValue()).year(1948).build();

        HistoricalDate sennaBirth = HistoricalDate.builder()
                .day(21).month(Month.MARCH.getValue()).year(1960).build();

        HistoricalDate sennaDeath = HistoricalDate.builder()
                .day(1).month(Month.MAY.getValue()).year(1994).build();

        Person unSavedCampbell = constructNewPerson("Malcolm",
                null,
                "Campbell",
                Gender.Male,
                campbellBirthPlace,
                campbellBirth,
                campbellDeathPlace,
                campbellDeath);


        Person unsavedSenna = constructNewPerson("Ayrton",
                null,
                "Senna da Silva",
                Gender.Male,
                sennaBirthPlace,
                sennaBirth,
                sennaDeathPlace,
                sennaDeath);

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
        assertEquals("All persons size", 2, allPersons);
        assertEquals("Cancelled persons size", 1, cancelledPersons);
        assertEquals("Cancelled person", "Ayrton", cancelledPersons.get(0).getFirstName());
    }

    private Place constructNewPlace(String name, boolean save) {
        Place unsavedPlace = Place.builder()
                .name(name)
                .build();

        Place savedPlace = null;

        if (save) {
            savedPlace = placeDao.save(unsavedPlace);
            return savedPlace;
        }

        return unsavedPlace;
    }

    private Person constructNewPerson(String firstName,
                                      String middleName,
                                      String familyName,
                                      Gender gender,
                                      Place birthPlace,
                                      HistoricalDate dateOfBirth,
                                      Place deathPlace,
                                      HistoricalDate dateOfDeath) {

        return Person.builder()
                .birthPlace(birthPlace)
                .createdBy("admin")
                .dateOfBirth(dateOfBirth)
                .dateOfDeath(dateOfDeath)
                .deathPlace(deathPlace)
                .familyName(familyName)
                .firstName(firstName)
                .gender(gender)
                .middleName(middleName)
                .prefixTitle(sir)
                .status(VersionStatus.New)
                .build();
    }

}
