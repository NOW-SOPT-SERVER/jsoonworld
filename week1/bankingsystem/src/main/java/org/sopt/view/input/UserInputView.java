package org.sopt.view.input;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class UserInputView {
    private Scanner scanner;
    private Set<String> validAccountNumbers;

    public UserInputView() {
        this.scanner = new Scanner(System.in);
        validAccountNumbers = new HashSet<>();
        validAccountNumbers.add("123-456-789");
        validAccountNumbers.add("987-654-321");
    }

    public int getUserOption() {
        System.out.println("환영합니다. SOPT 은행입니다.");
        System.out.println("원하시는 서비스를 선택해주세요.");
        System.out.println("1. 입금하기");
        System.out.println("2. 출금하기");
        System.out.println("3. 계좌 이체");
        System.out.println("4. 잔액 조회");
        System.out.println("0. 종료하기");
        System.out.print("선택: ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next(); // 현재 입력 스트림에 남아있는 잘못된 문자 제거
            System.out.println("[ERROR] 숫자를 입력해주세요.");
            return getUserOption(); // 재귀 호출로 다시 선택 받음
        }
    }

    public double getAmount() {
        System.out.print("금액을 입력해주세요: ");
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            scanner.next(); // 잘못된 입력 제거
            System.out.println("[ERROR] 숫자를 입력해주세요.");
            return getAmount(); // 재귀 호출로 다시 금액을 입력받음
        }
    }

    public String getAccountNumber() {
        while (true) {
            System.out.println("사용할 수 있는 계좌:");
            validAccountNumbers.forEach(System.out::println);
            System.out.print("계좌 번호를 입력해주세요: ");
            String accountNumber = scanner.next();
            if (validAccountNumbers.contains(accountNumber)) {
                return accountNumber;
            } else {
                System.out.println("[ERROR] 유효하지 않은 계좌 번호입니다. 다시 입력해주세요.");
            }
        }
    }
}
