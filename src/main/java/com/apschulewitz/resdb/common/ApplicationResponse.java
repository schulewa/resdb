package com.apschulewitz.resdb.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrianschulewitz on 22/04/2017.
 */
@Data
@Builder
@AllArgsConstructor
public class ApplicationResponse<T> {

    private ResponseStatus responseStatus;
    private String message;
    private List<T> data;


    @Tolerate
    public ApplicationResponse() {
        super();
        data = new ArrayList<>();
        responseStatus = ResponseStatus.OK;
    }

    public ApplicationResponse(ResponseStatus status, String message) {
        super();
        this.message = message;
        this.responseStatus = status;
    }

    public ApplicationResponse(List<T> data) {
        super();
        this.data = new ArrayList<>();
        this.data.addAll(data);
        responseStatus = ResponseStatus.OK;
    }

    public ApplicationResponse(T datum) {
        this.data = new ArrayList<>();
        data.add(datum);
        responseStatus = ResponseStatus.OK;
    }

}
