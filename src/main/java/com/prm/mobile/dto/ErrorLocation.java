package com.prm.mobile.dto;

import lombok.Getter;

@Getter
public enum ErrorLocation {
    BODY("body"), PATH("path"), QUERY("query");
    private String location;

    ErrorLocation(String location) {
        this.location = location;
    }
}
