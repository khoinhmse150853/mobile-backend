package com.prm.mobile.exception;

import com.google.common.collect.ImmutableMap;
import com.prm.mobile.dto.ApiErrorDetail;
import com.prm.mobile.dto.ApiErrorResponse;
import com.prm.mobile.dto.ErrorLocation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> methodArgumentNotValidException(HttpServletRequest request,
                                                                            MethodArgumentNotValidException ex) {
        List<Object> errorDetails = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> toErrorDetail(e, ErrorLocation.BODY))
                .collect(Collectors.toList());

        log.error("method=methodArgumentNotValidException, endPoint={}, errorDetails={}, exception={}",
                request.getRequestURI(),
                errorDetails,
                ex.getMessage());

        var errResp = getErrorResponse(ex.getStatusCode().toString(), ex.getTitleMessageCode(), errorDetails);

        return ResponseEntity.badRequest().body(errResp);
    }

    @ExceptionHandler(ExistedEntityException.class)
    public ResponseEntity<ApiErrorResponse> existedEntityException(HttpServletRequest request, ExistedEntityException ex) {
        log.error("method=existedEntityException, endPoint={}, exception={}",
                request.getRequestURI(),
                ex.getMessage());

        var httpCode = ex.getCode();
        var message = ex.getMessage();

        var errRes = getErrorResponse(ex.getCode(), message, Collections.emptyList());

        return ResponseEntity.status(Integer.parseInt(httpCode)).body(errRes);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> resourceNotFoundException(HttpServletRequest request, ResourceNotFoundException ex) {
        log.error("method=resourceNotFoundException, endPoint={}, exception={}",
                request.getRequestURI(),
                ex.getMessage());

        var httpCode = ex.getCode();
        var message = ex.getMessage();

        var errRes = getErrorResponse(ex.getCode(), message, Collections.emptyList());

        return ResponseEntity.status(Integer.parseInt(httpCode)).body(errRes);
    }

    private Map<String, Object> toErrorDetail(ObjectError error, ErrorLocation location) {
        var errMsg = Optional.ofNullable(error.getDefaultMessage()).orElse("");

        if (error instanceof FieldError) {
            var fieldError = (FieldError) error;

            return ApiErrorDetail.builder()
                    .field(fieldError.getField())
                    .issue(errMsg)
                    .location(location)
                    .value(fieldError.getRejectedValue())
                    .build()
                    .toMap();
        } else {
            return ImmutableMap.of("issue", errMsg);
        }
    }

    private ApiErrorResponse getErrorResponse(String errorCode, String message, List<Object> details) {
        return ApiErrorResponse.builder()
                .errorId(errorCode)
                .message(message)
                .details(details)
                .build();
    }
}
