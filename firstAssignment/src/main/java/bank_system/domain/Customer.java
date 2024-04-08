package bank_system.domain;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private String password;
    private List<Account> accounts;

    private Customer(String name, String password) {
        this.name = name;
        this.password = password;
        this.accounts = new ArrayList<>();
    }

    public static Customer create(String name, String password) {
        return new Customer(name, password);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public boolean canAccessWith(String password) {
        return this.password.equals(password);
    }
}
