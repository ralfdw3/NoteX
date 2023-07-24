package com.notex.system.dto;

import com.notex.system.models.Company;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CardRequest {
    @NotBlank
    String title;
    String description;
    LocalDate appearance;
    @NotNull
    Company company;
}
