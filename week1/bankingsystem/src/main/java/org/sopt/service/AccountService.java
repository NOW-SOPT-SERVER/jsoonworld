package org.sopt.service;

import org.sopt.domain.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private List<Account> accounts = new ArrayList<>();

    public AccountService() {
        accounts.add(new Account("123-456-789", 1000.0));
        accounts.add(new Account("987-654-321", 2000.0));
    }

    // 입금 후 잔액 반환
    public double deposit(String accountNumber, Double amount) {
        Account account = findAccountByNumber(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            return account.getBalance(); // 변경된 잔액 반환
        }
        return -1; // 계좌를 찾지 못한 경우
    }

    // 출금 후 잔액 반환, 출금이 불가능한 경우 -1 반환
    public double withdraw(String accountNumber, Double amount) {
        Account account = findAccountByNumber(accountNumber);
        if (account != null && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            return account.getBalance(); // 변경된 잔액 반환
        }
        return -1; // 잔액 부족 또는 계좌를 찾지 못한 경우
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
