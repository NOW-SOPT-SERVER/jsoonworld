package org.sopt.service;

import org.sopt.domain.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private List<Account> accounts = new ArrayList<>();

    public AccountService() {
        // 초기 계좌 데이터 생성 (실제 애플리케이션에서는 사용자 입력을 통해 계좌를 추가합니다)
        accounts.add(new Account("123-456-789", 1000.0));
        accounts.add(new Account("987-654-321", 2000.0));
    }

    public void deposit(String accountNumber, Double amount) {
        Account account = findAccountByNumber(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
        }
    }

    public boolean withdraw(String accountNumber, Double amount) {
        Account account = findAccountByNumber(accountNumber);
        if (account != null && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            return true;
        }
        return false;
    }

    public boolean transfer(String fromAccountNumber, String toAccountNumber, Double amount) {
        Account fromAccount = findAccountByNumber(fromAccountNumber);
        Account toAccount = findAccountByNumber(toAccountNumber);

        if (fromAccount != null && toAccount != null && fromAccount.getBalance() >= amount) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            return true;
        }
        return false;
    }

    public double checkBalance(String accountNumber) {
        Account account = findAccountByNumber(accountNumber);
        return account != null ? account.getBalance() : 0;
    }

    private Account findAccountByNumber(String accountNumber) {
        return accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
    }
}
