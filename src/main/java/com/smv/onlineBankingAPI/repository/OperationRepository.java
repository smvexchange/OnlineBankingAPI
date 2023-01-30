package com.smv.onlineBankingAPI.repository;

import com.smv.onlineBankingAPI.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
