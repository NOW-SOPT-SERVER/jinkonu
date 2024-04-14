package bank_system.io;

import bank_system.controller.command.AuthorizedCommand;
import bank_system.controller.command.UnauthorizedCommand;

public class OutputView {

    public static void write(String output) {
        System.out.print(output);
    }

    public static void writeln(String output) {
        System.out.println(output);
    }

    public static void writeErrorMessageOf(Exception e) {
        writeln(e.getMessage());
    }

    public static void writeUnauthorizedCommands() {
        writeCommandForm();

        for (UnauthorizedCommand command : UnauthorizedCommand.values())
            writeln(command.name);

        write(">>> ");
    }

    public static void writeAuthorizedCommands() {
        writeCommandForm();

        for (AuthorizedCommand command : AuthorizedCommand.values())
            writeln(command.name);

        write(">>> ");
    }

    private static void writeCommandForm() {
        writeln("아래 명령어 중 선택하여 작성해주세요.");
    }

    public static void writeOperationResult(String result) {
        writeln("\n" + result);
    }

    public static void writeForm(String target) {
        write(target + "을(를) 입력해주세요. : ");
    }
}
