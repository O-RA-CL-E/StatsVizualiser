package com.basespringboot.business;

import com.basespringboot.dto.AccountRequest;
import com.basespringboot.dto.AccountResponse;
import java.util.List;

public interface AccountService {

    AccountResponse create(AccountRequest request);

    List<AccountResponse> findAll();

    AccountResponse findById(Long id);

    AccountResponse update(Long id, AccountRequest request);

    void delete(Long id);
}