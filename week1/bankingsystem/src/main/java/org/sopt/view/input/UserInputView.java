package org.sopt.view.input;

import java.util.Scanner;
import java.util.Set;

public class UserInputView {
    private Scanner scanner;
    private Set<String> validAccountNumbers;

    public UserInputView() {
        this.scanner = new Scanner(System.in);
        validAccountNumbers = Set.of("123-456-789", "987-654-321");
    }

    public int getUserOption() {
        System.out.println("원하시는 서비스를 선택해주세요.");
        System.out.println("1. 입금하기");
        System.out.println("2. 출금하기");
        System.out.println("3. 계좌 이체");
        System.out.println("4. 잔액 조회");
        System.out.println("0. 종료하기");
        System.out.print("선택: ");
        return scanner.nextInt();
    }

    public double getAmount() {
        System.out.print("금액을 입력해주세요: ");
        return scanner.nextDouble();
    }

    public String getAccountNumber() {
        displayValidAccounts();
        System.out.print("계좌 번호를 입력해주세요: ");
        String accountNumber = scanner.next();

        if (!validAccountNumbers.contains(accountNumber)) {
            System.out.println("[ERROR] 유효하지 않은 계좌 번호입니다. 다시 입력해주세요.");
            return getAccountNumber();
        }
        return accountNumber;
    }

    private void displayValidAccounts() {
        System.out.println("사용할 수 있는 계좌:");
        for (String accountNumber : validAccountNumbers) {
            System.out.println(accountNumber);
        }
    }
}
