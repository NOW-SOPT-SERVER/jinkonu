package bank_system.utils.exception;

public class ShortMoneyException extends IllegalArgumentException {

    private static final String MESSAGE = "잔액이 부족합니다.";

    public ShortMoneyException() {
        super(MESSAGE);
    }
}
