package org.sopt.version3.service;

import org.sopt.version3.domain.Account;
import org.sopt.version3.error.ErrorMessage;
import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final Map<String, Account> accounts;

    public AccountService() {
        this.accounts = new HashMap<>();
    }

    // 계좌 추가 메소드
    public void addAccount(String accountNumber, Integer initialBalance) {
        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException(ErrorMessage.ACCOUNT_ALREADY_EXISTS.toString());
        }
        accounts.put(accountNumber, new Account(accountNumber, initialBalance));
    }


    // 계좌 제거 메소드 (선택적)
    public void removeAccount(String accountNumber) {
        if (!accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException(ErrorMessage.ACCOUNT_NOT_FOUND.toString());
        }
        accounts.remove(accountNumber);
    }

    public Account getAccount(String accountNumber) throws IllegalArgumentException {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ACCOUNT_NUMBER.toString());
        }
        return account;
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
