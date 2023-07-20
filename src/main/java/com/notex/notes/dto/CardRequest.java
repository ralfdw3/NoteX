package com.notex.notes.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class CardRequest {
    @NotBlank
    String title;
    @NotBlank
    String description;
    LocalDate aperence;
}
