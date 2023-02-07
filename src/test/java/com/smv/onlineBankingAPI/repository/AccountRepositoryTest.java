package com.smv.onlineBankingAPI.repository;

import com.smv.onlineBankingAPI.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void testRepo() {
        for (int i = 0; i < 10; i++) {
            accountRepository.save(new Account());
        }
        final List<Account> found = accountRepository.findAll();
        assertEquals(10, found.size());
    }
}