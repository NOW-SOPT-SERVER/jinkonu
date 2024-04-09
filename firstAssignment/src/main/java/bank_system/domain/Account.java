package bank_system.domain;

import java.util.UUID;

public class Account {

    private final String id;
    private Money balance;

    private Account() {
        this.id = UUID.randomUUID().toString();
        this.balance = Money.zero();
    }

    public static Account create() {
        return new Account();
    }

    public String getId() {
        return id;
    }

    public Long getBalance() {
        return balance.getAmount();
    }

    public void increase(Long amount) {
        balance = balance.add(amount);
    }

    public void decrease(Long amount) {
        balance = balance.subtract(amount);
    }
}
