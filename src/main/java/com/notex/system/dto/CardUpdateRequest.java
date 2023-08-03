package com.notex.system.dto;

import com.notex.system.enums.Status;
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

    @NotNull
    LocalDate appearance;

    @NotNull
    String companyCode;

    @NotBlank
    String companyName;

    @NotNull
    Status status;

    @Override
    public String toString() {
        return "CardUpdateRequest{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", appearance=" + appearance +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", status=" + status +
                '}';
    }
}
