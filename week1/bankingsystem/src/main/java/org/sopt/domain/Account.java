package org.sopt.domain;

import lombok.Getter;

@Getter
public class Account {
    private String accountNumber;
    private Double balance;

    public Account(String accountNumber, Double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void deposit(Double amount) {
        balance += amount;
    }

    public boolean withdraw(Double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(Account toAccount, Double amount) {
        if (withdraw(amount)) {
            toAccount.deposit(amount);
            return true;
        }
        return false;
    }

}
