package com.prm.mobile.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@Slf4j
public class ApiErrorDetail {
    private String field;
    private String issue;
    private Object value;
    private ErrorLocation location;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("field", field);
        map.put("issue", issue);
        map.put("value", value);
        map.put("location", location);
        return map;
    }
}
