package com.basespringboot.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

public record StatSnapshotRequest(
    @NotNull(message = "la date est obligatoire")
    @PastOrPresent(message = "la date ne doit pas être dans le futur")
    LocalDate date,

    @NotNull(message = "le nombre de followers est obligatoire")
    @PositiveOrZero(message = "le nombre de followers doit être positif ou nul")
    Long followers,

    @PositiveOrZero(message = "le nombre de posts doit être positif ou nul")
    Integer posts,

    @PositiveOrZero(message = "le nombre d'impressions doit être positif ou nul")
    Long impressions,

    @PositiveOrZero(message = "le taux d'engagement doit être positif ou nul")
    Double engagementRate
) {
}