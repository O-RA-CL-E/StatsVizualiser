package com.basespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.basespringboot.form.Account;
import com.basespringboot.form.Platform;

@Repository 
public interface AccountRepository extends JpaRepository<Account, Long>{
    boolean existsByPlatformAndHandle(Platform platform, String handle);
    boolean existsByPlatformAndHandleAndIdNot(Platform platform, String handle, Long id);
}