package bank_system.io;

import bank_system.controller.command.AuthorizedCommand;
import bank_system.controller.command.UnauthorizedCommand;
import bank_system.utils.validate.Validator;
import bank_system.utils.exception.InvalidCommandException;
import bank_system.utils.exception.InvalidJoinException;
import bank_system.utils.exception.InvalidMoneyException;

import java.util.Scanner;

public class InputView {

    public static String[] readIdAndPassword() {
        String[] inputs = new String[2];

        try {
            inputs[0] = readId();
            inputs[1] = readPassword();

            Validator.validateJoin(inputs[0], inputs[1]);
            return inputs;
        } catch (InvalidJoinException e) {
            OutputView.writeErrorMessageOf(e);

            return readIdAndPassword();
        }
    }

    public static UnauthorizedCommand readUnauthorizedCommand() {
        OutputView.writeUnauthorizedCommands();

        try {
            return UnauthorizedCommand.of(InputView.read());
        } catch (InvalidCommandException e) {
            OutputView.writeErrorMessageOf(e);

            return readUnauthorizedCommand();
        }
    }

    public static AuthorizedCommand readAuthorizedCommand() {
        OutputView.writeAuthorizedCommands();

        try {
            return AuthorizedCommand.of(InputView.read());
        } catch (InvalidCommandException e) {
            OutputView.writeErrorMessageOf(e);

            return readAuthorizedCommand();
        }
    }

    public static Long readMoney() {
        OutputView.writeForm("금액");

        try {
            return Long.parseLong(read());
        } catch (InvalidMoneyException e) {
            OutputView.writeln(e.getMessage());

            return readMoney();
        }
    }

    public static String readAccountNumber() {
        OutputView.writeln("보내실 계좌번호를 입력해주세요.");

        return read();
    }

    private static String read() {
        return new Scanner(System.in).nextLine();
    }

    private static String readId() {
        OutputView.writeForm("아이디");

        return read();
    }

    private static String readPassword() {
        OutputView.writeForm("비밀번호");

        return read();
    }
}
