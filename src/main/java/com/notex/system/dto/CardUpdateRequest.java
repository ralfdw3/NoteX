package com.notex.system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class CardUpdateRequest {
    @NotBlank
    String id;
    @NotBlank
    String title;
    String description;
    LocalDate appearance;
    @NotNull
    Long companyCode;
}
