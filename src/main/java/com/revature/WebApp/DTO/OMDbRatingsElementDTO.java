package com.revature.WebApp.DTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * This DTO is used to store the ratings collection that is part of the OMDb search results object
 */
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class OMDbRatingsElementDTO {
    private String source;
    private String value;

    public OMDbRatingsElementDTO() {
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}


