package com.smv.onlineBankingAPI.repository;

import com.smv.onlineBankingAPI.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    ClientRepository clientRepository;

    @Test
    public void testRepo() {
        for (int i = 0; i < 10; i++) {
            clientRepository.save(new Client());
        }
        final List<Client> found = clientRepository.findAll();
        assertEquals(10, found.size());
    }
}