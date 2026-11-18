package com.basespringboot.dto;

import lombok.*;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter 

public class FormDto {
    @NotBlank
    private String title;
}