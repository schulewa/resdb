package com.apschulewitz.resdb.common.controller;

import com.apschulewitz.resdb.common.ApplicationResponse;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Created by adrianschulewitz on 15/05/2017.
 */
@Slf4j
public class AbstractController<T, K extends Serializable> {

    public JSONObject findAll(CrudRepository<T, K> dao) {
        log.info("Processing request to find all entries");
        ApplicationResponse<T> applicationResponse = new ApplicationResponse<>();
        List<T> data = new ArrayList<>();
        Iterable<T> iter = dao.findAll();
        StreamSupport.stream(iter.spliterator(), false)
                .forEach(e -> data.add(e));
        applicationResponse.setData(data);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("response", applicationResponse);
        return jsonObject;
    }

}
