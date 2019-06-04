package com.apschulewitz.resdb.security.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;

@Data
@NoArgsConstructor
@SuppressWarnings("serial")
public class RestErrorList extends ArrayList<ErrorMessage> {

    private HttpStatus status;

    public RestErrorList(HttpStatus status, ErrorMessage... errors) {
        this(status.value(), errors);
    }

    public RestErrorList(int status, ErrorMessage... errors) {
        super();
        this.status = HttpStatus.valueOf(status);
        addAll(Arrays.asList(errors));
    }
}
