package com.notex.notes.dto;

import jakarta.validation.constraints.NotBlank;

public class CompanyResponse {
    @NotBlank
    String name;
    String code;
}
