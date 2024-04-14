package bank_system.domain;

public class Customer {

    private final String password;
    private final Account account;

    private Customer(String password, Account account) {
        this.password = password;
        this.account = account;
    }

    public static Customer create(String password, Account account) {
        return new Customer(password, account);
    }

    public Account getAccount() {
        return account;
    }

    public boolean canAccessWith(String password) {
        return this.password.equals(password);
    }
}
