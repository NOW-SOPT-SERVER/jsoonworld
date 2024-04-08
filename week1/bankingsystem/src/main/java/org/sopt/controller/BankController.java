package org.sopt.controller;

import org.sopt.error.ErrorMessage;
import org.sopt.service.AccountService;
import org.sopt.view.InputView;
import org.sopt.view.OutputView;

public class BankController {
    // 계좌 서비스, 입력 뷰, 출력 뷰 객체 선언
    private final AccountService accountService;
    private final InputView inputView;
    private final OutputView outputView;

    // 생성자: 계좌 서비스, 입력 뷰, 출력 뷰 객체 초기화
    public BankController() {
        this.accountService = new AccountService();
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    // 프로그램 실행 메서드
    public void start() {
        boolean running = true;
        while (running) {
            // 사용자가 선택한 옵션을 입력받음
            int option = inputView.getUserOption();
            // 사용자의 선택에 따라 해당 기능을 수행
            switch (option) {
                case 1:
                    processDeposit(); // 입금 처리
                    break;
                case 2:
                    processWithdraw(); // 출금 처리
                    break;
                case 3:
                    processTransfer(); // 이체 처리
                    break;
                case 4:
                    processCheckBalance(); // 잔액 조회 처리
                    break;
                case 0:
                    running = false; // 프로그램 종료
                    outputView.displayProgramExitMessage(); // 종료 메시지 출력
                    break;
                default:
                    outputView.displayInvalidOptionMessage(); // 잘못된 옵션 메시지 출력
                    break;
            }
        }
    }

    // 입금 처리 메서드
    private void processDeposit() {
        try {
            outputView.requestAccountNumberMessage(); // 계좌 번호 요청 메시지 출력
            String accountNumber = inputView.getAccountNumber(); // 계좌 번호 입력 받음
            Integer amount = inputView.getAmount(); // 입금 금액 입력 받음
            accountService.deposit(accountNumber, amount); // 입금 처리
            outputView.displaySuccessMessage("입금", amount); // 입금 성공 메시지 출력
        } catch (IllegalArgumentException e) {
            handleException(e); // 예외 처리
        }
    }

    // 출금 처리 메서드
    private void processWithdraw() {
        try {
            outputView.requestAccountNumberMessage(); // 계좌 번호 요청 메시지 출력
            String accountNumber = inputView.getAccountNumber(); // 계좌 번호 입력 받음
            Integer amount = inputView.getAmount(); // 출금 금액 입력 받음
            accountService.withdraw(accountNumber, amount); // 출금 처리
            outputView.displaySuccessMessage("출금", amount); // 출금 성공 메시지 출력
        } catch (IllegalArgumentException e) {
            handleException(e); // 예외 처리
        }
    }

    // 이체 처리 메서드
    private void processTransfer() {
        try {
            outputView.requestAccountNumberMessage(); // 송금 계좌 번호 요청 메시지 출력
            String fromAccountNumber = inputView.getAccountNumber(); // 송금 계좌 번호 입력 받음
            outputView.requestRecipientAccountNumberMessage(); // 수신 계좌 번호 요청 메시지 출력
            String toAccountNumber = inputView.getAccountNumber(); // 수신 계좌 번호 입력 받음
            Integer amount = inputView.getAmount(); // 이체 금액 입력 받음
            accountService.transfer(fromAccountNumber, toAccountNumber, amount); // 이체 처리
            outputView.displaySuccessMessage("이체", amount); // 이체 성공 메시지 출력
        } catch (IllegalArgumentException e) {
            handleException(e); // 예외 처리
        }
    }

    // 잔액 조회 처리 메서드
    private void processCheckBalance() {
        outputView.requestAccountNumberMessage(); // 계좌 번호 요청 메시지 출력
        String accountNumber = inputView.getAccountNumber(); // 계좌 번호 입력 받음
        Integer balance = accountService.checkBalance(accountNumber); // 잔액 조회 처리
        outputView.displayBalance(balance); // 잔액 출력
    }

    // 예외 처리 메서드
    private void handleException(IllegalArgumentException e) {
        ErrorMessage errorMessage = ErrorMessage.valueOf(e.getMessage()); // 예외 메시지 가져옴
        outputView.displayInputErrorMessage(errorMessage); // 예외 메시지 출력
    }
}
