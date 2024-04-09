package bank_system.controller;

import bank_system.utils.dto.AuthResult;
import bank_system.controller.command.AuthorizedCommand;
import bank_system.controller.command.UnauthorizedCommand;
import bank_system.domain.Customer;
import bank_system.io.InputController;
import bank_system.io.OutputController;

public class BankController {

    private static final String WELCOME_MESSAGE = "은행 프로그램에 접속하신 걸 환영합니다.\n아래 메뉴 중 선택하여 메뉴명을 입력해주세요.\n";

    public static void run() {
        welcome();

        Customer customer = authorize();
        executeCommandOf(customer);
    }

    private static void welcome() {
        OutputController.writeln(WELCOME_MESSAGE);
    }

    private static Customer authorize() {
        UnauthorizedCommand unauthorizedCommand = InputController.readUnauthorizedCommand();
        AuthResult result = unauthorizedCommand.operate();

        OutputController.writeOperationResult(result.message());
        return result.customer();
    }

    private static void executeCommandOf(Customer customer) {
        AuthorizedCommand authorizedCommand = InputController.readAuthorizedCommand();
        String result = authorizedCommand.operate(customer.getAccount());

        OutputController.writeOperationResult(result);
        if (authorizedCommand.isNotFinished()) executeCommandOf(customer);
    }
}
