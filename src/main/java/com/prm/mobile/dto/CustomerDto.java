package com.prm.mobile.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    @NotBlank
    private String userName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
