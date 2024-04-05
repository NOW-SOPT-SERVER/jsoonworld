package org.sopt.view.input;

import java.util.Scanner;

public class UserInputView {
    private Scanner scanner;

    public UserInputView() {
        this.scanner = new Scanner(System.in);
    }

    public int getUserOption() {
        System.out.println("환영합니다. SOPT 은행에 접속하셨습니다.");
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
        System.out.print("계좌 번호를 입력해주세요: ");
        return scanner.next();
    }
}
