package bank_system.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Account {

    private final Customer owner;
    private final String id;
    private Status status;
    private Money balance;
    private List<Transaction> transactions;

    private Account(Customer owner) {
        this.owner = owner;
        this.id = UUID.randomUUID().toString();
        this.status = Status.AVAILABLE;
        this.balance = Money.zero();
        this.transactions = new ArrayList<>();
    }

    public static Account create(Customer owner) {
        return new Account(owner);
    }

    public String getId() {
        return id;
    }

    public void stop() {
        status = Status.STOPPED;
    }

    public List<String> history() {
        return transactions.stream()
                .map(Transaction::toString)
                .toList();
    }

    public void increase(Long amount) {
        balance = balance.add(amount);
    }

    public void decrease(Long amount) {
        balance = balance.subtract(amount);
    }

    public enum Status {
        AVAILABLE,
        STOPPED
    }
}
