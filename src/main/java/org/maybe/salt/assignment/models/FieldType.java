package org.maybe.salt.assignment.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FieldType {
    @JsonProperty("Int")
    INT,
    @JsonProperty("String")
    STRING,
    @JsonProperty("Boolean")
    BOOLEAN,
    @JsonProperty("List")
    LIST,
    @JsonProperty("Date")
    DATE,
    @JsonProperty("Email")
    EMAIL,
    @JsonProperty("UUID")
    UUID,
    @JsonProperty("Auth-Token")
    AUTH_TOKEN
}
