package com.notex.notes.dto;

import jakarta.validation.constraints.NotBlank;

public class CompanyRequest {
    @NotBlank
    String name;
    String code;
}
