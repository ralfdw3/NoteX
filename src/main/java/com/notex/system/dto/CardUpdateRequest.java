package com.notex.system.dto;

import com.notex.system.models.Company;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class CardUpdateRequest {
    @NotBlank
    String id;
    @NotBlank
    String title;
    @NotBlank
    String description;
    LocalDate appearance;
    @NotNull
    Company company;
}
