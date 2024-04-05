package org.sopt.controller;

import org.sopt.domain.Account;
import org.sopt.service.AccountService;
import org.sopt.view.input.UserInputView;
import org.sopt.view.output.UserOutputView;

public class BankController {
    private AccountService accountService;
    private UserInputView userInputView;
    private UserOutputView userOutputView;
    private Account account; // 간단한 예제를 위해 단일 계좌만 사용

    public BankController(Account account, AccountService accountService, UserInputView userInputView, UserOutputView userOutputView) {
        this.account = account;
        this.accountService = accountService;
        this.userInputView = userInputView;
        this.userOutputView = userOutputView;
    }

    public void start() {
        boolean running = true;
        while (running) {
            int option = userInputView.getUserOption();
            switch (option) {
                case 1: // 입금
                    double depositAmount = userInputView.getAmount();
                    accountService.deposit(account, depositAmount);
                    userOutputView.displaySuccessMessage("입금", depositAmount);
                    break;
                case 2: // 출금
                    double withdrawAmount = userInputView.getAmount();
                    if (accountService.withdraw(account, withdrawAmount)) {
                        userOutputView.displaySuccessMessage("출금", withdrawAmount);
                    } else {
                        userOutputView.displayErrorMessage("잔액 부족");
                    }
                    break;
                case 3: // 계좌 이체
                    //TODO: 계좌 이체 로직은 여기에 구현, 실제 애플리케이션에서는 이체할 계좌의 입력도 필요
                    break;
                case 4: // 잔액 조회
                    double balance = accountService.checkBalance(account);
                    userOutputView.displayBalance(balance);
                    break;
                case 0: // 종료
                    running = false;
                    userOutputView.displayMessage("프로그램을 종료합니다.");
                    break;
                default:
                    userOutputView.displayErrorMessage("잘못된 선택입니다.");
                    break;
            }
        }
    }
}

