package org.sopt.version2.error;

public enum ErrorMessage {

    INVALID_OPTION("[ERROR] 유효하지 않은 선택입니다. 다시 입력해 주세요."),
    INVALID_AMOUNT("[ERROR] 유효하지 않은 금액입니다. 다시 입력해 주세요."),
    INVALID_ACCOUNT_NUMBER("[ERROR] 유효하지 않은 계좌 번호입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
