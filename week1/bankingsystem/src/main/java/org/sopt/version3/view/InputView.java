package org.sopt.version3.view;

import org.sopt.version3.error.ErrorMessage;

import java.util.InputMismatchException;
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
                scanner.nextLine(); // 입력 스트림에서 줄바꿈 문자 소비
                if (option >= 0 && option <= 4) {
                    return option;
                } else {
                    throw new IllegalArgumentException(ErrorMessage.INVALID_OPTION.getMessage());
                }
            } catch (InputMismatchException e) {
                System.err.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                scanner.nextLine(); // 입력 스트림에서 잘못된 입력 소비
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public String getRecipientAccountNumber() {
        while (true) {
            try {
                System.out.println("이체 받을 계좌의 번호를 입력해주세요:");
                String accountNumber = scanner.next();
                scanner.nextLine(); // 입력 스트림에서 줄바꿈 문자 소비
                if (validAccountNumbers.contains(accountNumber)) {
                    return accountNumber;
                } else {
                    throw new IllegalArgumentException(ErrorMessage.INVALID_ACCOUNT_NUMBER.getMessage());
                }
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public int getAmount() {
        while (true) {
            try {
                System.out.print("금액을 입력해주세요: ");
                int amount = scanner.nextInt();
                scanner.nextLine(); // 입력 스트림에서 줄바꿈 문자 소비
                if (amount > 0) {
                    return amount;
                } else {
                    throw new IllegalArgumentException(ErrorMessage.INVALID_AMOUNT.getMessage());
                }
            } catch (InputMismatchException e) {
                System.err.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                scanner.nextLine(); // 입력 스트림에서 잘못된 입력 소비
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public String getAccountNumber() {
        while (true) {
            try {
                displayValidAccounts();
                System.out.print("계좌 번호를 입력해주세요: ");
                String accountNumber = scanner.next();
                scanner.nextLine(); // 입력 스트림에서 줄바꿈 문자 소비
                if (validAccountNumbers.contains(accountNumber)) {
                    return accountNumber;
                } else {
                    throw new IllegalArgumentException(ErrorMessage.INVALID_ACCOUNT_NUMBER.getMessage());
                }
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void displayValidAccounts() {
        System.out.println("사용할 수 있는 계좌:");
        validAccountNumbers.forEach(System.out::println);
    }

    private void displayError(String message) {
        System.err.println(message);
    }
}
