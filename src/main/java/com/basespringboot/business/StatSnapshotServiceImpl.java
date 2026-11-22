package com.basespringboot.business;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basespringboot.dto.StatSnapshotRequest;
import com.basespringboot.dto.StatSnapshotResponse;
import com.basespringboot.exception.ConflictException;
import com.basespringboot.exception.NotFoundException;
import com.basespringboot.form.Account;
import com.basespringboot.form.StatSnapshot;
import com.basespringboot.repository.AccountRepository;
import com.basespringboot.repository.StatSnapshotRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class StatSnapshotServiceImpl implements StatSnapshotService {

    private final StatSnapshotRepository snapshotRepository;
    private final AccountRepository accountRepository;

    @Override
    public StatSnapshotResponse create(Long accountId, StatSnapshotRequest request) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Compte introuvable : " + accountId));

        if (snapshotRepository.existsByAccountIdAndDate(accountId, request.date())) {
            throw new ConflictException(
                    "Un relevé existe déjà pour le compte " + accountId + " le " + request.date());
        }

        StatSnapshot snapshot = new StatSnapshot();
        snapshot.setAccount(account);
        apply(snapshot, request);
        return StatSnapshotResponse.from(snapshotRepository.save(snapshot));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StatSnapshotResponse> findByAccount(Long accountId, LocalDate from, LocalDate to) {
        if (!accountRepository.existsById(accountId)) {
            throw new NotFoundException("Compte introuvable : " + accountId);
        }

        return snapshotRepository.findByAccountIdOrderByDateAsc(accountId).stream()
                .filter(s -> from == null || !s.getDate().isBefore(from))
                .filter(s -> to == null || !s.getDate().isAfter(to))
                .map(StatSnapshotResponse::from)
                .toList();
    }

    @Override
    public StatSnapshotResponse update(Long id, StatSnapshotRequest request) {
        StatSnapshot snapshot = getOrThrow(id);
        Long accountId = snapshot.getAccount().getId();

        if (snapshotRepository.existsByAccountIdAndDateAndIdNot(accountId, request.date(), id)) {
            throw new ConflictException(
                    "Un relevé existe déjà pour le compte " + accountId + " le " + request.date());
        }

        apply(snapshot, request);
        return StatSnapshotResponse.from(snapshotRepository.save(snapshot));
    }

    @Override
    public void delete(Long id) {
        snapshotRepository.delete(getOrThrow(id));
    }

    private StatSnapshot getOrThrow(Long id) {
        return snapshotRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Relevé introuvable : " + id));
    }

    private void apply(StatSnapshot snapshot, StatSnapshotRequest request) {
        snapshot.setDate(request.date());
        snapshot.setFollowers(request.followers());
        snapshot.setPosts(request.posts());
        snapshot.setImpressions(request.impressions());
        snapshot.setEngagementRate(request.engagementRate());
    }
}