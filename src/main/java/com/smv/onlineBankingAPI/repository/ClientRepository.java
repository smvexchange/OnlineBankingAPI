package com.smv.onlineBankingAPI.repository;

import com.smv.onlineBankingAPI.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
