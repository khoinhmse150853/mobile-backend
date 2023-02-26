package com.prm.mobile.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApiErrorResponse {
    private String errorId;
    private String message;
    private List<Object> details;
}
