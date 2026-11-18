package com.basespringboot.Controller;

import com.basespringboot.business.FormService;
import com.basespringboot.form.Form;
import com.basespringboot.dto.FormDto;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;

@RequestMapping ("/forms")
@RestController 
@RequiredArgsConstructor 

public class FormController {
    private final FormService formService;

    @PostMapping 
    @ResponseStatus (HttpStatus.CREATED)
    public Form save(@Valid @RequestBody FormDto form) {
        return formService.save(form);
    }
}