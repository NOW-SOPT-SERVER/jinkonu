package bank_system.domain;

import bank_system.utils.exception.ShortMoneyException;

public class Money {

    private static final Long INITIAL_MONEY = 0L;
    private final Long amount;

    private Money() {
        this.amount = INITIAL_MONEY;
    }

    private Money(Long amount) {
        this.amount = amount;
    }

    public static Money zero() {
        return new Money();
    }

    public static Money of(Long amount) {
        return new Money(amount);
    }

    public Long getAmount() {
        return amount;
    }

    public Money add(Long amount) {
        return new Money(this.amount + amount);
    }

    public Money subtract(Long amount) {
        if (this.amount < amount) throw new ShortMoneyException();

        return new Money(this.amount - amount);
    }
}

