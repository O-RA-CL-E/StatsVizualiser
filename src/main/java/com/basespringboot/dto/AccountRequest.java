package com.basespringboot.dto;

import com.basespringboot.form.Platform;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public record AccountRequest (
    @NotNull(message = "la plateforme est obligatoire")
    Platform platform,
    
    @NotBlank(message = "le handle est obligatoire")
    @Size(max = 100, message = "le handle ne doit pas dépasser 100 caractères")
    String handle,

    @Size(max = 20, message = "le label ne doit pas dépasser 20 caractères")
    String label,

    @Size(max = 255, message = "l'url ne doit pas dépasser 255 caractères")
    String url
) {
}
