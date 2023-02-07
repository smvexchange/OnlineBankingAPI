package com.smv.onlineBankingAPI.repository;

import com.smv.onlineBankingAPI.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
