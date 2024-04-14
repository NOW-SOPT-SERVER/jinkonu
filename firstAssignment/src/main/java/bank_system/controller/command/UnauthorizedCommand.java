package bank_system.controller.command;

import bank_system.controller.CustomerController;
import bank_system.utils.dto.AuthResult;
import bank_system.utils.exception.InvalidCommandException;

public enum UnauthorizedCommand {

    JOIN_CUSTOMER("회원가입") {
        @Override
        public AuthResult operate() {
            return CustomerController.join();
        }
    },
    LOGIN("로그인") {
        @Override
        public AuthResult operate() {
            return CustomerController.login();
        }
    };

    public final String name;
    public abstract AuthResult operate();

    UnauthorizedCommand(String name) {
        this.name = name;
    }

    public static UnauthorizedCommand of(String name) {
        for (UnauthorizedCommand command : UnauthorizedCommand.values())
            if (command.name.equals(name)) return command;

        throw new InvalidCommandException();
    }
}
