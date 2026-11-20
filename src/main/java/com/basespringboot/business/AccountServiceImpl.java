package com.basespringboot.business;

import com.basespringboot.dto.AccountRequest;
import com.basespringboot.dto.AccountResponse;
import com.basespringboot.exception.ConflictException;
import com.basespringboot.exception.NotFoundException;
import com.basespringboot.form.Account;
import com.basespringboot.repository.AccountRepository;
import com.basespringboot.exception.ConflictException;
import com.basespringboot.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountResponse create(AccountRequest request) {
        
        if (accountRepository.existsByPlatformAndHandle(request.platform(), request.handle())) {
            throw new ConflictException(
                    "Le handle '" + request.handle() + "' existe déjà sur " + request.platform());
        }

        Account account = new Account();
        apply(account, request);
        return AccountResponse.from(accountRepository.save(account));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponse> findAll() {
        return accountRepository.findAll().stream()
                .map(AccountResponse::from)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AccountResponse findById(Long id) {
        return AccountResponse.from(getOrThrow(id));
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
        return AccountResponse.from(accountRepository.save(account));
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
}