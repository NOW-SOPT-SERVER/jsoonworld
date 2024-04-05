package org.sopt.version1.service;

import org.sopt.version1.domain.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private List<Account> accounts = new ArrayList<>();

    public AccountService() {
        accounts.add(new Account("123-456-789", 0.0));
        accounts.add(new Account("987-654-321", 0.0));
    }


    // 입금 후 잔액 반환
    public double deposit(String accountNumber, Double amount) {
        Account account = findAccountByNumber(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("[ERROR] 계좌를 찾을 수 없습니다.");
        }
        account.setBalance(account.getBalance() + amount);
        return account.getBalance();
    }

    // 출금 후 잔액 반환
    public double withdraw(String accountNumber, Double amount) {
        Account account = findAccountByNumber(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("[ERROR] 계좌를 찾을 수 없습니다.");
        }
        if (account.getBalance() < amount) {
            throw new IllegalStateException("[ERROR] 잔액 부족");
        }
        account.setBalance(account.getBalance() - amount);
        return account.getBalance();
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
