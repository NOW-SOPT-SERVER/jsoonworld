package org.sopt.service;

import org.sopt.domain.Account;

public class AccountService {

    public void deposit(Account account, Double amount) {
        Double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
    }

    public boolean withdraw(Account account, Double amount) {
        if (amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            return true;
        }
        return false;
    }

    public boolean transfer(Account fromAccount, Account toAccount, Double amount) {
        if (withdraw(fromAccount, amount)) {
            deposit(toAccount, amount);
            return true;
        }
        return false;
    }
}
