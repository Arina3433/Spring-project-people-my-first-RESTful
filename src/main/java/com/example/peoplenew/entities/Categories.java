package com.example.peoplenew.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Categories {
    @JsonProperty("check")
    CHECK,
    @JsonProperty("monitoring")
    MONITORING,
    @JsonProperty("realization")
    REALIZATION

}
