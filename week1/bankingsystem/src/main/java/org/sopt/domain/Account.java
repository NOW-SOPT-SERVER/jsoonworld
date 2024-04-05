package org.sopt.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private String accountNumber;
    private Double balance;

    public Account(String accountNumber, Double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

}
