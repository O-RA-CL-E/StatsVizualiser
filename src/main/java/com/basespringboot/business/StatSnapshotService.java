package com.basespringboot.business;

import java.time.LocalDate;
import java.util.List;

import com.basespringboot.dto.StatSnapshotRequest;
import com.basespringboot.dto.StatSnapshotResponse;

public interface StatSnapshotService {

    StatSnapshotResponse create(Long accountId, StatSnapshotRequest request);

    List<StatSnapshotResponse> findByAccount(Long accountId, LocalDate from, LocalDate to);

    StatSnapshotResponse update(Long id, StatSnapshotRequest request);

    void delete(Long id);
}