package bank_system.repository;

import bank_system.domain.Account;
import bank_system.domain.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomerRepository {

    private static final Map<String, Customer> customers = new HashMap<>();

    public static Customer join(String id, String password) {
        Account account = AccountRepository.create();
        Customer customer = Customer.create(password, account);

        customers.put(id, customer);
        return customer;
    }

    public static Optional<Customer> findByIdAndPassword(String id, String password) {
        Customer customer = customers.get(id);

        if (customer == null || !customer.canAccessWith(password))
            return Optional.empty();

        return Optional.of(customer);
    }
}
