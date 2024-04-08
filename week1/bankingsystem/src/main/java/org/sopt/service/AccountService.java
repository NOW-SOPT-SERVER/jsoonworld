package org.sopt.service;

import org.sopt.domain.Account;
import org.sopt.error.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    // 계좌 정보를 저장하는 리스트
    private final List<Account> accounts = new ArrayList<>();

    // AccountService 생성자: 초기 계좌 정보를 리스트에 추가
    public AccountService() {
        accounts.add(new Account("123-456-789", 0));
        accounts.add(new Account("987-654-321", 0));
    }

    // 계좌 번호로 계좌를 찾는 메서드
    private Account findAccountByNumber(String accountNumber) {
        return accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_ACCOUNT_NUMBER.toString()));
    }

    // 입금 메서드
    public void deposit(String accountNumber, Integer amount) {
        Account account = findAccountByNumber(accountNumber);
        account.deposit(amount);
    }

    // 출금 메서드
    public void withdraw(String accountNumber, Integer amount) {
        Account account = findAccountByNumber(accountNumber);
        account.withdraw(amount);
    }

    // 이체 메서드
    public void transfer(String fromAccountNumber, String toAccountNumber, Integer amount) {
        // 이체하는 계좌와 받는 계좌를 찾음
        Account fromAccount = findAccountByNumber(fromAccountNumber);
        Account toAccount = findAccountByNumber(toAccountNumber);
        // 이체하는 계좌의 잔액이 이체 금액보다 적은 경우 오류 발생
        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException(ErrorMessage.INSUFFICIENT_BALANCE.toString());
        }
        // 이체 수행
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }

    // 잔액 조회 메서드
    public Integer checkBalance(String accountNumber) {
        Account account = findAccountByNumber(accountNumber);
        return account.getBalance();
    }
}
