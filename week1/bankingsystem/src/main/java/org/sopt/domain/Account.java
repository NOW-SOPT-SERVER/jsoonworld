package org.sopt.domain;

import lombok.Getter;
import org.sopt.error.ErrorMessage;

@Getter
public class Account {
    private String accountNumber; // 계좌번호
    private Integer balance; // 잔액

    // 생성자: 계좌번호와 초기 잔액을 받아 계좌 객체를 생성
    public Account(String accountNumber, Integer initialBalance) {
        // 초기 잔액이 음수인 경우 예외 발생
        if (initialBalance == null || initialBalance < 0) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_INITIAL_BALANCE.toString());
        }
        this.accountNumber = accountNumber; // 계좌번호 설정
        this.balance = initialBalance; // 초기 잔액 설정
    }

    // 입금 메서드
    public void deposit(Integer amount) {
        // 입금 금액이 유효하지 않은 경우 예외 발생
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DEPOSIT_AMOUNT.toString());
        }
        this.balance += amount; // 잔액 증가
    }

    // 출금 메서드
    public void withdraw(Integer amount) {
        // 출금 금액이 유효하지 않은 경우 예외 발생
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_WITHDRAW_AMOUNT.toString());
        }
        // 잔액이 출금 금액보다 적은 경우 예외 발생
        if (this.balance < amount) {
            throw new IllegalArgumentException(ErrorMessage.INSUFFICIENT_BALANCE.toString());
        }
        this.balance -= amount; // 잔액 감소
    }
}
