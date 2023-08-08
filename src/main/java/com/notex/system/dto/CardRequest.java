package com.notex.system.dto;

import com.notex.system.enums.Status;
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

    @NotNull
    LocalDate appearance;

    @NotNull
    String companyCode;

    String companyName;

    @NotNull
    Status status;

}
