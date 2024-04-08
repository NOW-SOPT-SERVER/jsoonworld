package org.sopt.version3.view;

import org.sopt.version2.error.ErrorMessage;

import java.util.Scanner;
import java.util.Set;

public class InputView {
    private final Scanner scanner;
    private final Set<String> validAccountNumbers;

    public InputView(Set<String> validAccountNumbers) {
        this.scanner = new Scanner(System.in);
        this.validAccountNumbers = validAccountNumbers;
    }

    public int getUserOption() {
        while (true) {
            try {
                System.out.println("원하시는 서비스를 선택해주세요.");
                System.out.println("1. 입금하기");
                System.out.println("2. 출금하기");
                System.out.println("3. 계좌 이체");
                System.out.println("4. 잔액 조회");
                System.out.println("0. 종료하기");
                System.out.print("선택: ");
                int option = scanner.nextInt();
                if (option < 0) {
                    throw new IllegalArgumentException(ErrorMessage.INVALID_OPTION.getMessage());
                }
                return option;
            } catch (IllegalArgumentException e) {
                displayError(e.getMessage());
            } finally {
                if (scanner.hasNext()) {
                    scanner.nextLine();
                }
            }
        }
    }

    public int getAmount() {
        while (true) {
            try {
                System.out.print("금액을 입력해주세요: ");
                int amount = scanner.nextInt();
                if (amount <= 0) {
                    throw new IllegalArgumentException(ErrorMessage.INVALID_AMOUNT.getMessage());
                }
                return amount;
            } catch (IllegalArgumentException e) {
                displayError(e.getMessage());
            } finally {
                if (scanner.hasNext()) {
                    scanner.nextLine();
                }
            }
        }
    }

    public String getAccountNumber() {
        while (true) {
            try {
                displayValidAccounts();
                System.out.print("계좌 번호를 입력해주세요: ");
                String accountNumber = scanner.next();
                if (!validAccountNumbers.contains(accountNumber)) {
                    throw new IllegalArgumentException(ErrorMessage.INVALID_ACCOUNT_NUMBER.getMessage());
                }
                return accountNumber;
            } catch (IllegalArgumentException e) {
                displayError(e.getMessage());
            }
        }
    }

    private void displayValidAccounts() {
        System.out.println("사용할 수 있는 계좌:");
        validAccountNumbers.forEach(System.out::println);
    }

    private void displayError(String message) {
        System.out.println(message);
        scanner.nextLine();
    }
}
