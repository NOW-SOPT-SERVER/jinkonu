package bank_system.utils.exception;

public class InvalidMoneyException extends NumberFormatException {

    private static final String MESSAGE = "입력하신 값은 숫자가 아닙니다. 다시 금액을 입력해주세요.";

    public InvalidMoneyException() {
        super(MESSAGE);
    }
}
