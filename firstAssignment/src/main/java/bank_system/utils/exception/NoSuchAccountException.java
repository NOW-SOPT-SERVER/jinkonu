package bank_system.utils.exception;

import java.util.NoSuchElementException;

public class NoSuchAccountException extends NoSuchElementException {

    private static final String MESSAGE = "입력하신 계좌번호가 없습니다. 다시 입력해주세요.";

    public NoSuchAccountException() {
        super(MESSAGE);
    }
}
