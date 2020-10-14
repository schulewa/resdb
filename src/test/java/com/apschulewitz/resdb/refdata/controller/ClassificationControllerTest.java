package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.utils.StringUtils;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.ClassificationTestHelper;
import com.apschulewitz.resdb.refdata.model.entity.ClassificationCollection;
import com.apschulewitz.resdb.refdata.model.entity.ClassificationEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.apschulewitz.resdb.refdata.ClassificationTestHelper.ADORNMENT_NAME;
import static com.apschulewitz.resdb.refdata.ClassificationTestHelper.ANKLET_NAME;
import static com.apschulewitz.resdb.refdata.ClassificationTestHelper.ARMLET_NAME;
import static com.apschulewitz.resdb.refdata.ClassificationTestHelper.JEWELLERY_NAME;
import static com.apschulewitz.resdb.refdata.ClassificationTestHelper.TATTOO_NAME;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClassificationControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void given_valid_classification_when_save_is_executed_then_classification_is_saved() {
        ClassificationCollection unsavedCollection = ClassificationTestHelper.constructNewClassificationCollection("Artefact Classifications");

        // level 1

        ClassificationEntry adornmentEntry = ClassificationTestHelper.constructNewClassificationEntry(ADORNMENT_NAME, unsavedCollection);

        // level 2

        ClassificationEntry jewelleryEntry = ClassificationTestHelper.constructNewClassificationEntry(JEWELLERY_NAME, unsavedCollection);
        jewelleryEntry.setParentEntry(adornmentEntry);

        ClassificationEntry tattooEntry = ClassificationTestHelper.constructNewClassificationEntry(TATTOO_NAME, unsavedCollection);
        tattooEntry.setParentEntry(adornmentEntry);

        // level 3

        ClassificationEntry ankletEntry = ClassificationTestHelper.constructNewClassificationEntry(ANKLET_NAME, unsavedCollection);
        ankletEntry.setParentEntry(jewelleryEntry);

        ClassificationEntry armletEntry = ClassificationTestHelper.constructNewClassificationEntry(ARMLET_NAME, unsavedCollection);
        armletEntry.setParentEntry(jewelleryEntry);

        unsavedCollection.getEntries().add(adornmentEntry); // add level 1 to collection

        String url = constructRestUrl(RestUrlPaths.CLASSIFICATION_CONTROLLER_BASE_URL, port, "");
        ResponseEntity<ClassificationCollection> responseEntity = testRestTemplate.postForEntity(url, unsavedCollection, ClassificationCollection.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    private String constructRestUrl(String baseUrl, int port, String endpoint) {
        if (StringUtils.isEmpty(endpoint)) {
            return String.format("%s:%d/%s", "http://localhost:", port, baseUrl);
        } else {
            return String.format("%s:%d/%s/%s", "http://localhost:", port, baseUrl, endpoint);
        }
    }

    private String constructRestUrl(String baseUrl, int port) {
        return constructRestUrl(baseUrl, port, "");
    }
}
