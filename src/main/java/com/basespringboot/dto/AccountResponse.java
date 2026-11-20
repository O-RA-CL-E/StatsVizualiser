package com.basespringboot.dto;

import java.time.LocalDateTime;

import com.basespringboot.form.Platform;
import com.basespringboot.form.Account;
 

public record AccountResponse(
        Long id,
        Platform platform,
        String handle,
        String label,
        String url,
        LocalDateTime createdAt,
        Long latestFollowers
) {

    public static AccountResponse from(Account account, Long latestFollowers) {
        return new AccountResponse(
                account.getId(),
                account.getPlatform(),
                account.getHandle(),
                account.getLabel(),
                account.getUrl(),
                account.getCreatedAt(),
                latestFollowers
        );
    }
}
