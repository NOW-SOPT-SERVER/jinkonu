package bank_system.domain;

import bank_system.controller.command.AuthorizedCommand;

import static bank_system.controller.command.AuthorizedCommand.*;

public class Transaction {

    private final Account from;
    private final Account to;
    private final Money money;
    private final AuthorizedCommand type;

    private Transaction(Account from, Account to, Long amount, AuthorizedCommand type) {
        this.from = from;
        this.to = to;
        this.money = Money.of(amount);
        this.type = type;
    }

    public static Transaction deposit(Account account, Long amount) {
        account.increase(amount);

        return new Transaction(account, account, amount, DEPOSIT);
    }

    public static Transaction withdraw(Account account, Long amount) {
        account.decrease(amount);

        return new Transaction(account, account, amount, WITHDRAW);
    }

    public static Transaction transfer(Account fromAccount, Account toAccount, Long amount) {
        fromAccount.decrease(amount);
        toAccount.increase(amount);

        return new Transaction(fromAccount, toAccount, amount, TRANSFER);
    }

    @Override
    public String toString() {
        if (type.equals(DEPOSIT)) {
            return type.name + " " +
                    from.getId() + "에서 " +
                    money.getAmount() + "원 ";
        }

        if (type.equals(WITHDRAW)) {
            return type.name + " " +
                    to.getId() + "로 " +
                    money.getAmount() + "원 ";
        }

        if (type.equals(TRANSFER))
            return type.name + " " +
                    from.getId() + "에서 " +
                    to.getId() + "로 " +
                    money.getAmount() + "원 ";

        return null;
    }
}

