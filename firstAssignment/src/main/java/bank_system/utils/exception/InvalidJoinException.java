package bank_system.utils.exception;

public class InvalidJoinException extends IllegalArgumentException {

    private static final String MESSAGE = "잘못된 아이디 혹은 비밀번호입니다.";

    public InvalidJoinException() {
        super(MESSAGE);
    }
}
