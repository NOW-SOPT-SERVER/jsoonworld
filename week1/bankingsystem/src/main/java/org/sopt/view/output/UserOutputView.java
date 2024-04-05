package org.sopt.view.output;

public class UserOutputView {

    public void displayBalance(double balance) {
        System.out.printf("현재 잔액은 %.2f원입니다.\n", balance);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displaySuccessMessage(String operation, double amount) {
        System.out.printf("%s 이(가) 성공적으로 완료되었습니다. 금액: %.2f원\n", operation, amount);
    }

    public void displayErrorMessage(String message) {
        System.out.println("오류: " + message);
    }
}
