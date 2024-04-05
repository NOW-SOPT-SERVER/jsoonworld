package org.sopt.version2.view;

import org.sopt.version2.error.ErrorMessage;

public class OutputView {

    public void displayBalance(int balance) {
        System.out.printf("현재 잔액은 %d원입니다.\n", balance);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displaySuccessMessage(String operation, int amount) {
        System.out.printf("%s 완료되었습니다. 금액: %d원\n", operation, amount);
    }

    public void displayErrorMessage(ErrorMessage errorMessage) {
        System.out.println(errorMessage.getMessage());
    }

    public void displayProgramExitMessage() {
        displayMessage("프로그램을 종료합니다.");
    }

    public void displayInvalidOptionMessage() {
        displayErrorMessage(ErrorMessage.INVALID_OPTION);
    }

    public void displayInputErrorMessage(ErrorMessage errorMessage) {
        displayErrorMessage(errorMessage);
    }

}
