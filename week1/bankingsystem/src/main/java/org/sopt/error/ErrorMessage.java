package org.sopt.error;

public enum ErrorMessage {
    NEGATIVE_INITIAL_BALANCE("[ERROR] 초기 잔액은 음수일 수 없습니다."),
    INVALID_DEPOSIT_AMOUNT("[ERROR] 입금 금액은 양수이어야 합니다."),
    INVALID_WITHDRAW_AMOUNT("[ERROR] 출금 금액은 양수이어야 합니다."),
    INSUFFICIENT_BALANCE("[ERROR] 잔액이 부족합니다."),
    INVALID_OPTION("[ERROR] 유효하지 않은 선택입니다. 다시 입력해 주세요."),
    INVALID_AMOUNT("[ERROR] 유효하지 않은 금액입니다. 다시 입력해 주세요."),
    INVALID_ACCOUNT_NUMBER("[ERROR] 유효하지 않은 계좌 번호입니다. 다시 입력해 주세요."),
    ACCOUNT_ALREADY_EXISTS("[ERROR] 계좌 번호가 이미 존재합니다."),
    ACCOUNT_NOT_FOUND("[ERROR] 계좌 번호가 존재하지 않습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

    public String getMessage() {
        return message;
    }
}
