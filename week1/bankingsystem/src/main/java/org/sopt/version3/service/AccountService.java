package org.sopt.version3.service;

import org.sopt.version2.domain.Account;
import org.sopt.version2.error.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private final List<Account> accounts = new ArrayList<>();

    public AccountService() {
        accounts.add(new Account("123-456-789", 0));
        accounts.add(new Account("987-654-321", 0));
    }

    public Account getAccount(String accountNumber) throws IllegalArgumentException {
        return accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_ACCOUNT_NUMBER.toString()));
    }

    public void deposit(String accountNumber, Integer amount) {
        Account account = getAccount(accountNumber);
        account.deposit(amount);
    }

    public void withdraw(String accountNumber, Integer amount) {
        Account account = getAccount(accountNumber);
        account.withdraw(amount);
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, Integer amount) {
        Account fromAccount = getAccount(fromAccountNumber);
        Account toAccount = getAccount(toAccountNumber);
        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException(ErrorMessage.INSUFFICIENT_BALANCE.toString());
        }
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }

    public Integer checkBalance(String accountNumber) {
        Account account = getAccount(accountNumber);
        return account.getBalance();
    }
}
