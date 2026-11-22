package com.basespringboot.dto;

import java.time.LocalDate;

import com.basespringboot.form.StatSnapshot;

public record StatSnapshotResponse(
    Long id,
    Long accountId,
    LocalDate date,
    Long followers,
    Integer posts,
    Long impressions,
    Double engagementRate
) {

    public static StatSnapshotResponse from(StatSnapshot snapshot) {
        return new StatSnapshotResponse(
            snapshot.getId(),
            snapshot.getAccount().getId(),
            snapshot.getDate(),
            snapshot.getFollowers(),
            snapshot.getPosts(),
            snapshot.getImpressions(),
            snapshot.getEngagementRate()
        );
    }
}