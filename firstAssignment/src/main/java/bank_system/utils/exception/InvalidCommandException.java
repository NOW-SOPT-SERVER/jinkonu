package bank_system.utils.exception;

public class InvalidCommandException extends IllegalArgumentException {

    private static final String MESSAGE = "잘못된 명령어입니다. 다시 입력해주세요.";

    public InvalidCommandException() {
        super(MESSAGE);
    }
}
