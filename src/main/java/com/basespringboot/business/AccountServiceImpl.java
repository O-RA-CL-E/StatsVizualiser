package com.basespringboot.business;

import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basespringboot.dto.AccountRequest;
import com.basespringboot.dto.AccountResponse;
import com.basespringboot.exception.ConflictException;
import com.basespringboot.exception.NotFoundException;
import com.basespringboot.form.Account;
import com.basespringboot.form.StatSnapshot;
import com.basespringboot.repository.AccountRepository;
import com.basespringboot.repository.StatSnapshotRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final StatSnapshotRepository statSnapshotRepository;

    @Override
    public AccountResponse create(AccountRequest request) {

        if (accountRepository.existsByPlatformAndHandle(request.platform(), request.handle())) {
            throw new ConflictException(
                    "Le handle '" + request.handle() + "' existe déjà sur " + request.platform());
        }

        Account account = new Account();
        apply(account, request);
        return toResponse(accountRepository.save(account));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponse> findAll() {
        return accountRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AccountResponse findById(Long id) {
        return toResponse(getOrThrow(id));
    }

    @Override
    public AccountResponse update(Long id, AccountRequest request) {
        Account account = getOrThrow(id);

        if (accountRepository.existsByPlatformAndHandleAndIdNot(
                request.platform(), request.handle(), id)) {
            throw new ConflictException(
                    "Le handle '" + request.handle() + "' existe déjà sur " + request.platform());
        }

        apply(account, request);
        return toResponse(accountRepository.save(account));
    }

    @Override
    public void delete(Long id) {
        accountRepository.delete(getOrThrow(id));
    }

    private Account getOrThrow(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Compte introuvable : " + id));
    }

    private void apply(Account account, AccountRequest request) {
        account.setPlatform(request.platform());
        account.setHandle(request.handle());
        account.setLabel(request.label());
        account.setUrl(request.url());
    }

    private AccountResponse toResponse(Account account) {
        Long latestFollowers = statSnapshotRepository
                .findFirstByAccountIdOrderByDateDesc(account.getId())
                .map(StatSnapshot::getFollowers)
                .orElse(null);
        return AccountResponse.from(account, latestFollowers);
    }
}