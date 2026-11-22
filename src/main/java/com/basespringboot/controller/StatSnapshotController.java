package com.basespringboot.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.basespringboot.business.StatSnapshotService;
import com.basespringboot.dto.StatSnapshotRequest;
import com.basespringboot.dto.StatSnapshotResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StatSnapshotController {

    private final StatSnapshotService snapshotService;

    @PostMapping("/accounts/{accountId}/snapshots")
    @ResponseStatus(HttpStatus.CREATED)
    public StatSnapshotResponse create(@PathVariable Long accountId,
                                       @Valid @RequestBody StatSnapshotRequest request) {
        return snapshotService.create(accountId, request);
    }

    @GetMapping("/accounts/{accountId}/snapshots")
    public List<StatSnapshotResponse> findByAccount(
            @PathVariable Long accountId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return snapshotService.findByAccount(accountId, from, to);
    }

    @PutMapping("/snapshots/{id}")
    public StatSnapshotResponse update(@PathVariable Long id,
                                       @Valid @RequestBody StatSnapshotRequest request) {
        return snapshotService.update(id, request);
    }

    @DeleteMapping("/snapshots/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        snapshotService.delete(id);
    }
}