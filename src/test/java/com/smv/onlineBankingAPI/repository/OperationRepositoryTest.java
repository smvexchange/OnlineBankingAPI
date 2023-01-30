package com.smv.onlineBankingAPI.repository;

import com.smv.onlineBankingAPI.model.Operation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class OperationRepositoryTest {

    @Autowired
    OperationRepository operationRepository;

    @Test
    public void testRepo() {
        for (int i = 0; i < 10; i++) {
            operationRepository.save(new Operation());
        }
        final List<Operation> found = operationRepository.findAll();
        assertEquals(10, found.size());
    }
}