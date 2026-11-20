package com.basespringboot.controller;

import java.util.List;
import com.basespringboot.business.AccountService;
import com.basespringboot.dto.AccountRequest;
import com.basespringboot.dto.AccountResponse;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/accounts")
@RequiredArgsConstructor

public class AccountController {
    private final AccountService accountService;

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse create(@Valid @RequestBody AccountRequest request) {
        return accountService.create(request);
    }

    @GetMapping 
    public List<AccountResponse> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public AccountResponse findById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @PutMapping("/{id}")
    public AccountResponse update(@PathVariable Long id, @Valid @RequestBody AccountRequest request) {
        return accountService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }
}
