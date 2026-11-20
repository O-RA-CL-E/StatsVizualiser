package com.basespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basespringboot.form.StatSnapshot;

public interface StatSnapshotRepository extends JpaRepository<StatSnapshot, Long> {
}