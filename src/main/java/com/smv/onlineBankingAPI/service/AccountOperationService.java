package com.smv.onlineBankingAPI.service;

import com.smv.onlineBankingAPI.enums.OperationType;
import com.smv.onlineBankingAPI.model.Account;
import com.smv.onlineBankingAPI.model.AccountOperation;
import com.smv.onlineBankingAPI.model.TransferOperation;
import com.smv.onlineBankingAPI.model.TransferOperationKey;
import com.smv.onlineBankingAPI.repository.AccountRepository;
import com.smv.onlineBankingAPI.web.exception.IllegalParameterException;
import com.smv.onlineBankingAPI.web.exception.NoSuchAccountException;
import com.smv.onlineBankingAPI.web.exception.NotEnoughMoneyException;
import com.smv.onlineBankingAPI.web.response.BalanceResponse;
import com.smv.onlineBankingAPI.web.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountOperationService {

    private final AccountRepository accountRepository;

    public AccountOperationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public BalanceResponse getBalance(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new NoSuchAccountException("Account with id " + accountId + " not found.", -1));
        log.info("Account# " + accountId + ": Balance request completed.");
        return new BalanceResponse(account.getBalance());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {NotEnoughMoneyException.class, NoSuchAccountException.class})
    public BaseResponse takeMoney(Long accountId, BigDecimal cash) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new NoSuchAccountException("Account with id " + accountId + " not found.", 0));
        takeMoneyOperation(account, cash, OperationType.TAKE_MONEY);
        log.info("Account# " + accountId + ": Take out cash successful complete. Amount: " + cash + ".");
        return new BaseResponse(1);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {NoSuchAccountException.class})
    public BaseResponse putMoney(Long accountId, BigDecimal cash) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new NoSuchAccountException("Account with id " + accountId + " not found.", 0));
        putMoneyOperation(account, cash, OperationType.PUT_MONEY);
        log.info("Account# " + accountId + ": Cash deposit was successful complete. Amount: " + cash + ".");
        return new BaseResponse(1);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {NotEnoughMoneyException.class, NoSuchAccountException.class})
    public BaseResponse transferMoney(Long senderId, Long receiverId, BigDecimal cash) {
        Account sender = accountRepository.findById(senderId).orElseThrow(
                () -> new NoSuchAccountException("Account with id " + senderId + " not found.", 0));
        Account receiver = accountRepository.findById(receiverId).orElseThrow(
                () -> new NoSuchAccountException("Account with id " + receiverId + " not found.", 0));
        takeMoneyOperation(sender, cash, OperationType.SEND_MONEY);
        putMoneyOperation(receiver, cash, OperationType.RECEIVE_MONEY);
        TransferOperation transferOperation = TransferOperation.builder()
                .id(new TransferOperationKey(senderId, receiverId))
                .sender(sender)
                .receiver(receiver)
                .cash(cash)
                .build();
        sender.setOutgoingTransferSet(transferOperation);
        receiver.setIncomingTransferSet(transferOperation);
        accountRepository.save(sender);
        accountRepository.save(receiver);
        log.info("Account# " + senderId + ": Transfer money to account# " + receiverId + " completed. Amount: " + cash + ".");
        return new BaseResponse(1);
    }

    public Set<AccountOperation> getOperationList(Long accountId, LocalDateTime startDate, LocalDateTime endDate) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new NoSuchAccountException("Account with id " + accountId + " not found.", 0));
        Set<AccountOperation> accountOperations = account.getAccountOperationSet().stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toCollection(LinkedHashSet::new));
        if (startDate != null && endDate != null) {
            if (startDate.isBefore(endDate)) {
                accountOperations = accountOperations.stream()
                        .filter(operation -> operation.getLocalDateTime().isAfter(startDate))
                        .filter(operation -> operation.getLocalDateTime().isBefore(endDate))
                        .collect(Collectors.toCollection(LinkedHashSet::new));
            } else {
                throw new IllegalParameterException("StartDate parameter must be before endDate parameter", 0);
            }
        }
        if (startDate != null) {
            accountOperations = accountOperations.stream()
                    .filter(operation -> operation.getLocalDateTime().isAfter(startDate))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        }
        if (endDate != null) {
            accountOperations = accountOperations.stream()
                    .filter(operation -> operation.getLocalDateTime().isBefore(endDate))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        }
        log.info("Account# " + accountId + ": OperationList request completed.");
        return accountOperations;
    }

    private void takeMoneyOperation(Account account, BigDecimal cash, OperationType operationType) {
        if (account.getBalance().compareTo(cash) >= 0) {
            account.setBalance(account.getBalance().subtract(cash));
            AccountOperation accountOperation = AccountOperation.builder()
                    .localDateTime(LocalDateTime.now())
                    .account(account)
                    .operationType(operationType)
                    .cash(cash)
                    .build();
            account.setAccountOperationSet(accountOperation);
            accountRepository.save(account);
        } else {
            throw new NotEnoughMoneyException("Account# " + account.getId() + ": Transaction failed. Not enough money.", 0);
        }
    }

    private void putMoneyOperation(Account account, BigDecimal cash, OperationType operationType) {
        account.setBalance(account.getBalance().add(cash));
        AccountOperation accountOperation = AccountOperation.builder()
                .localDateTime(LocalDateTime.now())
                .account(account)
                .operationType(operationType)
                .cash(cash)
                .build();
        account.setAccountOperationSet(accountOperation);
        accountRepository.save(account);
    }
}
