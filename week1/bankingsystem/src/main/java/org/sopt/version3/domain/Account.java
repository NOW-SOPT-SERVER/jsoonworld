package org.sopt.version3.domain;

import lombok.Getter;
import org.sopt.version3.error.ErrorMessage;

@Getter
public class Account {
    private String accountNumber;
    private Integer balance;

    public Account(String accountNumber, Integer initialBalance) {
        if (initialBalance == null || initialBalance < 0) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_INITIAL_BALANCE.toString());
        }
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // 입금 메서드
    public void deposit(Integer amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DEPOSIT_AMOUNT.toString());
        }
        this.balance += amount;
    }

    // 출금 메서드
    public void withdraw(Integer amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_WITHDRAW_AMOUNT.toString());
        }
        if (this.balance < amount) {
            throw new IllegalArgumentException(ErrorMessage.INSUFFICIENT_BALANCE.toString());
        }
        this.balance -= amount;
    }

}
