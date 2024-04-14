package bank_system.controller.command;

import bank_system.controller.AccountController;
import bank_system.controller.TransactionController;
import bank_system.domain.Account;
import bank_system.utils.exception.InvalidCommandException;

public enum AuthorizedCommand {
    BALANCE("잔액 조회") {
        @Override
        public String operate(Account account) {
            return AccountController.checkBalanceOf(account);
        }
    },
    TRANSACTION_HISTORY("거래 조회") {
        @Override
        public String operate(Account account) {
            return TransactionController.getTransactionHistoryOf(account);
        }
    },
    DEPOSIT("입금") {
        @Override
        public String operate(Account account) {
            return TransactionController.deposit(account);
        }
    },
    WITHDRAW("출금") {
        @Override
        public String operate(Account account) {
            return TransactionController.withdraw(account);
        }
    },
    TRANSFER("송금") {
        @Override
        public String operate(Account account) {
            return TransactionController.transfer(account);
        }
    },
    QUIT("종료") {
        @Override
        public String operate(Account account) {
            return QUIT.name;
        }
    };

    public final String name;
    public abstract String operate(Account account);

    AuthorizedCommand(String name) {
        this.name = name;
    }

    public static AuthorizedCommand of(String name) {
        for (AuthorizedCommand command : AuthorizedCommand.values())
            if (command.name.equals(name))
                return command;

        throw new InvalidCommandException();
    }

    public boolean isNotFinished() {
        return !this.equals(QUIT);
    }
}
