package com.basespringboot.dto;

import com.basespringboot.form.Platform;
import com.basespringboot.form.Account;
import java.time.LocalDateTime; 

public record AccountResponse(
        Long id,
        Platform platform,
        String handle,
        String label,
        String url,
        LocalDateTime createdAt
) {

    public static AccountResponse from(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getPlatform(),
                account.getHandle(),
                account.getLabel(),
                account.getUrl(),
                account.getCreatedAt()
        );
    }
}
