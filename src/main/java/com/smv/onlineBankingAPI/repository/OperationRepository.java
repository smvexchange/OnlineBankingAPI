package com.smv.onlineBankingAPI.repository;

import com.smv.onlineBankingAPI.model.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<AccountOperation, Long> {
}
