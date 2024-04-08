package org.sopt.view;

import org.sopt.error.ErrorMessage;

import java.util.Scanner;
import java.util.Set;

public class InputView {
    // 입력을 받기 위한 Scanner 객체와 유효한 계좌 번호 집합
    private Scanner scanner;
    private Set<String> validAccountNumbers;

    // 생성자: Scanner 초기화 및 유효한 계좌 번호 설정
    public InputView() {
        this.scanner = new Scanner(System.in);
        validAccountNumbers = Set.of("123-456-789", "987-654-321");
    }

    // 사용자의 선택을 받는 메서드
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
                if (!scanner.hasNextInt()) {
                    throw new IllegalArgumentException(ErrorMessage.INVALID_OPTION.getMessage());
                }
                return scanner.nextInt();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                scanner.next(); // 입력 스트림을 비우고 다음 입력을 받습니다.
            }
        }
    }

    // 금액을 입력받는 메서드
    public int getAmount() {
        while (true) {
            try {
                System.out.print("금액을 입력해주세요: ");
                if (!scanner.hasNextInt()) {
                    throw new IllegalArgumentException(ErrorMessage.INVALID_AMOUNT.getMessage());
                }
                return scanner.nextInt();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                scanner.next(); // 입력 스트림을 비우고 다음 입력을 받습니다.
            }
        }
    }

    // 계좌 번호를 입력받는 메서드
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
                System.out.println(e.getMessage());
            }
        }
    }

    // 유효한 계좌 목록을 출력하는 메서드
    private void displayValidAccounts() {
        System.out.println("사용할 수 있는 계좌:");
        validAccountNumbers.forEach(System.out::println);
    }
}
