package org.sopt.controller;

import org.sopt.service.AccountService;
import org.sopt.view.input.UserInputView;
import org.sopt.view.output.UserOutputView;

public class BankController {
    private AccountService accountService = new AccountService();
    private UserInputView userInputView = new UserInputView();
    private UserOutputView userOutputView = new UserOutputView();

    public void start() {
        boolean running = true;
        while (running) {
            int option = userInputView.getUserOption();
            switch (option) {
                case 1: // 입금
                    userOutputView.displayMessage("입금할 계좌의 번호를 입력해주세요:");
                    String depositAccountNumber = userInputView.getAccountNumber();
                    double depositAmount = userInputView.getAmount();
                    accountService.deposit(depositAccountNumber, depositAmount);
                    userOutputView.displaySuccessMessage("입금", depositAmount);
                    break;
                case 2: // 출금
                    userOutputView.displayMessage("출금할 계좌의 번호를 입력해주세요:");
                    String withdrawAccountNumber = userInputView.getAccountNumber();
                    double withdrawAmount = userInputView.getAmount();
                    if (accountService.withdraw(withdrawAccountNumber, withdrawAmount)) {
                        userOutputView.displaySuccessMessage("출금", withdrawAmount);
                    } else {
                        userOutputView.displayErrorMessage("잔액 부족 또는 계좌 오류");
                    }
                    break;
                case 3: // 계좌 이체
                    userOutputView.displayMessage("이체할 계좌의 번호를 입력해주세요:");
                    String fromAccountNumber = userInputView.getAccountNumber();
                    userOutputView.displayMessage("이체 받을 계좌의 번호를 입력해주세요:");
                    String toAccountNumber = userInputView.getAccountNumber();
                    double transferAmount = userInputView.getAmount();
                    if (accountService.transfer(fromAccountNumber, toAccountNumber, transferAmount)) {
                        userOutputView.displaySuccessMessage("이체", transferAmount);
                    } else {
                        userOutputView.displayErrorMessage("이체 실패: 잔액 부족 또는 계좌 오류");
                    }
                    break;
                case 4: // 잔액 조회
                    userOutputView.displayMessage("조회할 계좌의 번호를 입력해주세요:");
                    String balanceAccountNumber = userInputView.getAccountNumber();
                    double balance = accountService.checkBalance(balanceAccountNumber);
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
