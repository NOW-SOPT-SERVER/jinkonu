package bank_system.domain;

public abstract class Transaction {

    private Account from;
    private Account to;
    private Money amount;

    public abstract void transact();
}
