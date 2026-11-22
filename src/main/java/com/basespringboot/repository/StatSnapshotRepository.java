package com.basespringboot.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basespringboot.form.StatSnapshot;

public interface StatSnapshotRepository extends JpaRepository<StatSnapshot, Long> {

    Optional<StatSnapshot> findFirstByAccountIdOrderByDateDesc(Long accountId);

    List<StatSnapshot> findByAccountIdOrderByDateAsc(Long accountId);

    boolean existsByAccountIdAndDate(Long accountId, LocalDate date);

    boolean existsByAccountIdAndDateAndIdNot(Long accountId, LocalDate date, Long id);
}