package bank_system.repository;

import bank_system.domain.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccountRepository {

    private static final Map<String, Account> accounts = new HashMap<>();

    public static Account create() {
        Account account = Account.create();

        accounts.put(account.getId(), account);
        TransactionRepository.init(account);
        return account;
    }

    public static Optional<Account> findBy(String id) {
        Account account = accounts.get(id);

        if (account == null)
            return Optional.empty();

        return Optional.of(account);
    }
}
