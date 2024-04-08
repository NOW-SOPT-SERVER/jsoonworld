package org.sopt.version3.view;

import org.sopt.version3.error.ErrorMessage;

public class OutputView {
    private static final String BALANCE_MESSAGE_TEMPLATE = "현재 잔액은 %d원입니다.\n";
    private static final String SUCCESS_MESSAGE_TEMPLATE = "%s 완료되었습니다. 금액: %d원\n";
    private static final String EXIT_MESSAGE = "프로그램을 종료합니다.";
    private static final String ACCOUNT_NUMBER_REQUEST = "해당 서비스를 이용할 계좌의 번호를 입력해주세요.";
    private static final String RECIPIENT_ACCOUNT_NUMBER_REQUEST = "이체 받을 계좌의 번호를 입력해주세요:";

    public void displayBalance(int balance) {
        System.out.printf(BALANCE_MESSAGE_TEMPLATE, balance);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displaySuccessMessage(String operation, int amount) {
        System.out.printf(SUCCESS_MESSAGE_TEMPLATE, operation, amount);
    }

    public void displayErrorMessage(ErrorMessage errorMessage) {
        displayMessage(errorMessage.getMessage());
    }

    public void displayProgramExitMessage() {
        displayMessage(EXIT_MESSAGE);
    }

    public void displayInvalidOptionMessage() {
        displayErrorMessage(ErrorMessage.INVALID_OPTION);
    }

    public void requestAccountNumberMessage() {
        displayMessage(ACCOUNT_NUMBER_REQUEST);
    }

    public void requestRecipientAccountNumberMessage() {
        displayMessage(RECIPIENT_ACCOUNT_NUMBER_REQUEST);
    }
}
