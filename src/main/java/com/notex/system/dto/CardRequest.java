package com.notex.system.dto;

import com.notex.system.models.Company;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CardRequest {
    @NotBlank
    String title;
    String description;
    LocalDate appearance;
    @NotNull
    Company company;
}
