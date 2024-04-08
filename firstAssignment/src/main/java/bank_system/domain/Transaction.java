package bank_system.domain;

import java.time.LocalDateTime;

public class Transaction {

    private final Account from;
    private final Account to;
    private final Money money;
    private final LocalDateTime createdAt;
    private Type type;

    public Transaction(Account from, Account to, Long amount) {
        this.from = from;
        this.to = to;
        this.money = Money.of(amount);
        this.createdAt = LocalDateTime.now();
    }

    public static Transaction deposit(Account account, Long amount) {
        account.increase(amount);

        return new Transaction(account, account, amount);
    }

    public static Transaction withdraw(Account account, Long amount) {
        account.decrease(amount);

        return new Transaction(account, account, amount);
    }

    public static Transaction transfer(Account fromAccount, Account toAccount, Long amount) {
        fromAccount.decrease(amount);
        toAccount.increase(amount);

        return new Transaction(fromAccount, toAccount, amount);
    }

    public enum Type {
        DEPOSIT,
        WITHDRAW,
        TRANSFER
    }
}

